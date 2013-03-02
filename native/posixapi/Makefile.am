BUILT_SOURCES = posixapi_interface_wrap.cxx
JAVA_PACKAGE=it.grid.storm.filesystem.swig
STUBS_DIR=$(top_builddir)/../src/main/java/it/grid/storm/filesystem/swig

if BUILD_ALL
lib_LTLIBRARIES = libposixapi_interface.la

libposixapi_interface_la_CPPFLAGS=$(JNI_CFLAGS) -I$(top_builddir)/common/
libposixapi_interface_la_CXXFLAGS=-DDEBUG -DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64 

libposixapi_interface_la_SOURCES= \
    posixapi_interface_wrap.cxx \
    posixfs.cpp \
    posixfs.hpp \
    posixfs_acl.cpp \
    posixfs_acl.hpp

libposixapi_interface_la_LIBADD= $(top_builddir)/common/libstormfscommon.la
endif

posixapi_interface_wrap.cxx: posixapi_interface.i 
	@echo "Generating posixapi Java stubs in $(STUBS_DIR)"
	if [ ! -d $(STUBS_DIR) ]; then mkdir -p $(STUBS_DIR); fi
	swig -c++ -java -I../common -package $(JAVA_PACKAGE) -Wall -outdir $(STUBS_DIR) -o posixapi_interface_wrap.cxx posixapi_interface.i

clean-local:
	rm -rf build
	rm -f posixapi_interface_wrap.cxx

distclean-local:
	rm -rf Makefile.in
