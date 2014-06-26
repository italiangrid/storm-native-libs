/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class gpfs extends genericfs {
  private long swigCPtr;

  protected gpfs(long cPtr, boolean cMemoryOwn) {
    super(gpfsapi_interfaceJNI.gpfs_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(gpfs obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gpfsapi_interfaceJNI.delete_gpfs(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public gpfs(String mntpath) throws it.grid.storm.filesystem.WrongFilesystemType, it.grid.storm.filesystem.FilesystemError {
    this(gpfsapi_interfaceJNI.new_gpfs(mntpath), true);
  }

  public void prealloc(String filename, long size) throws it.grid.storm.filesystem.FilesystemError {
    gpfsapi_interfaceJNI.gpfs_prealloc(swigCPtr, this, filename, size);
  }

  public long get_exact_size(String filename) throws it.grid.storm.filesystem.FilesystemError {
    return gpfsapi_interfaceJNI.gpfs_get_exact_size(swigCPtr, this, filename);
  }

  public long get_exact_last_modification_time(String pathname) throws it.grid.storm.filesystem.FilesystemError {
    return gpfsapi_interfaceJNI.gpfs_get_exact_last_modification_time(swigCPtr, this, pathname);
  }

  public int truncate_file(String filename, long desired_size) throws it.grid.storm.filesystem.FilesystemError {
    return gpfsapi_interfaceJNI.gpfs_truncate_file(swigCPtr, this, filename, desired_size);
  }

  public long get_number_of_blocks(String filename) throws it.grid.storm.filesystem.FilesystemError {
    return gpfsapi_interfaceJNI.gpfs_get_number_of_blocks(swigCPtr, this, filename);
  }

  public boolean is_quota_enabled(String fileset_root) throws it.grid.storm.filesystem.FilesystemError {
    return gpfsapi_interfaceJNI.gpfs_is_quota_enabled(swigCPtr, this, fileset_root);
  }

  public quota_info get_fileset_quota_info(String fileset_root) throws it.grid.storm.filesystem.FilesystemError {
    return new quota_info(gpfsapi_interfaceJNI.gpfs_get_fileset_quota_info(swigCPtr, this, fileset_root), true);
  }

  public fs_acl new_acl() throws it.grid.storm.filesystem.FilesystemError {
    long cPtr = gpfsapi_interfaceJNI.gpfs_new_acl(swigCPtr, this);
    return (cPtr == 0) ? null : new fs_acl(cPtr, true);
  }

  public boolean is_file_on_disk(String filename) throws it.grid.storm.filesystem.FilesystemError {
    return gpfsapi_interfaceJNI.gpfs_is_file_on_disk(swigCPtr, this, filename);
  }

}
