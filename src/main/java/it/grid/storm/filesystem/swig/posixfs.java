/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class posixfs extends genericfs {

  private long swigCPtr;

  protected posixfs(long cPtr, boolean cMemoryOwn) {

    super(posixapi_interfaceJNI.posixfs_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(posixfs obj) {

    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {

    delete();
  }

  public synchronized void delete() {

    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        posixapi_interfaceJNI.delete_posixfs(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public posixfs(String mntpath)
    throws it.grid.storm.filesystem.AclNotSupported,
    it.grid.storm.filesystem.FilesystemError {

    this(posixapi_interfaceJNI.new_posixfs(mntpath), true);
  }

  public fs_acl new_acl() throws it.grid.storm.filesystem.FilesystemError {

    long cPtr = posixapi_interfaceJNI.posixfs_new_acl(swigCPtr, this);
    return (cPtr == 0) ? null : new fs_acl(cPtr, true);
  }

}
