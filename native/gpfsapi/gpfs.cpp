/**
 *
 *  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2006-2010.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @file   gpfs.cpp
 * @author Riccardo Murri <riccardo.murri@ictp.it>
 *
 * Implementation of the gpfs class.
 *
 */


/// CVS Revision info
static const char* const RCSID="$Id: gpfs.cpp,v 1.10 2007/04/17 10:30:16 lmagnoni Exp $";


#include "gpfs.hpp"
#include "gpfs_fcntl.h"

#include "fs_errors.hpp"

#include <cassert>
#include <errno.h>
#include <fcntl.h>
#include <gpfs.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/vfs.h>
#include <unistd.h>
#include <sstream>
#include <stdexcept>

#include <unistd.h>
#include <iostream>
#include <stdio.h>

/** GPFS struct used to retrieve the fileset name out of a file **/
typedef struct {
  gpfsFcntlHeader_t hdr;
  gpfsGetFilesetName_t fileset;
  gpfsGetStoragePool_t pool;
} gpfs_fileset_info;

typedef struct {
  std::string name;
  int id;
} fileset_name;

/** This function initializes the gpfs_fileset_info struct so that it can be
 ** used with the gpfs_fcntl call **/ 
static void init_gpfs_fileset_info(gpfs_fileset_info* i){
  i->hdr.totalLength = sizeof(i->hdr) + sizeof(i->fileset) + sizeof(i->pool);
  i->hdr.fcntlVersion = GPFS_FCNTL_CURRENT_VERSION;
  i->hdr.fcntlReserved = 0;

  i->fileset.structLen = sizeof(i->fileset);
  i->fileset.structType = GPFS_FCNTL_GET_FILESETNAME;

  i->pool.structLen = sizeof(i->pool);
  i->pool.structType = GPFS_FCNTL_GET_STORAGEPOOL;
}

static 
fileset_name 
get_fileset_name(const std::string& fileset_root){

  fileset_name fs_name;
  int fd = ::open(fileset_root.c_str(),0);

  if (fd < 0){
    std::ostringstream msg;
    msg << "Error opening file '" << fileset_root << "'.";
    throw fs::system_error(msg.str(), errno);
  }

  gpfs_fileset_info i;
  init_gpfs_fileset_info(&i);

  int retval = gpfs_fcntl(fd, &i);
  if (retval){
    std::ostringstream msg;
    msg << "Error getting fileset information from file " << fileset_root;
    throw fs::system_error(msg.str(),errno);
  }

  int fileset_id;
  retval = gpfs_getfilesetid(const_cast<char*>(fileset_root.c_str()),
    i.fileset.buffer,
    &fileset_id);

  if (retval){
    std::ostringstream msg;
    msg << "Error translating fileset name '" << i.fileset.buffer << " to numerical id.";
    throw fs::system_error(msg.str(),errno);
  }

  fs_name.name = std::string(i.fileset.buffer);
  fs_name.id = fileset_id;

  return fs_name;
}

// --- module local functions --- //

/** Wrapper around gpfs_stat() call from libgpfs; throws a
 *  %system_error exception to signal an error condition.
 *
 * @param pathname pathname of the file or directory to stat
 *
 * @param output a @c{struct stat64_t} whose fields are to be filled
 * with information on @a pathname
 *
 * @return exact stat information on GPFS filesystem entry @a pathname
 *
 * @throw %system_error containing error code of failed @c gpfs_stat
 * call.
 */
static void
xgpfs_stat(const std::string& pathname, 
           stat64_t& output)
  throw(fs::error)
{
  int rc = gpfs_stat(const_cast<char*>(pathname.c_str()), &output);
  if (-1 == rc)
    {
      std::ostringstream msg;
      msg << __FILE__ 
          << ": gpfs_stat(" << pathname << ") failed";
      throw fs::system_error(msg.str(), errno);
    }
}



// --- exported functions --- //

