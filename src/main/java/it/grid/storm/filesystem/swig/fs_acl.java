/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package it.grid.storm.filesystem.swig;

public class fs_acl {

  private long swigCPtr;
  private boolean swigCMemOwnBase;

  protected fs_acl(long cPtr, boolean cMemoryOwn) {

    swigCMemOwnBase = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(fs_acl obj) {

    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {

    delete();
  }

  public synchronized void delete() {

    if (swigCPtr != 0) {
      if (swigCMemOwnBase) {
        swigCMemOwnBase = false;
        posixapi_interfaceJNI.delete_fs_acl(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public boolean access(int mode, int uid, int[] gid) {

    return posixapi_interfaceJNI.fs_acl_access(swigCPtr, this, mode, uid, gid);
  }

  public void clear() {

    posixapi_interfaceJNI.fs_acl_clear(swigCPtr, this);
  }

  public void load(String pathname, boolean delete_also)
    throws it.grid.storm.filesystem.FilesystemError, java.lang.RuntimeException {

    posixapi_interfaceJNI.fs_acl_load__SWIG_0(swigCPtr, this, pathname,
      delete_also);
  }

  public void load(String pathname)
    throws it.grid.storm.filesystem.FilesystemError, java.lang.RuntimeException {

    posixapi_interfaceJNI.fs_acl_load__SWIG_1(swigCPtr, this, pathname);
  }

  public void enforce(String pathname)
    throws it.grid.storm.filesystem.FilesystemError, java.lang.RuntimeException {

    posixapi_interfaceJNI.fs_acl_enforce(swigCPtr, this, pathname);
  }

  public int get_group_owner_gid() {

    return posixapi_interfaceJNI.fs_acl_get_group_owner_gid(swigCPtr, this);
  }

  public int get_group_owner_perm() {

    return posixapi_interfaceJNI.fs_acl_get_group_owner_perm(swigCPtr, this);
  }

  public int get_mask() {

    return posixapi_interfaceJNI.fs_acl_get_mask(swigCPtr, this);
  }

  public int get_other_perm() {

    return posixapi_interfaceJNI.fs_acl_get_other_perm(swigCPtr, this);
  }

  public int get_owner_perm() {

    return posixapi_interfaceJNI.fs_acl_get_owner_perm(swigCPtr, this);
  }

  public int get_owner_uid() {

    return posixapi_interfaceJNI.fs_acl_get_owner_uid(swigCPtr, this);
  }

  public boolean has_group_perm(int gid) {

    return posixapi_interfaceJNI.fs_acl_has_group_perm(swigCPtr, this, gid);
  }

  public boolean has_user_perm(int uid) {

    return posixapi_interfaceJNI.fs_acl_has_user_perm(swigCPtr, this, uid);
  }

  public boolean has_explicit_mask() {

    return posixapi_interfaceJNI.fs_acl_has_explicit_mask(swigCPtr, this);
  }

  public boolean is_group_perm_masked(int gid) {

    return posixapi_interfaceJNI.fs_acl_is_group_perm_masked(swigCPtr, this,
      gid);
  }

  public boolean is_group_owner(int gid) {

    return posixapi_interfaceJNI.fs_acl_is_group_owner(swigCPtr, this, gid);
  }

  public boolean is_owner(int uid) {

    return posixapi_interfaceJNI.fs_acl_is_owner(swigCPtr, this, uid);
  }

  public static boolean is_permission_subset(int subset, int superset) {

    return posixapi_interfaceJNI.fs_acl_is_permission_subset(subset, superset);
  }

  public boolean is_user_perm_masked(int uid) {

    return posixapi_interfaceJNI
      .fs_acl_is_user_perm_masked(swigCPtr, this, uid);
  }

  public int get_group_effective_perm(int gid) {

    return posixapi_interfaceJNI.fs_acl_get_group_effective_perm(swigCPtr,
      this, gid);
  }

  public int get_group_perm(int gid) {

    return posixapi_interfaceJNI.fs_acl_get_group_perm(swigCPtr, this, gid);
  }

  public int get_user_effective_perm(int uid) {

    return posixapi_interfaceJNI.fs_acl_get_user_effective_perm(swigCPtr, this,
      uid);
  }

  public int get_user_perm(int uid) {

    return posixapi_interfaceJNI.fs_acl_get_user_perm(swigCPtr, this, uid);
  }

  public int grant_group_owner_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_group_owner_perm(swigCPtr, this,
      perm);
  }

  public int grant_group_perm(int gid, int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_group_perm(swigCPtr, this, gid,
      perm);
  }

  public int grant_group_perm_not_owner(int gid, int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_group_perm_not_owner(swigCPtr,
      this, gid, perm);
  }

  public int grant_other_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_other_perm(swigCPtr, this, perm);
  }

  public int grant_owner_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_owner_perm(swigCPtr, this, perm);
  }

  public int grant_user_perm(int uid, int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_user_perm(swigCPtr, this, uid,
      perm);
  }

  public int grant_user_perm_not_owner(int uid, int perm) {

    return posixapi_interfaceJNI.fs_acl_grant_user_perm_not_owner(swigCPtr,
      this, uid, perm);
  }

  public boolean has_extended_acl() {

    return posixapi_interfaceJNI.fs_acl_has_extended_acl(swigCPtr, this);
  }

  public int remove_group_perm_not_owner(int gid) {

    return posixapi_interfaceJNI.fs_acl_remove_group_perm_not_owner(swigCPtr,
      this, gid);
  }

  public int remove_user_perm_not_owner(int uid) {

    return posixapi_interfaceJNI.fs_acl_remove_user_perm_not_owner(swigCPtr,
      this, uid);
  }

  public int revoke_group_owner_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_revoke_group_owner_perm(swigCPtr, this,
      perm);
  }

  public int revoke_group_perm(int gid, int perm) {

    return posixapi_interfaceJNI.fs_acl_revoke_group_perm(swigCPtr, this, gid,
      perm);
  }

  public int revoke_group_perm_not_owner(int gid, int perm) {

    return posixapi_interfaceJNI.fs_acl_revoke_group_perm_not_owner(swigCPtr,
      this, gid, perm);
  }

  public int revoke_other_perm(int mask) {

    return posixapi_interfaceJNI.fs_acl_revoke_other_perm(swigCPtr, this, mask);
  }

  public int revoke_owner_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_revoke_owner_perm(swigCPtr, this, perm);
  }

  public int revoke_user_perm(int uid, int perm) {

    return posixapi_interfaceJNI.fs_acl_revoke_user_perm(swigCPtr, this, uid,
      perm);
  }

  public int revoke_user_perm_not_owner(int uid, int perm) {

    return posixapi_interfaceJNI.fs_acl_revoke_user_perm_not_owner(swigCPtr,
      this, uid, perm);
  }

  public int set_group_owner_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_set_group_owner_perm(swigCPtr, this,
      perm);
  }

