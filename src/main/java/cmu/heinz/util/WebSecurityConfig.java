package util;

import cmu.heinz.util.Authenticate;
import javax.naming.directory.DirContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements AuthenticationProvider {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        auth
                .inMemoryAuthentication()
                .withUser("001").password("123").roles("USER");
        auth
                .inMemoryAuthentication()
                .withUser("002").password("123").roles("USER");
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        DirContext ctx = Authenticate.authenticateUser(a.getName(), a.getCredentials().toString());
        if(ctx == null){
            throw new BadCredentialsException("PLease check your credentials");
        }
        
        return new UsernamePasswordAuthenticationToken(a.getName(), a.getCredentials().toString());
    }

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
