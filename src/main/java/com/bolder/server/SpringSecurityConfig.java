package com.bolder.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bolder.server.auth.JWTAuthorizationFilter;
import com.bolder.server.auth.JWTLoginFilter;
import com.bolder.server.auth.JWTService;
import com.bolder.server.auth.JpaUserDetailsService;



@Configuration
@Order(1)
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@EnableWebSecurity
@CrossOrigin(origins= "*")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	BCryptPasswordEncoder  passwordEncoder;
    
	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;

	
    @Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity.ignoring().antMatchers("/css/**","/js/**","/fontawesome-free/**,/403","/images/**","/login/**","/logo","/logor","/test");
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable().authorizeRequests()
//		http.csrf().disable().authorizeRequests()
		//.antMatchers("/textalbaran").permitAll()
    	.antMatchers("/mob/auth/**","/out/**").permitAll()
		.antMatchers("/articulo/**",
					 "/cliente/**",
					 "/pedido/**",
				     "/factura/**",
				     "/cartera/**",
				     "/me/**").hasAnyAuthority("ADMIN","USER","SUPER")
		
		.antMatchers("/configuracion/**",
					 "/usuario/**",
					 "/registro/**" ).hasAnyAuthority("ADMIN","SUPER")

		.anyRequest().denyAll()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
        .addFilterBefore(new JWTLoginFilter("/auth", authenticationManager(),jwtService),
                UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new JWTLoginFilter("/mob/auth", authenticationManager(),jwtService),
                UsernamePasswordAuthenticationFilter.class)        
        // Las demás peticiones pasarán por este filtro para validar el token
        .addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtService));
		
		//http.exceptionHandling().accessDeniedPage("/403");
		
	}
	
    
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

		builder.userDetailsService(jpaUserDetailsService)
		.passwordEncoder(passwordEncoder);

	}
	
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("HEAD","GET", "POST", "PUT", "DELETE", "PATCH"));
        //configuration.setAllowedOrigins(Arrays.asList("http://*.bolder.com:*"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}




