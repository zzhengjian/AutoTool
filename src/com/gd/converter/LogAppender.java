package com.gd.converter;

import java.io.ByteArrayOutputStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.util.StatusPrinter;

public class LogAppender {

	
	public void init(){
		// Destination stream
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		// Get LoggerContext from SLF4J
		LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();

		// Encoder
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(context);
		encoder.setPattern("%d{HH:mm:ss} %-5level %logger{36} - %msg%n");
		encoder.start();

		// OutputStreamAppender
		OutputStreamAppender<ILoggingEvent> appender= new OutputStreamAppender<>();
		appender.setName( "OutputStream Appender" );
		appender.setContext(context);
		appender.setEncoder(encoder);
		appender.setOutputStream(stream);
		
		
		appender.start();

		Logger log = context.getLogger(this.getClass());
		log.addAppender(appender);

		log.info( "text from logger");

		// Output to stdout logback status
		StatusPrinter.print(context);
	}
	
}
