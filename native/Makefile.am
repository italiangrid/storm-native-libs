ACLOCAL_AMFLAGS=-I m4

if BUILD_ALL
SUBDIRS = common posixapi gpfsmock gpfsapi cutil lcmapsutil
else
SUBDIRS = posixapi gpfsapi
endif

distclean-local:
	rm -f configure aclocal.m4 
	rm -f m4/libtool.m4 m4/ltoptions.m4 m4/ltsugar.m4 m4/ltversion.m4 m4/lt~obsolete.m4
	rm -rf autom4te.cache aux
	rm -rf Makefile.in 
