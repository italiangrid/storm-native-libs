/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class genericfs {

  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected genericfs(long cPtr, boolean cMemoryOwn) {

    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(genericfs obj) {

    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {

    delete();
  }

  public synchronized void delete() {

    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        posixapi_interfaceJNI.delete_genericfs(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public long get_free_space() throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_get_free_space(swigCPtr, this);
  }

  public long get_number_of_blocks(String filename) {

    return posixapi_interfaceJNI.genericfs_get_number_of_blocks(swigCPtr, this,
      filename);
  }

  public long get_size(String filename)
    throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_get_size(swigCPtr, this, filename);
  }

  public long get_last_modification_time(String pathname)
    throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_get_last_modification_time(swigCPtr,
      this, pathname);
  }

  public long get_exact_size(String filename)
    throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_get_exact_size(swigCPtr, this,
      filename);
  }

  public long get_exact_last_modification_time(String pathname)
    throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_get_exact_last_modification_time(
      swigCPtr, this, pathname);
  }

  public int truncate_file(String filename, long desired_size)
    throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_truncate_file(swigCPtr, this,
      filename, desired_size);
  }

  public boolean is_file_on_disk(String filename)
    throws it.grid.storm.filesystem.FilesystemError {

    return posixapi_interfaceJNI.genericfs_is_file_on_disk(swigCPtr, this,
      filename);
  }

  public void change_group_ownership(String filename, String groupname)
    throws it.grid.storm.filesystem.FilesystemError {

    posixapi_interfaceJNI.genericfs_change_group_ownership(swigCPtr, this,
      filename, groupname);
  }

  public fs_acl new_acl() throws it.grid.storm.filesystem.FilesystemError {

    long cPtr = posixapi_interfaceJNI.genericfs_new_acl(swigCPtr, this);
    return (cPtr == 0) ? null : new fs_acl(cPtr, true);
  }

}