/** Constructor, taking pathname of the filesystem mount point.
 * 
 * @throw fs::system_error if a system call fails;
 * fs::wrong_filesystem_type if given path does not point to a GPFS
 * filesystem
 */
fs::gpfs::gpfs(const std::string& mntpath) 
  throw(fs::wrong_filesystem_type, fs::error)
  : genericfs(mntpath)
{
  // check if given mountpoint is gpfs, by calling 'gpfs_stat'
  // on it, and checking if we get an error in return
  stat64_t st;
  int rc = gpfs_stat(const_cast<char*>(mntpath.c_str()), &st);
  if (-1 == rc) {
    if (EINVAL == errno) {
      std::ostringstream msg;
      msg << "No GPFS filesystem on '" << mntpath << "'";
      throw fs::wrong_filesystem_type(msg.str());
    } else { // ENOSYS, ENOENT, EPERM
      int err = errno;
      std::ostringstream msg;
      msg << __FILE__ 
          << ": gpfs_stat(" << mntpath << ") failed";
      throw system_error(msg.str(), err);
    }
  } else if (0 != rc) {
    // should not happen!
    assert (-1 == rc || 0 == rc);
  }
  std::cout << "Using GPFS v3.1 driver for: " << mntpath << std::endl;
}


/** Preallocate filesystem blocks on a inode.  The file is created, if
 * it does not exist.
 *
 * The @a size parameter indicates the number of bytes to allocate,
 * from the @em start of the file; if a file has non-zero size, then
 * only (size - actual length of file) bytes are effectively
 * preallocated.
 *
 * @param  filename  Pathname to the inode.
 * @param  size      Number of bytes to preallocate.
 *
 * @throw  fs::error, if a system call fails.
 */
void
fs::gpfs::prealloc (const std::string& filename, 
                    const alloc_size_t size) 
  throw(fs::error)
{
  int fd = open (filename.c_str(), 
                 O_WRONLY|O_CREAT|O_LARGEFILE, 
                 S_IRUSR|S_IWUSR);
  if (-1 == fd)
    {
      int err = errno;
      std::ostringstream msg;
      msg << __FILE__ 
          << ": open(" << filename 
          << ",O_WRONLY|O_CREAT|O_LARGEFILE,S_IRUSR|S_IWUSR) failed";
      throw system_error(msg.str(), err);
    }

  int rc = gpfs_prealloc (fd, (gpfs_off64_t)0, size);
  if (-1 == rc)
    {
      int err = errno;
      std::ostringstream msg;
      msg << __FILE__ 
          << ": gpfs_prealloc(" << filename << "," << size << ") failed";
      close(fd);
     throw system_error(msg.str(), err);
    }

  close(fd);
}


/**
 * Return the named file size in bytes.  If the passed path points not
 * to a regular file, then throws a @c std::logic_error exception.
 *
 * <em>Note:</em> As opposed to the <code>get_size()</code> in
 * <code>posixapi_interface</code>, this function is guaranteed to
 * return accurate data, at the cost of forcing an update of the
 * metadata in the cluster, so it may incur in a sensible performance
 * penalty.
 *
 * @param  filename  Pathname of the file whose size is to be returned.
 *
 * @return           Size of the file, or negative value if some error 
 *                   occurred.
 *
 * @throw fs::error, if a system call fails; std::logic_error if
 * argument is not a regular file.
 */
size_t
fs::gpfs::get_exact_size (const std::string& filename)
  throw(fs::error, std::logic_error)
{
  stat64_t st;
  xgpfs_stat (filename, st); /* dereference symlinks */
  
  // when debugging, stop at this point 
  // if called with a directory name
  assert(S_ISREG(st.st_mode));
  
  if (S_ISREG(st.st_mode)) 
    return st.st_size;
  else
    {
      // should not be called with a directory name,
      // throw @c std::logic_error
      std::ostringstream msg;
      msg << "gpfs::get_size(" << filename 
          << "): argument is a directory;" 
        "cannot return size of a directory.";
      throw std::logic_error(msg.str());
    }
}

