BUILT_SOURCES = gpfsapi_interface_wrap.cxx
lib_LTLIBRARIES = libgpfsapi_interface.la

JAVA_PACKAGE=it.grid.storm.filesystem.swig

libgpfsapi_interface_la_CXXFLAGS= $(JNI_CFLAGS) -DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64 -I$(top_builddir)/common -I$(top_builddir)/gpfsmock -I$(top_builddir)/posixapi
libgpfsapi_interface_la_LIBADD= $(top_builddir)/gpfsmock/libgpfs.la $(top_builddir)/common/libstormfscommon.la

libgpfsapi_interface_la_SOURCES= \
	gpfs23_acl.hpp \
	gpfs23.hpp \
	gpfs31_acl.hpp \
	gpfs.hpp \
	gpfs23_acl.cpp \
	gpfs23.cpp \
	gpfs31_acl.cpp \
	gpfs.cpp
	
gpfsapi_interface_wrap.cxx: gpfsapi_interface.i
	if [ ! -d build ]; then mkdir build; fi
	swig -c++ -java -I$(top_builddir)/posixapi -I$(top_builddir)/common -package $(JAVA_PACKAGE) -Wall -outdir build -o gpfsapi_interface_wrap.cxx gpfsapi_interface.i

clean-local:
	rm -rf build
	rm -f gpfsapi_interface_wrap.cxx

distclean-local:
	rm -rf Makefile.in
