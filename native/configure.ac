AC_INIT([storm-native-libs], [1.0.2], andrea.ceccanti@cnaf.infn.it)
AC_PREFIX_DEFAULT([/usr])

AC_CONFIG_AUX_DIR([./aux])
AC_CONFIG_MACRO_DIR([m4])
AC_CONFIG_SRCDIR(common/genericfs.hpp)
AM_INIT_AUTOMAKE

AC_COPYRIGHT([Copyright (c) 2004-2013 Istituto Nazionale di Fisica Nucleare 
See LICENCE file for details
])

AC_ARG_WITH(jni_location,
	[  --with-jni_location=JNI_LOCATION	Sets the location where jni.h.],
	[with_jni_location="$withval"],
	[])

AC_ARG_WITH(java_home,
    [  --with-java_home=JAVA_HOME    Sets the java home. It is needed to find jni.h],
    [with_java_home="$withval"],
	[])

AC_ARG_WITH(gpfs_prefix,
    [  --with-gpfs_prefix=GPFS_HOME  Sets where GPFS libraries and includes will be searched for. Default is /usr/lpp/mmfs.],
    [with_gpfs_prefix="$withval"],
    [with_gpfs_prefix="/usr/lpp/mmfs"])

if test x"$with_jni_location" = x; then
	if test x"$with_java_home" = x; then
		AC_MSG_ERROR([Please set Java home with the --with-java_home option, or the jni.h location with the --with-jni_location option])
	fi
fi

if test -n "$with_jni_location"; then
	JNI_CFLAGS="-I$with_jni_location"
fi

if test -n "$with_java_home"; then
	JNI_CFLAGS="-I$with_java_home/include -I$with_java_home/include/linux"
fi

AC_ARG_ENABLE([gpfs],
    [ --enable-gpfs    Builds GPFS drivers ],
    [ case "$enableval" in
        yes) build_gpfs="yes" ;;
        no) build_gpfs="no" ;;
        *)  AC_MSG_ERROR([bad value ${enableval} for --enable-gpfs. Accepted values: yes, no.]) ;;
      esac],
    [build_gpfs="no"])


AC_ARG_ENABLE([regen],
    [  --enable-regen  Regenerate C++ and Java SWIG stubs ],
    [ case "$enableval" in
        yes) force_regen="yes" ;;
        no)  force_regen="no" ;;
        *)  AC_MSG_ERROR([bad value ${enableval} for --enable-regen. Accepted values: yes, no.]) ;;
      esac],
    [force_regen="no"])

AM_CONDITIONAL([BUILD_GPFS], [test x"$build_gpfs" = x"yes"])

AM_CONDITIONAL([FORCE_REGEN], [test x"$force_regen" = x"yes"])

