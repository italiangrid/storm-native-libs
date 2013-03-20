package it.grid.storm.filesystem.util;

import it.grid.storm.filesystem.swig.posixfs;

import java.io.FileNotFoundException;

public class PosixFSUtil extends BaseFSUtil{
	
	public PosixFSUtil(String[] args) throws FileNotFoundException {
		
		if (args.length < 2){
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
