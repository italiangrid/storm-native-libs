lib_LTLIBRARIES= libstorm_lcmaps.la

libstorm_lcmaps_la_CFLAGS= -DLCMAPS_RETURN_POOLINDEX_WITHOUT_GSI_C $(LCMAPS_CFLAGS)
libstorm_lcmaps_la_LIBADD= $(LCMAPS_LIBS)
libstorm_lcmaps_la_SOURCES= storm_lcmaps.c storm_lcmaps.h

distclean-local:
	rm -rf Makefile.in
