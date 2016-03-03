/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 3 Mar 2016 09:30:33 Author: Pablo
 */

package com.pabloam;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Pablo
 *
 */
@SpringBootApplication
public class Application {

	// The logger
	final static Logger logger = (Logger) LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws IOException {

		logger.info("Working Directory: {}", System.getProperty("user.dir"));
		for (String string : args) {
			logger.info("Received Arg: {}", string);
		}
		SpringApplication.run(Application.class, args);
	}

}
