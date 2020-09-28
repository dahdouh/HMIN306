package com.supanadit.restsuite.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogWriter {
	
	private static PrintWriter fileWriter;
	
	public static void writeLog() {
		fileWriter.close();
	}
	
	public static void out(String nameMethod1, String nameMethod2) {
		try {
            PrintWriter writer = getWriter();
            writer.write(nameMethod1 +"->"+ nameMethod2 + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            ShutdownHookLog shutdownHook = new ShutdownHookLog();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
}
