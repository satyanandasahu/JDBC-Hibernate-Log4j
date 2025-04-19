package com.example.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * To test log4j2
 */
public class MainLog {

	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
	//	for (int i = 0; i < 1000; i++) {
			logger.trace("1.This is a TRACE message.");
			logger.debug("2.This is a DEBUG message.");
			logger.info("3.This is an INFO message.");
			logger.warn("4.This is a WARN message.");
			logger.error("5.This is an ERROR message.");
	//	}
	}
}
