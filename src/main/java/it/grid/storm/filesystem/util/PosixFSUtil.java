package it.grid.storm.filesystem.util;

import java.io.File;
import java.io.FileNotFoundException;


import it.grid.storm.filesystem.swig.fs_acl;
import it.grid.storm.filesystem.swig.posixapi_interface;
import it.grid.storm.filesystem.swig.posixfs;
import static it.grid.storm.filesystem.swig.fs_acl.permission_flags.*;

public class PosixFSUtil {

	String file;
	posixfs fs;
	
	public static enum Command{
		GET_FREE_SPACE,
		GET_SIZE,
		GET_EXACT_SIZE,
		GET_LAST_MODIFICATION_TIME,
		GET_EXACT_LAST_MODIFICATION_TIME,
		TRUNCATE,
		PRINT_ACL,
		SET_ACL;
	}
	
	private void fileSanityChecks(String fileName) throws FileNotFoundException{
		File f = new File(fileName);
		
		if (!f.exists()){
			throw new FileNotFoundException(fileName);
		}
	}
	
	private long longSanityChecks(String longStr){
		Long l = Long.parseLong(longStr);
		return l;
	}
	
	private void argsLengthCheck(String[] args, int lenght, String message){
		if (args.length < lenght){	
			System.err.format("Usage: PosixFSUtil %s", message);
			System.exit(1);
		}
	}
	
	private boolean test(int bits, int permission){
		
		String binaryString = Integer.toBinaryString(bits);
		int result = bits & permission;
		
		return (result == permission);
	}
	
	private String permissionString(int bits){
		
		if ( test(bits, PERM_ALL))
			return "ALL";
		
		StringBuilder str = new StringBuilder();
		if ( test(bits, PERM_READ_DATA))
			str.append("r");
		else
			str.append("-");
		
		if ( test(bits, PERM_WRITE_DATA) )
			str.append("w");
		else
			str.append("-");
		
		if ( test(bits, PERM_EXECUTE) )
			str.append("x");
		else
			str.append("-");
		
		return str.toString();	
	}
	
	
	private void printACL(String file){
		
		fs_acl acl = fs.new_acl();
		acl.load(file);
		
		int ownerUid = acl.get_owner_uid();
		int groupOwnerId = acl.get_group_owner_gid();
		
		String ownerName = posixapi_interface.username_from_uid(ownerUid);
		String groupName = posixapi_interface.groupname_from_gid(groupOwnerId);
		
		String ownerPerms = permissionString(acl.get_owner_perm());
		String groupOwnerPerms = permissionString(acl.get_group_owner_perm());
		String otherPerms = permissionString(acl.get_other_perm());
		
		System.out.format("# file: %s\n# owner: %s\n# group: %s\n", 
				file,
				ownerName,
				groupName);
		
		
		System.out.format("user::%s\n", ownerPerms);
		
		
		for (int uid: acl.get_uid_list()){
			
			int perms = acl.get_user_perm(uid);
			String name = posixapi_interface.username_from_uid(uid);
			System.out.format("user:%s:%s\n", 
					name, 
					permissionString(perms));
		}
		
		System.out.format("group::%s\n", groupOwnerPerms);
		
		for (int gid: acl.get_gid_list()){
			int perms = acl.get_group_perm(gid);
			String name = posixapi_interface.groupname_from_gid(gid);
			System.out.format("group:%s:%s\n", 
					name, 
					permissionString(perms));
			
		}
		
		if (acl.get_uid_list().length > 0 || acl.get_gid_list().length > 0){
			String maskPermString  = permissionString(acl.get_mask());
			if (!maskPermString.equals("")){
				System.out.format("mask::%s\n", maskPermString);
			}
		}
		
		System.out.format("other:::%s\n", otherPerms);
		
	}
	
	
	private void executeCommand(String[] args) throws FileNotFoundException{
		String cmd = args[0].toUpperCase().replace("-", "_");
		Command c = Command.valueOf(cmd);
		
		switch(c){
		
		case GET_FREE_SPACE:
			System.out.format("free space: %d\n", fs.get_free_space());
			break;
		case GET_SIZE:
			argsLengthCheck(args, 2, "get-size <filename>");
			fileSanityChecks(args[1]);
			System.out.format("file size: %d\n", fs.get_size(args[1]));
			break;
		case GET_LAST_MODIFICATION_TIME:
			argsLengthCheck(args, 2, "get-last-modification-time <filename>");
			fileSanityChecks(args[1]);
			System.out.format("file last modification time: %d\n", fs.get_last_modification_time(args[1]));
			break;
			
		case TRUNCATE:
			argsLengthCheck(args, 3, "truncate <filename> <size>");
			fileSanityChecks(args[1]);
			fs.truncate_file(args[1], longSanityChecks(args[2]));
			System.out.format("file %s truncate to size: %s\n", args[1], args[2]);
			break;
			
		case PRINT_ACL:
			argsLengthCheck(args, 2, "print-acl <filename>");
			fileSanityChecks(args[1]);
			printACL(args[1]);
			break;
		
		default:
			throw new IllegalArgumentException("Unsupported command! "+args[0]);
		}
		
		System.exit(0);
		
	}
	public PosixFSUtil(String[] args) throws FileNotFoundException {
		
		if (args.length < 1){
			System.err.println("usage: PosixFSUtil command [args]+");
			System.exit(1);
		}
					
		fs = new posixfs("/");
		
		executeCommand(args);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		new PosixFSUtil(args);
	}
}
