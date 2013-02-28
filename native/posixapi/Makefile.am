BUILT_SOURCES = posixapi_interface_wrap.cxx
lib_LTLIBRARIES = libposixapi_interface.la

JAVA_PACKAGE=it.grid.storm.filesystem.swig

libposixapi_interface_la_CXXFLAGS= $(JNI_CFLAGS) -DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64 -I$(top_builddir)/common/

libposixapi_interface_la_SOURCES= \
    posixapi_interface_wrap.cxx \
    posixfs.cpp \
    posixfs.hpp \
    posixfs_acl.cpp \
    posixfs_acl.hpp

libposixapi_interface_la_LIBADD= $(top_builddir)/common/libstormfscommon.la

posixapi_interface_wrap.cxx: posixapi_interface.i
	if [ ! -d build ]; then mkdir build; fi
	swig -c++ -java -I../common -package $(JAVA_PACKAGE) -Wall -outdir build -o posixapi_interface_wrap.cxx posixapi_interface.i

clean-local:
	rm -rf build
	rm -f posixapi_interface_wrap.cxx

distclean-local:
	rm -rf Makefile.in
