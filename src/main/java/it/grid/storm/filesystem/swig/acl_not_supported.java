/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class acl_not_supported extends error {
  private long swigCPtr;

  protected acl_not_supported(long cPtr, boolean cMemoryOwn) {
    super(posixapi_interfaceJNI.acl_not_supported_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(acl_not_supported obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        posixapi_interfaceJNI.delete_acl_not_supported(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public acl_not_supported() {
    this(posixapi_interfaceJNI.new_acl_not_supported__SWIG_0(), true);
  }

  public acl_not_supported(String reason) {
    this(posixapi_interfaceJNI.new_acl_not_supported__SWIG_1(reason), true);
  }

}
