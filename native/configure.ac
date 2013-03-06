AC_INIT([storm-native-libs], [1.0.0], andrea.ceccanti@cnaf.infn.it)
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

AC_ARG_ENABLE([regen],
    [  --enable-regen  Regenerate C++ and Java SWIG stubs ],
    [ case "$enableval" in
        yes) force_regen="yes" ;;
        no)  force_regen="no" ;;
        *)  AC_MSG_ERROR([bad value ${enableval} for --enable-regen. Accepted values: yes, no.]) ;;
      esac],
    [force_regen="no"])

AM_CONDITIONAL([FORCE_REGEN], [test x"$force_regen" = x"yes"])

if test x"$force_regen" = x"yes"; then
    AC_MSG_NOTICE([Removing committed SWIG C++ stubs as --enable-regen forces SWIG stubs generation.])
    ## Remove C++ Swig Stubs
    rm -f posixapi/*_wrap.cxx
    rm -f gpfsapi/*_wrap.cxx

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
    [],
    [AC_MSG_ERROR("Error checking libacl. Please install the libacl-devel package.")]
    )


AC_CHECK_HEADER([lcmaps/lcmaps.h],
    [],
    [AC_MSG_ERROR("Could not find lcmaps.h. Please install the lcmaps-interface package.")],
    [])

## Check lcmaps-without-gsi
PKG_CHECK_MODULES([LCMAPS],[lcmaps-return-poolindex-without-gsi])

fi

AC_SUBST(JNI_CFLAGS)

AC_CONFIG_FILES([Makefile 
                 common/Makefile
                 posixapi/Makefile
                 gpfsmock/Makefile
                 gpfsapi/Makefile
                 cutil/Makefile
                 lcmapsutil/Makefile])
AC_OUTPUT
