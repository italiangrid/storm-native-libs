BUILT_SOURCES = storm_xattrs_wrap.cxx
JAVA_PACKAGE=it.grid.storm.filesystem.swig
STUBS_DIR=$(top_builddir)/../src/main/java/it/grid/storm/filesystem/swig

if BUILD_ALL
lib_LTLIBRARIES = libstormxattrs.la

libstormxattrs_la_CPPFLAGS = $(JNI_CFLAGS)

if DEBUG
libstormxattrs_la_CXXFLAFS =  -DDEBUG
else
libstormxattrs_la_CXXFLAFS =  -DNDEBUG
endif

libstormxattrs_la_SOURCES= \
    storm_xattrs_wrap.cxx \
    extended_attrs.cpp \
    extended_attrs.hpp
endif


storm_xattrs_wrap.cxx: storm_xattrs.i
	@echo "Generating stormxattrs Java stubs in $(STUBS_DIR)"
	swig -c++ -java -package $(JAVA_PACKAGE) -Wall -Wextra -outdir $(STUBS_DIR) -o storm_xattrs_wrap.cxx storm_xattrs.i
	
distclean-local:
	rm -rf Makefile.in
