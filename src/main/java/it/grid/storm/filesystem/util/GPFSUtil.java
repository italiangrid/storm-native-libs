package it.grid.storm.filesystem.util;

import java.io.FileNotFoundException;

import it.grid.storm.filesystem.swig.gpfs;
import it.grid.storm.filesystem.swig.quota_info;

public class GPFSUtil extends BaseFSUtil {

	public GPFSUtil(String[] args) throws FileNotFoundException {
		
		if (args.length < 2){
			System.err.println("usage: GPFSUtil mountpoint command [args]+");
			System.exit(1);
		}
		
		String[] shiftedArgs = new String[args.length-1];
		System.arraycopy(args, 1, shiftedArgs, 0, shiftedArgs.length);
		
		fs = new gpfs(args[0]);
		executeCommand(shiftedArgs);
	}
	
	
	void printQuotaInfo(String filesetRoot){
		gpfs localGPFS = (gpfs) fs;
		
		if ( localGPFS.is_quota_enabled(filesetRoot)){
		
			quota_info i = localGPFS.get_fileset_quota_info(filesetRoot);
			
			System.out.format("Fileset name: %s Fileset id: %d\n", 
					i.getFileset_name(),
					i.getFileset_id());
			
			System.out.format("Usage: %d Hard limit: %d, Soft limit: %d\n",
					i.getBlock_usage(),
					i.getBlock_hard_limit(),
					i.getBlock_soft_limit());
		}else {
			
			System.out.println("Quota is not enabled on "+filesetRoot);
			
		}
	}
	
	@Override
	protected void executeCommand(String[] args) throws FileNotFoundException {
		String cmd = args[0].toUpperCase().replace("-", "_");
		
		if (cmd.equals("PRINT_QUOTA_INFO")){
			argsLengthCheck(args, 2, "print-quota-info <fileset_root>");
			fileSanityChecks(args[1]);
			printQuotaInfo(args[1]);
		}else
			super.executeCommand(args);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		new GPFSUtil(args);

	}

}