size_t
fs::gpfs::get_number_of_blocks(const std::string& filename){
  
  stat64_t st;
  xgpfs_stat (filename, st);

  if (S_ISREG(st.st_mode)){
      return st.st_blocks;
  }else{

      std::ostringstream msg;
      msg << "gpfs::get_number_of_blocks(" << filename 
          << "): argument is a directory;" 
        "cannot return the number of allocated blocks for a directory.";
      throw std::logic_error(msg.str());
  }
}

fs::quota_info
fs::gpfs::get_fileset_quota_info(const std::string& fileset_root){
  
  gpfs_quotaInfo_t gpfs_quota_info;
  fs::quota_info quota_info;

  fileset_name fs_name = get_fileset_name(fileset_root);

  if (gpfs_quotactl(const_cast<char*>(fileset_root.c_str()),
      GPFS_QCMD(Q_GETQUOTA,GPFS_FILESETQUOTA),
      fs_name.id,
      &gpfs_quota_info)){
    
    std::ostringstream msg;

    if (errno == GPFS_E_NO_QUOTA_INST){

      msg << "This file system does not support quotas. Fileset root: " 
          << fileset_root;
      throw quota_not_supported(msg.str());

    }else {

      msg << "Error getting quota information out of filesystem. Fileset root: "
          << fileset_root;
      throw system_error(msg.str(),errno);

    }
  }

  quota_info.fileset_name = fs_name.name;
  quota_info.fileset_id = fs_name.id;
  quota_info.block_usage = gpfs_quota_info.blockUsage;
  quota_info.block_hard_limit = gpfs_quota_info.blockHardLimit;
  quota_info.block_soft_limit = gpfs_quota_info.blockSoftLimit;

  return quota_info;
}

bool
fs::gpfs::is_quota_enabled(const std::string& fileset_root){

  try{

    get_fileset_quota_info(fileset_root);
    
  }catch(const quota_not_supported& ex){
    return false;
  }

  return true;
}
/**
 *  Truncate the specified file to the desired size
 * @return 0 if success, -1 if error occours.
 * 
 * @throw fs::error, if the truncate system call fails.
 */ 

int
fs::gpfs::truncate_file (const std::string& filename, 
                     size_t desired_size) 
  throw(fs::error)
{
	  			
  int res = truncate (filename.c_str(), desired_size);
  //int res = truncate("/tmp/testfile", 3000);       
  if (-1 == res)
    {
  	  int err = errno;
      std::ostringstream msg;
      msg << __FILE__<< ": truncate(" << filename<<","
      << desired_size << ") failed";
      throw system_error(msg.str(), err);
    }
   
  return res;

}
/**
 * Return the last modification time (as UNIX epoch value) of the
 * passed file or directory.
 *
 * <em>Note:</em> As opposed to the <code>get_size()</code> in
 * <code>posixapi_interface</code>, this function is guaranteed to
 * return accurate data, at the cost of forcing an update of the
 * metadata in the cluster, so it may incur in a sensible performance
 * penalty.
 *
 * @param pathname Pathname of the file or directory whose last
 *                 modification time is to be returned.
 *
 * @return         Last modification time (as UNIX epoch value) or
 *                 negative value if some error occured.
 *
 * @throw  fs::error, if a system call fails.
 */
time_t
fs::gpfs::get_exact_last_modification_time (const std::string& pathname) 
  throw(fs::error)
{
  stat64_t st;
  xgpfs_stat (pathname, st); /* dereference symlinks */
  return st.st_mtime;
}

fs::fs_acl_ptr 
fs::gpfs::new_acl () const
  throw(fs::error)
{
   fs_acl_ptr p(new gpfs31_acl);
   return p;
}
