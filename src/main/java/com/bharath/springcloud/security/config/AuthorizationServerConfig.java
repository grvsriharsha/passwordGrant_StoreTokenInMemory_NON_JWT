package com.bharath.springcloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;

//This takes

//This AuthorizationserverConfig should be configured with
//
//		1.The token store where we store the generated tokens.So from the same tokenstore ResourceServer can get the token and check
//
//		2.Authentication manger in OAuth2SecurityConfig,but we return the defualt authentic mananger from there(else we would have configured inmemory,JDBC auth)
//
//		3.Instead of using direct Authentication manager lets use ,UserDetail service

//        4. Where to store the clientDetails,ie the other microservices should send this details if they wanna use this app.
//
//        5. Here we used password grant.Password grant is used in case where
//             a. You are logging into directly to the webapp with its credentils ,but it doesnt want basic auth,but the ouath token.
//        6. ResourceId which connects the Authorizationserver and ResourceServer

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String RESOURCE_ID = "couponservice";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

//	@Value("${keyFile}")
//	private String keyFile;
//	@Value("${password}")
//	private String password;
//	@Value("${alias}")
//	private String alias;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(tokenStore()).accessTokenConverter(jwtAccessTokenConverter())
//				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
		endpoints.tokenStore(new InMemoryTokenStore())
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("couponclientapp").secret(passwordEncoder.encode("9999"))
				.authorizedGrantTypes("password", "refresh_token").scopes("read", "write").resourceIds(RESOURCE_ID);
		;
	}
	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.tokenKeyAccess("permitAll()");
//	}
//
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(jwtAccessTokenConverter());
//	}
//
//	@Bean
//	public JwtAccessTokenConverter jwtAccessTokenConverter() {
//		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyFile),
//				password.toCharArray());
//		KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias);
//		jwtAccessTokenConverter.setKeyPair(keyPair);
//		return jwtAccessTokenConverter;
//
//	}

}
