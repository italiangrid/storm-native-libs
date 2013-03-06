//
// mapping for common types on POSIX systems; <sys/types.h> cannot be
// parsed by SWIG because of gcc-specific constructs...
//

// map these to a *signed* int, otherwise Java will
// use a 64-bit type...
typedef int uid_t;
typedef int gid_t;
typedef int mode_t;

// these ought to be 64-bit indeed
typedef unsigned int off_t;
typedef unsigned int size_t;
typedef unsigned int time_t;

// from #include <sys/acl.h>
typedef int acl_perm_t; 

// map std::string to Java String
%include "std_string.i"

#define SWIG_SHARED_PTR_SUBNAMESPACE tr1
%include <std_shared_ptr.i>
%shared_ptr(fs_acl)

%include "pass_vector_by_value_java.i"
JAVA_STD_VECTOR(gid_t, jint, Int, Int)

%include "enumtypeunsafe.swg"
