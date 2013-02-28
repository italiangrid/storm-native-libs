lib_LTLIBRARIES= libstorm_cutil.la

libstorm_cutil_la_CFLAGS= -DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64 -I$(top_builddir)/gpfsmock
libstorm_cutil_la_LIBADD= $(top_builddir)/gpfsmock/libgpfs.la
libstorm_cutil_la_SOURCES= storm_libcutil.c

distclean-local:
	rm -rf Makefile.in