if test x"$force_regen" = x"yes"; then
    AC_MSG_NOTICE([Removing committed SWIG C++ stubs as --enable-regen forces SWIG stubs generation.])
    ## Remove C++ Swig Stubs
    rm -f posixapi/*_wrap.cxx
    rm -f gpfsapi/*_wrap.cxx
    rm -f xattrs/*_wrap.cxx

    AC_CHECK_PROG(SWIG,swig,["yes"],["no"])

    if test x"$SWIG" = xno; then
	    AC_MSG_ERROR(Cannot find swig.)
    fi
fi

## Limit build to Java stubs, i.e. don't compile native libraries
AC_ARG_ENABLE([stubs],
    [  --enable-stubs   Builds only java library stubs ],
    [ case "$enableval" in
        yes) stubs_only="yes" ;;
        no)  stubs_only="no" ;;
        *)  AC_MSG_ERROR([bad value ${enableval} for --enable-stubs. Accepted values: yes, no.]) ;;
      esac],
    [stubs_only="no"])

AM_CONDITIONAL([BUILD_ALL], [test x"$stubs_only" = x"no"])

if test x"$stubs_only" = x"yes"; then
    AC_MSG_NOTICE([Building only java stubs as requested.])
fi

AC_ARG_ENABLE([debug],
   [  --enable-debug    Enable debug information ],
   [ case "$enableval" in
        yes) debug_enabled="yes" ;;
        no) debug_enabled="no" ;;
        *) AC_MSG_ERROR([bad value ${enableval} for --enable-debug. Accepted values: yes, no.]) ;;
    esac],
    [debug_enabled="no"])

AM_CONDITIONAL([DEBUG], [ test x"$debug_enabled" = x"yes" ])

AC_PROG_CXX
AC_PROG_CC
AC_PROG_LIBTOOL
AC_PROG_INSTALL

## CHECK JNI HEADERS
CPPFLAGS_SAVE=$CPPFLAGS
CPPFLAGS=$JNI_CFLAGS
AC_CHECK_HEADER([jni.h], [have_jni=yes], [have_jni=no], [])
CPPFLAGS=$CPPFLAGS_SAVE

if test x$have_jni = xno; then
    AC_MSG_FAILURE([jni.h not found.])
fi

if test x"$stubs_only" = x"no"; then

## Check libattr-devel
AC_CHECK_HEADER([attr/xattr.h], 
    [], 
    [AC_MSG_ERROR("Could not find xattr.h. Please install the libattr-devel package.")],
    [])

AC_CHECK_LIB([attr],
     [getxattr],
     [ATTR_LIBS=-lattr],
     [AC_MSG_ERROR("Error checking libattr. Please install the libacl-devel package.")]
     )

## Check libacl-devel
AC_CHECK_HEADER([sys/acl.h], 
    [], 
    [AC_MSG_ERROR("Could not find acl.h. Please install the libacl-devel package.")],
    [])

AC_CHECK_HEADER([acl/libacl.h], 
    [], 
    [AC_MSG_ERROR("Could not find libacl.h. Please install the libacl-devel package.")],
    [])

AC_CHECK_LIB([acl],
    [acl_create_entry],
    [ACL_LIBS=-lacl],
    [AC_MSG_ERROR("Error checking libacl. Please install the libacl-devel package.")]
    )


AC_CHECK_HEADER([lcmaps/lcmaps.h],
    [],
    [AC_MSG_ERROR("Could not find lcmaps.h. Please install the lcmaps-interface package.")],
    [])

## Check lcmaps-without-gsi
PKG_CHECK_MODULES([LCMAPS],[lcmaps-return-poolindex-without-gsi])

if test "x$build_gpfs" = "xyes"; then
    GPFS_CFLAGS="-I$with_gpfs_prefix/include"
    GPFS_LIBS="-L$with_gpfs_prefix/lib -lgpfs"

    CPPFLAGS_SAVE=$CPPFLAGS
    CPPFLAGS=$GPFS_CFLAGS
    AC_CHECK_HEADER([gpfs.h], [ : ], [AC_MSG_ERROR("Error checking gpfs headers. Install the gpfs development packages on this machine.")],[])
    CPPFLAGS=$CPPFLAGS_SAVE

    CPPFLAGS_SAVE=$CPPFLAGS
    CPPFLAGS=$GPFS_LIBS
    AC_CHECK_LIB([gpfs],[gpfs_stat],[ : ],[AC_MSG_ERROR("Error checking gpfs library. Install the gpfs development packages on this machine.")],[])
    CPPFLAGS=$CPPFLAGS_SAVE

    AC_SUBST(GPFS_CFLAGS)
    AC_SUBST(GPFS_LIBS)
fi

fi

AC_SUBST(JNI_CFLAGS)
AC_SUBST(ATTR_LIBS)
AC_SUBST(ACL_LIBS)

AC_CONFIG_FILES([Makefile 
                 common/Makefile
                 posixapi/Makefile
                 xattrs/Makefile
                 gpfsapi/Makefile
                 lcmapsutil/Makefile])
AC_OUTPUT
