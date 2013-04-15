BUILT_SOURCES = posixapi_interface_wrap.cxx
JAVA_PACKAGE=it.grid.storm.filesystem.swig
STUBS_DIR=$(top_builddir)/../src/main/java/it/grid/storm/filesystem/swig

if BUILD_ALL
lib_LTLIBRARIES = libposixapi_interface.la

noinst_PROGRAMS = test-posixfs

libposixapi_interface_la_CPPFLAGS=$(JNI_CFLAGS) -I$(top_builddir)/common/
libposixapi_interface_la_CXXFLAGS=-DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64 

if DEBUG
libposixapi_interface_la_CXXFLAGS+=-DDEBUG
else
libposixapi_interface_la_CXXFLAGS+=-DNDEBUG
endif

libposixapi_interface_la_SOURCES= \
    posixapi_interface_wrap.cxx \
    posixfs.cpp \
    posixfs.hpp \
    posixfs_acl.cpp \
    posixfs_acl.hpp

libposixapi_interface_la_LIBADD= $(top_builddir)/common/libstormfscommon.la $(ACL_LIBS)

test_posixfs_CPPFLAGS=-I$(top_builddir)/common/

test_posixfs_SOURCES= \
    test_posixfs.cpp 

test_posixfs_LDADD= libposixapi_interface.la $(ACL_LIBS)
endif

posixapi_interface_wrap.cxx: posixapi_interface.i
	@echo "Generating posixapi Java stubs in $(STUBS_DIR)"
	swig -c++ -java -I$(top_builddir)/common -package $(JAVA_PACKAGE) -Wall -outdir $(STUBS_DIR) -o posixapi_interface_wrap.cxx posixapi_interface.i

distclean-local:
	rm -rf Makefile.in
