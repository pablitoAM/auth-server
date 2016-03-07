/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 7 Mar 2016 11:47:23 Author: Pablo
 */

package com.pabloam.oauth2.repository;

import org.bson.Document;

/**
 * @author Pablo
 *
 */
public interface UserRepository {

	Document findUserByUsername(String username);

}
