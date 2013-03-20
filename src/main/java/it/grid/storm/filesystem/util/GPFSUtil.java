package it.grid.storm.filesystem.util;

import java.io.FileNotFoundException;

import it.grid.storm.filesystem.swig.gpfs;

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
	
	public static void main(String[] args) throws FileNotFoundException {
		new GPFSUtil(args);

	}

}
