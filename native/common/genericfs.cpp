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
 * @file   genericfs.cpp
 * @author Riccardo Murri <riccardo.murri@ictp.it>
 *
 * Implementation of the fs::genericfs class.
 */

/// CVS Revision info
const char* const RCSID="$Id: genericfs.cpp,v 1.10 2007/04/17 10:30:16 lmagnoni Exp $";


#include "fs_errors.hpp"
#include "genericfs.hpp"
#include "util.hpp"

#include <cassert>
#include <cerrno>
#include <sstream>
#include <cstdlib>
#include <sys/stat.h>
#include <sys/statvfs.h>
#include <unistd.h>
#include <grp.h>

/* POSIX.1e ACL support */
#include <sys/types.h>
#include <acl/libacl.h>



/** Constructor, taking path to the filesystem mount point.  Checks
 * that ACL support is enabled on the specified directory tree, and
 * throws a fs::acl_not_supported exception if it's not.  It does so
 * by trying to get the ACL of the supplied path, and checking if the
 * return code is ENOTSUP or EINVAL.
 *
 * @param  mntpath  Pathname of the filesystem mount point.
 *
 * @throw  fs::error, if a system call fails.
 */
fs::genericfs::genericfs(const std::string& mntpath)
  throw(fs::acl_not_supported, fs::error)
  : mountpoint(mntpath)
{
    ; // empty
}


/**
 * Return the named file size in bytes.  If the passed path points to
 * a directory, the return value is undefined.
 *
 * @param path  Pathname of the file whose size is to be returned.
 *
 * @return Size of the file, in bytes.  If @c path points to a
 * directory, then the result is undefined.
 *
 * @throw fs::error, if a system call fails; std::logic_error if
 * argument is not a regular file.
 */
size_t
fs::genericfs::get_size (const std::string& path)
  throw(fs::error, std::logic_error)
{
  struct stat64 st;
  xstat(path, st);

  // when debugging, stop at this point
  // if called with a directory name
  assert(S_ISREG(st.st_mode));

  if (S_ISREG(st.st_mode))
    // return file size
    return st.st_size;
  else
    // should not be called with a directory name,
    // throw @c std::logic_error
    {
      std::ostringstream msg;
      msg << "get_size(" << path
          << "): argument is a directory;"
        "cannot return size of a directory.";
      throw std::logic_error(msg.str());
    }
}

/**
* Returns the number of blocks allocated to a file passed as
* argument.

* @param path the file for which blocks are to be returned
* @return     the number of blocks allocated to the file
*
* @throw logic:error, if the file is not a regular file
**/
size_t
fs::genericfs::get_number_of_blocks(const std::string& path){

   struct stat64 st;
   xstat(path,st);

   if (S_ISREG(st.st_mode)){
       return st.st_blocks;
   }else {

      std::ostringstream msg;
      msg << "get_number_of_blocks(" << path
          << "): argument is a directory;"
        "cannot return allocated blocks for a directory.";
      throw std::logic_error(msg.str());

   }
}


void
fs::genericfs::change_group_ownership(const std::string& filename,
    const std::string& groupname)
    throw(fs::error){

    struct group *gr = getgrnam(groupname.c_str());

    if (gr == 0){
        std::ostringstream msg;
        msg << "change_group_ownership("
            << filename
            << ","
            << groupname << "): groupname was not found.";

        throw std::logic_error(msg.str());
    }

    if (chown(filename.c_str(), -1, gr->gr_gid)){
        int err = errno;
        std::ostringstream msg;
        msg << "Error setting file '" << filename
            << "' group ownership to group '"
            << groupname << "(" << gr->gr_gid << ")" << "'.";

        throw system_error(msg.str(), err);
    }
}
/**
 * Return the last modification time (as UNIX epoch value) of the
 * passed file or directory.
 *
 * @param filename Pathname of the file or directory whose last
 *                 modification time is to be returned.
 *
 * @return         Last modification time (as UNIX epoch value).
 *
 * @throw  fs::error, if a system call fails.
 */
time_t
fs::genericfs::get_last_modification_time (const std::string& path)
  throw(fs::error)
{
  struct stat64 st;
  xstat(path, st);
  return st.st_mtime;
}

/**
 *  Truncate the specified file to the desired size
 * @return 0 if success, -1 if error occours.
 *
 * @throw fs::error, if the truncate system call fails.
 */
int
fs::genericfs::truncate_file (const std::string& filename, size_t desired_size)
throw(fs::error)
{
  int res =  truncate (filename.c_str(),
      desired_size);

  if (-1 == res)
  {
    int err = errno;
    std::ostringstream msg;
    msg << __FILE__
      << ": truncate(" << filename<<"," << desired_size << ") failed";
    throw system_error(msg.str(), err);
  }
}


/** Return size of the available space on this filesystem (in bytes).
 *
 * @return Size of the available space in bytes.
 *
 * @throw  fs::error, if a system call fails.
 */
size_t
fs::genericfs::get_free_space()
  throw(fs::error)
{
  struct statvfs st;
  xstatvfs (mountpoint, st);
  return st.f_bavail*st.f_bsize;
}


bool
fs::genericfs::is_file_on_disk(const std::string& filename)
  throw(fs::error)
{
  struct stat64 st;
  xstat(filename, st);
  return true;
}
