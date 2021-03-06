/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class gpfs31_acl extends posixfs_acl {

  private long swigCPtr;
  private boolean swigCMemOwnDerived;

  protected gpfs31_acl(long cPtr, boolean cMemoryOwn) {

    super(gpfsapi_interfaceJNI.gpfs31_acl_SWIGSmartPtrUpcast(cPtr), true);
    swigCMemOwnDerived = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(gpfs31_acl obj) {

    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {

    delete();
  }

  public synchronized void delete() {

    if (swigCPtr != 0) {
      if (swigCMemOwnDerived) {
        swigCMemOwnDerived = false;
        gpfsapi_interfaceJNI.delete_gpfs31_acl(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public gpfs31_acl() {

    this(gpfsapi_interfaceJNI.new_gpfs31_acl(), true);
  }

  public void load(String path, boolean delete_permission_too)
    throws it.grid.storm.filesystem.FilesystemError,
    it.grid.storm.filesystem.InvalidPathException,
    it.grid.storm.filesystem.InvalidPermissionOnFileException,
    it.grid.storm.filesystem.AclNotSupported, java.lang.RuntimeException {

    gpfsapi_interfaceJNI.gpfs31_acl_load__SWIG_0(swigCPtr, this, path,
      delete_permission_too);
  }

  public void load(String path)
    throws it.grid.storm.filesystem.FilesystemError,
    it.grid.storm.filesystem.InvalidPathException,
    it.grid.storm.filesystem.InvalidPermissionOnFileException,
    it.grid.storm.filesystem.AclNotSupported, java.lang.RuntimeException {

    gpfsapi_interfaceJNI.gpfs31_acl_load__SWIG_1(swigCPtr, this, path);
  }

  public void enforce(String path)
    throws it.grid.storm.filesystem.FilesystemError,
    it.grid.storm.filesystem.InvalidPathException,
    it.grid.storm.filesystem.InvalidPermissionOnFileException,
    it.grid.storm.filesystem.AclNotSupported, java.lang.RuntimeException {

    gpfsapi_interfaceJNI.gpfs31_acl_enforce(swigCPtr, this, path);
  }

}
