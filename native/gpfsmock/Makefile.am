lib_LTLIBRARIES = libgpfs.la

libgpfs_la_CXXFLAGS= -DHAVE_STDIO_FILEBUF -D_LARGEFILE_SOURCE -D_FILE_OFFSET_BITS=64

libgpfs_la_SOURCES= \
    libgpfs.c \
    gpfs.h

distclean-local:
	rm -rf Makefile.in
