BUILT_SOURCES = storm_xattrs_wrap.cxx
JAVA_PACKAGE=it.grid.storm.filesystem.swig
STUBS_DIR=$(top_builddir)/../src/main/java/it/grid/storm/filesystem/swig

if BUILD_ALL
lib_LTLIBRARIES = libstorm-xattrs.la
noinst_PROGRAMS = test-xattrs

libstorm_xattrs_la_CPPFLAGS = $(JNI_CFLAGS)

if DEBUG
libstorm_xattrs_la_CXXFLAFS =  -DDEBUG
else
libstorm_xattrs_la_CXXFLAFS =  -DNDEBUG
endif

libstorm_xattrs_la_SOURCES= \
    storm_xattrs_wrap.cxx \
    extended_attrs.cpp \
    extended_attrs.hpp

test_xattrs_SOURCES = \
    test_xattrs.cpp

test_xattrs_LDADD = libstorm-xattrs.la
endif


storm_xattrs_wrap.cxx: storm_xattrs.i
	@echo "Generating stormxattrs Java stubs in $(STUBS_DIR)"
	swig -c++ -java -package $(JAVA_PACKAGE) -Wall -Wextra -outdir $(STUBS_DIR) -o storm_xattrs_wrap.cxx storm_xattrs.i
	
distclean-local:
	rm -rf Makefile.in
