/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class quota_not_supported extends error {

  private long swigCPtr;

  protected quota_not_supported(long cPtr, boolean cMemoryOwn) {

    super(gpfsapi_interfaceJNI.quota_not_supported_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(quota_not_supported obj) {

    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {

    delete();
  }

  public synchronized void delete() {

    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gpfsapi_interfaceJNI.delete_quota_not_supported(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public quota_not_supported(String reason) {

    this(gpfsapi_interfaceJNI.new_quota_not_supported(reason), true);
  }

}
