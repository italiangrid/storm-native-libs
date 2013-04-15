// -----------------------------------------------------------------------
// -----------------------------------------------------------------------
//
// gpfsapi_interface.i -- SWIG interface module for StoRM GPFS module
//
// Copyright (c) 2005, 2006 Riccardo Murri <riccardo.murri@ictp.it>
// for the EGRID/INFN joint project StoRM.
//
// You may copy, modify and distribute this code under the terms 
// in the StoRM LICENSE.txt file.
//
// $Id: gpfsapi_interface.i,v 1.8 2007/04/13 14:08:42 aforti Exp $
//
// -----------------------------------------------------------------------
// -----------------------------------------------------------------------

%module gpfsapi_interface
%javaconst(1); 

%include load_native_lib_in_jniclass.i
LOAD_NATIVE_LIB_IN_JNICLASS(gpfsapi_interface)

%include "types.i"
%include "storm_exceptions.i"

%{
#include "fs_errors.hpp"
%}
%import "fs_errors.hpp"

%immutable;

%import "fs_acl.hpp"

%shared_ptr(posixfs_acl)

%import "posixfs_acl.hpp"

%shared_ptr(gpfs31_acl)

%{
#include "gpfs31_acl.hpp"
%}
%include "gpfs31_acl.hpp"

%import "genericfs.hpp"
%{
#include "gpfs.hpp"
%}
%include "gpfs.hpp"
