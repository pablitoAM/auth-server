/**
 * Copyright (c) 2016 Molenaar Strategie BV.
 * Created: 3 Mar 2016 09:33:45 Author: Pablo
 */

package com.pabloam.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author Pablo
 *
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

	// ===============================
	// Properties
	// ===============================

	@Value("keystore.password:secret")
	private String keystorePassword;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	//
	@Autowired
	private UserDetailsService userDetailsService;

	// ===============================
	// Beans
	// ===============================

	/**
	 * JWT Access token converter bean
	 * 
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// @formatter:off
		KeyPair keyPair = new KeyStoreKeyFactory(
				new ClassPathResource("keystore.jks"), keystorePassword.toCharArray()
		).getKeyPair("test");
		// @formatter:on
		converter.setKeyPair(keyPair);
		return converter;
	}

	// ===============================
	// Implementations
	// ===============================

	/**
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer)
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// @formatter:off
			endpoints				
				.authenticationManager(null)
				.accessTokenConverter(jwtAccessTokenConverter())
				.userDetailsService(userDetailsService);
		// @formatter:on
	}

	/**
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// @formatter:off		
		clients
			.inMemory()
        		.withClient("client-with-registered-redirect")
        		.authorizedGrantTypes("authorization_code")
        		.authorities("ROLE_CLIENT")
        		.scopes("read", "trust")
        		.resourceIds(RESOURCE_ID)
        		.redirectUris("http://anywhere?key=value")
        		.secret("secret123")
        	.and()
        		.withClient("my-client-with-secret")
        		.authorizedGrantTypes("client_credentials", "refresh_token")
        		.authorities("USER", "ADMIN")
        		.scopes("read")
        		.resourceIds(RESOURCE_ID)
        		.secret("secret");
        // @formatter:on

		// clients
		// .inMemory()
		// .withClient("clientapp")
		// .authorizedGrantTypes("password", "refresh_token")
		// .authorities("USER")
		// .scopes("read", "write")
		// .resourceIds(RESOURCE_ID)
		// .secret("123456");
		// @formatter:on
	}

	/**
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer)
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}
