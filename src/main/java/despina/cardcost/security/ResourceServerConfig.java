package despina.cardcost.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    //a class to define access control for the protected resource that the API exposes

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .mvcMatcher("/payment-cards-cost/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
               /* .mvcMatchers("/messages/**").access("hasAuthority('SCOPE_message.read')")*/
                .and()
                .oauth2ResourceServer()
                .jwt();
    }

}