  public int set_group_perm(int gid, int perm) {

    return posixapi_interfaceJNI.fs_acl_set_group_perm(swigCPtr, this, gid,
      perm);
  }

  public int set_group_perm_not_owner(int gid, int perm) {

    return posixapi_interfaceJNI.fs_acl_set_group_perm_not_owner(swigCPtr,
      this, gid, perm);
  }

  public int set_mask(int new_mask) {

    return posixapi_interfaceJNI.fs_acl_set_mask(swigCPtr, this, new_mask);
  }

  public int set_other_perm(int new_other_perm) {

    return posixapi_interfaceJNI.fs_acl_set_other_perm(swigCPtr, this,
      new_other_perm);
  }

  public int set_owner_perm(int perm) {

    return posixapi_interfaceJNI.fs_acl_set_owner_perm(swigCPtr, this, perm);
  }

  public int set_user_perm(int uid, int perm) {

    return posixapi_interfaceJNI
      .fs_acl_set_user_perm(swigCPtr, this, uid, perm);
  }

  public int set_user_perm_not_owner(int uid, int perm) {

    return posixapi_interfaceJNI.fs_acl_set_user_perm_not_owner(swigCPtr, this,
      uid, perm);
  }

  public long size() {

    return posixapi_interfaceJNI.fs_acl_size(swigCPtr, this);
  }

  public int[] get_uid_list() {

    return posixapi_interfaceJNI.fs_acl_get_uid_list(swigCPtr, this);
  }

  public long get_uid_list_size() {

    return posixapi_interfaceJNI.fs_acl_get_uid_list_size(swigCPtr, this);
  }

  public int[] get_gid_list() {

    return posixapi_interfaceJNI.fs_acl_get_gid_list(swigCPtr, this);
  }

  public final static class permission_flags {

    public final static int PERM_NONE = 0;
    public final static int PERM_EXECUTE = (1 << 0);
    public final static int PERM_WRITE_DATA = (1 << 1);
    public final static int PERM_READ_DATA = (1 << 2);
    public final static int PERM_WRITE_ACL = (1 << 3);
    public final static int PERM_READ_ACL = (1 << 4);
    public final static int PERM_DELETE = (1 << 5);
    public final static int PERM_TRAVERSE_DIRECTORY = (1 << 0);
    public final static int PERM_LIST_DIRECTORY = (1 << 2);
    public final static int PERM_CREATE_SUBDIRECTORY = (1 << 6);
    public final static int PERM_CREATE_FILE = (1 << 7);
    public final static int PERM_DELETE_CHILD = (1 << 8);
    public final static int PERM_ALL = PERM_EXECUTE | PERM_WRITE_DATA
      | PERM_READ_DATA | PERM_WRITE_ACL | PERM_READ_ACL | PERM_DELETE
      | PERM_TRAVERSE_DIRECTORY | PERM_LIST_DIRECTORY
      | PERM_CREATE_SUBDIRECTORY | PERM_CREATE_FILE | PERM_DELETE_CHILD;
  }

}
