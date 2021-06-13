//package com.bharath.springcloud.security.config;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import com.bharath.springcloud.security.UserDetailsServiceImpl;
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	UserDetailsServiceImpl userDetailsService;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.httpBasic();
//		http.authorizeRequests()
//				.mvcMatchers(HttpMethod.GET, "/couponapi/coupons")
//				.hasAnyRole("USER", "ADMIN")
//				.mvcMatchers(HttpMethod.POST, "/couponapi/coupons")
//				.hasAnyRole("ADMIN").and().csrf().disable();
//
//	}
//
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
////	@Override
////	@Bean
////	public AuthenticationManager authenticationManagerBean() throws Exception {
////		return super.authenticationManagerBean();
////	}
//
//}
