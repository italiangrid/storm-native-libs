%module storm_xattrs
%include <std_string.i>

%include "exception.i"
%include "string_vector.i"

%pragma(java) jniclasscode=%{
  static {
    try {
        System.loadLibrary("storm-xattrs");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}

// Exception handling

%exception {
  try {
    $action
  }
  SWIG_CATCH_STDEXCEPT // catch std::exception
  catch (...) {

    SWIG_exception(SWIG_UnknownError, "Unknown exception");
  }
}

%{
#include "extended_attrs.hpp"
%}

%include "extended_attrs.hpp"
