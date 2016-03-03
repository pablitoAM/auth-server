/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 3 Mar 2016 17:05:01 Author: Pablo
 */

package com.pabloam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Pablo
 *
 */
public class SecuirtyConfiguration extends WebSecurityConfigurerAdapter {

	// ===============================
	// Properties
	// ===============================

	@Autowired
	private UserDetailsService userDetailsService;

	// ===============================
	// Implementation
	// ===============================m

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
