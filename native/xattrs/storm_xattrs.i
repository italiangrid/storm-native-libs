%module storm_xattrs
%include <std_string.i>
// %include <std_vector.i>

%include "string_vector.i"

%pragma(java) jniclasscode=%{
  static {
    try {
        System.loadLibrary("storm_xattrs");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load. \n" + e);
      System.exit(1);
    }
  }
%}

%{
#include "extended_attrs.hpp"
%}

%include "extended_attrs.hpp"
