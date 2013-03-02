noinst_LTLIBRARIES = libstormfscommon.la

libstormfscommon_la_CXXFLAGS = -DDEBUG -DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64
libstormfscommon_la_SOURCES= \
	fs_acl.cpp \
	fs_errors.cpp \
	genericfs.cpp \
	path.cpp \
	sigchld.cpp \
	string_to_number.cpp \
	users_and_groups.cpp \
	util.cpp \
	fs_acl.hpp \
	fs_errors.hpp \
	genericfs.hpp \
	path.hpp \
	split2.hpp \
	string_to_number.hpp \
	users_and_groups.hpp \
	util.hpp \
    helper_command.cpp \
    helper_command.hpp
	
distclean-local:
	rm -rf Makefile.in
