package com.jochoa.market.web.security;

import com.jochoa.market.domain.service.MarketUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(marketUserDetailsService); //Le decimos a spring que nosostros manejamos la autencicación, dando nuestro usuario y contraseña.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll() //Las peticiones que terminen en authenticate las permite
            .anyRequest().authenticated()//Las demas no
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Con esto indicamos que nuestra sessión será sin sessión, ya que los jwt los validara

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class); //Indicamos que es un filtro de usuario y contraseña
    }

    @Override//Esto se hace para que sea spring el que siga controlando la gestión de autenticación.
    @Bean // No solo es para que lo use, si no para que sepa que explicitamente lo estamos eligiendo como gestion de autenticación.
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
