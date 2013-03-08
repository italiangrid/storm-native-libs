/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class StringVector extends java.util.AbstractList<String> {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected StringVector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(StringVector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        storm_xattrsJNI.delete_StringVector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

    public String get (int idx) {
        return get_impl(idx);
    }
    
    public int size(){
        return (int) size_impl();
    }

    public String set(int idx, String s){

        String old = get_impl(idx);
        set_impl(idx,s);
        return old;
    }

  public StringVector() {
    this(storm_xattrsJNI.new_StringVector__SWIG_0(), true);
  }

  public StringVector(long n) {
    this(storm_xattrsJNI.new_StringVector__SWIG_1(n), true);
  }

  public long size_impl() {
    return storm_xattrsJNI.StringVector_size_impl(swigCPtr, this);
  }

  public long capacity() {
    return storm_xattrsJNI.StringVector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    storm_xattrsJNI.StringVector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return storm_xattrsJNI.StringVector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    storm_xattrsJNI.StringVector_clear(swigCPtr, this);
  }

  public void push_back(String x) {
    storm_xattrsJNI.StringVector_push_back(swigCPtr, this, x);
  }

  public String get_impl(int i) {
    return storm_xattrsJNI.StringVector_get_impl(swigCPtr, this, i);
  }

  public void set_impl(int i, String val) {
    storm_xattrsJNI.StringVector_set_impl(swigCPtr, this, i, val);
  }

}