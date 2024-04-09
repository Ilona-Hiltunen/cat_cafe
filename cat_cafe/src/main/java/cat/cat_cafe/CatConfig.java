package cat.cat_cafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import cat.cat_cafe.web.AppUserService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class CatConfig {

    @Autowired
    private AppUserService userDetailService;

     private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
        new AntPathRequestMatcher("/api/**"),
        new AntPathRequestMatcher("/h2-console/**"),
        new AntPathRequestMatcher("/cats"),
        new AntPathRequestMatcher("/cat/**"),
        new AntPathRequestMatcher("/removecat/**"),
        new AntPathRequestMatcher("/new**"),
        new AntPathRequestMatcher("/bookings"),
        new AntPathRequestMatcher("/booking/**"),
        new AntPathRequestMatcher("/removebooking/**"),
        new AntPathRequestMatcher("/users"),
        new AntPathRequestMatcher("/user/**"),
        new AntPathRequestMatcher("/deleteuser/**")

     };

     //Setting authentication and enabling some sites for all
     @Bean
     public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
            authorize -> authorize
            .requestMatchers(antMatcher("/index/**")).permitAll()
            .requestMatchers(antMatcher("/catlist")).permitAll()
            .requestMatchers(antMatcher("/images/**")).permitAll()
            .requestMatchers(antMatcher("/signup")).permitAll()
            .requestMatchers(antMatcher("/savecustomer")).permitAll()
            .requestMatchers(antMatcher("/")).permitAll()
            .requestMatchers(WHITE_LIST_URLS).permitAll()
            .anyRequest().authenticated())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .formLogin(formlogin -> formlogin
            .loginPage("/login")
            .defaultSuccessUrl("/index", true)
            .permitAll())
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());

            return http.build();
     }

     @Autowired
     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
     }


    //Setting configuration for custom validation, so I can use custom messages from src/main/resources/messages.properties
    @Bean
    public MessageSource mSource() {
        ReloadableResourceBundleMessageSource mSource = new ReloadableResourceBundleMessageSource();
        mSource.setBasename("classpath:messages");
        mSource.setDefaultEncoding("UTF-8");
        return mSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(mSource());
        return bean;
        
    }
}




