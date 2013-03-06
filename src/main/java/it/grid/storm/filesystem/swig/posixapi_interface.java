/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class posixapi_interface {
  public static int chmod(String path, int mode) {
    return posixapi_interfaceJNI.chmod(path, mode);
  }

  public static int chown(String path, int owner, int group) {
    return posixapi_interfaceJNI.chown(path, owner, group);
  }

  public static int creat(String pathname, int mode) {
    return posixapi_interfaceJNI.creat(pathname, mode);
  }

  public static int mkdir(String pathname, int mode) {
    return posixapi_interfaceJNI.mkdir(pathname, mode);
  }

  public static int setuid(int uid) {
    return posixapi_interfaceJNI.setuid(uid);
  }

  public static int unlink(String pathname) {
    return posixapi_interfaceJNI.unlink(pathname);
  }

  public static int umask(int mask) {
    return posixapi_interfaceJNI.umask(mask);
  }

  public static int geteuid() {
    return posixapi_interfaceJNI.geteuid();
  }

  public static int getuid() {
    return posixapi_interfaceJNI.getuid();
  }

}
