
// --- exception handling --- //
// 
// This code catches the C++ exceptions and re-throws them
// as proper Java exceptions.
//
%include "exception.i"

// set base class for all exceptions thrown by this package
// (also look in fs_errors.hpp)
%typemap(javabase) fs::error                 "it.grid.storm.filesystem.FilesystemError";

// expected C++ method name to expected Java method name
%rename(getMessage) what;

// fs_errors: exception classes for this package;
// they will all inherit from fs::error, which is made into
// a Java excpetion by the commands above.
//
%{
#include "fs_errors.hpp"
%}
%include "fs_errors.hpp"

%typemap(throws, throws="it.grid.storm.filesystem.WrongFilesystemType") fs::wrong_filesystem_type {
  jclass excep = jenv->FindClass("it/grid/storm/filesystem/WrongFilesystemType");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}

%typemap(throws, throws="it.grid.storm.filesystem.AclNotSupported") fs::acl_not_supported {
  jclass excep = jenv->FindClass("it/grid/storm/filesystem/AclNotSupported");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}

%typemap(throws, throws="it.grid.storm.filesystem.InvalidPathException") fs::invalid_path {
  jclass excep = jenv->FindClass("it/grid/storm/filesystem/InvalidPathException");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}

%typemap(throws, throws="it.grid.storm.filesystem.InvalidPermissionOnFileException") fs::permission_denied {
  jclass excep = jenv->FindClass("it/grid/storm/filesystem/InvalidPathException");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}

%typemap(throws, throws="it.grid.storm.filesystem.FilesystemError") fs::error {
  jclass excep = jenv->FindClass("it/grid/storm/filesystem/FilesystemError");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}

%typemap(throws, throws="java.lang.RuntimeException") std::exception {
  jclass excep = jenv->FindClass("java/lang/RuntimeException");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}

%typemap(throws, throws="java.lang.RuntimeException") std::runtime_error {
  jclass excep = jenv->FindClass("java/lang/RuntimeException");
  if (excep)
    jenv->ThrowNew(excep, $1.what());
  return $null;
}
