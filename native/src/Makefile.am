BUILT_SOURCES = posixapi_interface_wrap.cxx
lib_LTLIBRARIES = libposixapi_interface.la

POSIX_INTERFACE= \
	posixapi_interface.i \
	pass_vector_by_value_java.i

JAVA_PACKAGE=it.grid.storm.filesystem.swig

libposixapi_interface_la_SOURCES= \
    posixapi_interface_wrap.cxx \
    posixfs.cpp \
    posixfs.hpp \
    genericfs.cpp \
    genericfs.hpp \
    fs_acl.cpp \
    fs_acl.hpp \
    posixfs_acl.cpp \
    posixfs_acl.hpp \
    fs_errors.cpp \
    fs_errors.hpp \
    util.cpp \
    util.hpp

posixapi_interface_wrap.cxx: posixapi_interface.i
	if [ ! -d build ]; then mkdir build; fi
	swig -c++ -java -package $(JAVA_PACKAGE) -Wall -outdir build -o posixapi_interface_wrap.cxx posixapi_interface.i
