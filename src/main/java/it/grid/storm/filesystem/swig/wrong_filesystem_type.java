/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class wrong_filesystem_type extends error {

  private long swigCPtr;

  protected wrong_filesystem_type(long cPtr, boolean cMemoryOwn) {

    super(gpfsapi_interfaceJNI.wrong_filesystem_type_SWIGUpcast(cPtr),
      cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(wrong_filesystem_type obj) {

    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {

    delete();
  }

  public synchronized void delete() {

    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gpfsapi_interfaceJNI.delete_wrong_filesystem_type(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public wrong_filesystem_type(String reason) {

    this(gpfsapi_interfaceJNI.new_wrong_filesystem_type(reason), true);
  }

}
