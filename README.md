StoRM native libs
===============================

Native libraries used by the StoRM backend to access underlying file system functionality.

## Build from source

### Supported platforms

* Almalinux 9 on x86_64 architecture

### Repositories

Some of the packages needed to build storm-native-libs are in the [EPEL](http://fedoraproject.org/wiki/EPEL) repository. To enable it run

    yum install epel-release

### Building

#### Build-time dependencies

In order to build the storm native libs you will need the following packages
installed:

* autotools
* pkgconfig
* maven
* swig >= 2.0.9
* libacl-devel
* java 11
* gpfs.base >= 5.1.9


To build the native part of the library:

Build command:

    ./bootstrap
    ./configure --with-java_home=${JAVA_HOME} --enable-gpfs
    make


If the stubs interface has changed you will also need the `--eneable-regen` flag.
Stubs are committed in the repository as on SL the SWIG version is quite old
and does not support smart pointers.

To build the java part (to be built after the native part), just use:

    mvn package
