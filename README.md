StoRM native libs
===============================

Native libraries used by the StoRM backend to access underlying file system functionality.

## Build from source

### Supported platforms
* Scientific Linux 5 on x86_64 architecture
* Scientific Linux 6 on x86_64 architecture

### Repositories

Some of the packages needed to build storm-native-libs are in the [EPEL](http://fedoraproject.org/wiki/EPEL) 
repository. To enable it run

```bash
yum install epel-release
```

### Building

You need git to get the code, and ant (libtool, automake, autoconf) and gcc and gcc-c++ to build it.

The other dependcies are

* lcmaps-without-gsi-devel

Build command:
```bash
./bootstrap
./configure --with-java_home=%{java_home} --enable-gpfs
make
```
