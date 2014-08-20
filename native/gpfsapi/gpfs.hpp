/**
 * @file   gpfs.hpp
 * @author Riccardo Murri <riccardo.murri@ictp.it>
 *
 * Interface for the fs::gpfs class.
 *
 */
/*
 * Copyright (c) 2005, 2006 Riccardo Murri <riccardo.murri@ictp.it>
 * for the EGRID/INFN joint project StoRM.
 *
 * You may copy, modify and distribute this file under the terms
 * contained in the StoRM backend license file LICENSE.txt
 *
 * Documentation for these functions is in the gpfsapi_interface.cpp
 * file.
 */
#ifndef __GPFS_H
#define __GPFS_H

#include "genericfs.hpp"
#include "gpfs31_acl.hpp"
#include "fs_errors.hpp"

#include <string>
#include <stdexcept>
#include <sys/types.h>
#include <gpfs.h>


namespace fs {

  typedef struct gpfs_fileset_quota_info {
    /** The fileset name **/
    std::string fileset_name;

    /** The fileset id **/
    int fileset_id;

    /** The used blocks **/
    long long block_usage;

    /** The quota hard limit **/
    long long block_hard_limit;

    /** The quota soft limit **/
    long long block_soft_limit;

  } quota_info;

  /** Interface to GPFS-specific functions. */
  class gpfs : public fs::genericfs {
  public:
    /** Constructor, taking pathname of the filesystem mount point. */
    gpfs(const std::string& mntpath)
      throw(fs::wrong_filesystem_type, fs::error);

    /** Preallocate filesystem blocks on a inode.  The file is created, if
        it does not exist. */
    virtual void prealloc (const std::string& filename, const alloc_size_t size)
      throw(fs::error);

    /** Return the named file size in bytes; up-to-date information is
        returned, at the cost of forcing a cluster-wide flushing of
        metadata. */
    virtual size_t get_exact_size (const std::string& filename)
      throw(fs::error, std::logic_error);

    /** Return the last modification time (as UNIX epoch value) of the
        passed file or directory; up-to-date information is returned, at
        the cost of forcing a cluster-wide flushing of metadata. */
    virtual time_t get_exact_last_modification_time (const std::string& pathname)
      throw(fs::error);

	  /** Truncate the specified file to the desired size in bytes.
        On success, zero is returned. On error, -1 is returned, and errno is set appropriately.  */
    virtual int truncate_file(const std::string&  filename, size_t desired_size)
      throw(fs::error);

    /** Returns the number of blocks allocated to the file. **/
    virtual size_t get_number_of_blocks(const std::string& filename)
      throw(fs::error, std::logic_error);

    /** Checks whether quota is enabled on the fileset rooted at fileset_root **/
    bool is_quota_enabled(const std::string& fileset_root)
      throw(fs::error);

    /** Returns quota information for the fileset root at fileset_root **/
    quota_info get_fileset_quota_info(const std::string& fileset_root)
      throw(fs::error);

    virtual fs_acl_ptr new_acl() const
      throw(fs::error);

    virtual bool is_file_on_disk(const std::string& filename)
      throw(fs::error);

    virtual bool is_file_on_tape(const std::string& filename)
      throw(fs::error);

  }; // class gpfs
}; // namespace fs


#endif // #ifndef __GPFS_H
