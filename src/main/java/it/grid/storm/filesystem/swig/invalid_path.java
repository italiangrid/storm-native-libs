/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class invalid_path extends error {
  private long swigCPtr;

  protected invalid_path(long cPtr, boolean cMemoryOwn) {
    super(gpfsapi_interfaceJNI.invalid_path_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(invalid_path obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gpfsapi_interfaceJNI.delete_invalid_path(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public invalid_path(String reason) {
    this(gpfsapi_interfaceJNI.new_invalid_path(reason), true);
  }

}
