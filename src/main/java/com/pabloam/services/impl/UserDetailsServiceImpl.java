/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 3 Mar 2016 14:54:08 Author: Pablo
 */

package com.pabloam.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Pablo
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
