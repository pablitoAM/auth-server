/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 3 Mar 2016 16:11:59 Author: Pablo
 */

package com.pabloam.services;

import com.pabloam.model.User;

/**
 * @author Pablo
 *
 */
public interface UserRepository<ID> {

	/**
	 * Returns the user with the given userId if any.
	 * 
	 * @param userId
	 * @return
	 */
	User findUserById(ID userId);

	/**
	 * Returns the user with the given login if any.
	 * 
	 * @param login
	 * @return
	 */
	User findUserByLogin(String login);

}
