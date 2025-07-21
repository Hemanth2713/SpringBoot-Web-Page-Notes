package com.practice.SecurityUsingBycrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // This annotation is used to enable web security in the application.
// In Spring 6, certain things have changed in how Spring Security is implemented.
// When we want to customize the security configuration, we need to return an object of SecurityFilterChain.
// This means we need to create a bean that will return a SecurityFilterChain object.
public class SecurityConfig {
    @Autowired
    private  UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

















    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean // By default, Spring will create the object for you if you are not defining it explicitly.
    /* Spring Security, behind the scenes, works with the Spring Security Filter Chain for the filters in between.
     Now, we are overriding this default configuration, so we need to specify our own configuration. By default,
     Spring Security uses HTTP Security for managing the security filters. To do this, we need to pass an object
     of HTTP Security and configure it as needed.*/
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        //Since we have changed the security here by default, if you dont do this, spring will takecare of it,spring will say ok, there are alot of default configuraion available
        //So every time a user try to acccess a particular resource m we'll check for certain filters. if the user is logged in is enabled
        //But now we are doing now is we are sating we have got the HTTP object and we are saying,let me specify what i want, and hem you have specified nothing there
//
//        //Before we create the security object we have to make some changes
//        security.csrf(customizer->customizer.disable());//By doing this we are disabling the CSRF
//        //If we disable the CSRF and we run the application. You can see that there's still  username and password login form
//        //To enable username and password we have to use authorize
//        security.authorizeHttpRequests(request->request.anyRequest().authenticated());
//        //We are making that we are enabling the security for the request.
//        //We have to provide the form for login
//        //security.formLogin(Customizer.withDefaults());//We don't need form login when we have a stateless
//        security.httpBasic(Customizer.withDefaults());
//        //So what i want to also achieve is we want to make sure that you get a new session. So basically  I wanted to make it stateless.
//        security.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//


        //Instead of writing the above format we can use the below format
       security.csrf(customizer->customizer.disable())
                .authorizeHttpRequests(request->request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return security.build();
    }


    //These user details service are work with the static values
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin= User
//                                .withDefaultPasswordEncoder()
//                                .username("hemanth")
//                                .password("hemanth123")
//                                .roles("admin")
//                                .build();
//        UserDetails user= User
//                .withDefaultPasswordEncoder()
//                .username("sai")
//                .password("sai123")
//                .roles("user")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
}
