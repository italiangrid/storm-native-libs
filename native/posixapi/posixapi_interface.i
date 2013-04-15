// -----------------------------------------------------------------------
// -----------------------------------------------------------------------
//
// posixapi_interface.i -- SWIG interface module for posixapi_interface.c
//
// Copyright (c) 2005, 2006 Riccardo Murri <riccardo.murri@ictp.it>
// for the EGRID/INFN joint project StoRM.
//
// You may copy, modify and distribute this code under the terms 
// in the StoRM LICENSE.txt file.
//
// $Id: posixapi_interface.i,v 1.16 2006/05/26 09:44:06 amessina Exp $
//
// -----------------------------------------------------------------------
// -----------------------------------------------------------------------

%module posixapi_interface

%include <std_string.i>
%javaconst(1); 


// ---- load native code in JNI class initialization ---- //
//
// This pragma will instruct SWIG code to load
// the native code library when the JNI-proxy class
// is loaded by the JVM.
//
%include load_native_lib_in_jniclass.i
LOAD_NATIVE_LIB_IN_JNICLASS(posixapi_interface)

%include "types.i"

// Include exception handling code and C++ -> Java exception
// typemaps
%include "storm_exceptions.i"

// all wrapped objects are read-only, from the Java point of view
%immutable;

// useful standard POSIX system calls from libc
//

%{
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
%}

int chmod(const char *path, mode_t mode);
int chown(const char *path, uid_t owner, gid_t group);
int creat(const char *pathname, mode_t mode);
int mkdir(const char *pathname, mode_t mode);
int setuid(uid_t uid);
int unlink(const char *pathname);

mode_t umask(mode_t mask);

uid_t geteuid(void);	
uid_t getuid(void);


// fs_acl
%{
#include "fs_acl.hpp"
%}
%include "fs_acl.hpp"

// generic_fs 
%{
#include "genericfs.hpp"
%}
%include "genericfs.hpp"

// posixfs
//
%import "genericfs.hpp"
%{
#include "posixfs.hpp"
%}
%include "posixfs.hpp"

%shared_ptr(posixfs_acl)

%{
#include "posixfs_acl.hpp"
%}
%include "posixfs_acl.hpp"

// User and groups utils
%{
#include "users_and_groups.hpp"
%}
%include "users_and_groups.hpp"
