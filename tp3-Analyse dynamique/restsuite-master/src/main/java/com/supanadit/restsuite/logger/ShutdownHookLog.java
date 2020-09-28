package com.supanadit.restsuite.logger;

public class ShutdownHookLog extends Thread{
	@Override
	public void run() {
        LogWriter.writeLog();
    }
}
