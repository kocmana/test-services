package at.kocmana.testservices.productservice.config;

import java.security.SecureRandom;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class WebSecurityConfiguration {

  public static final String ADMIN_ROLE = "ADMIN";
  public static final String USER_ROLE = "USER";

  private final BasicAuthEntryPoint authenticationEntryPoint;
  private final SecurityWhitelistProperties securityWhitelistProperties;
  private final DataSource dataSource;

  @Autowired
  public WebSecurityConfiguration(BasicAuthEntryPoint authenticationEntryPoint,
                                  SecurityWhitelistProperties securityWhitelistProperties, DataSource dataSource) {
    log.warn("I got a call at security constructor.");

    this.authenticationEntryPoint = authenticationEntryPoint;
    this.securityWhitelistProperties = securityWhitelistProperties;
    this.dataSource = dataSource;
  }

  @Bean
  public UserDetailsManager users() {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    log.warn("I got a call at security.");

    httpSecurity.authorizeRequests()
        .antMatchers(HttpMethod.DELETE, "/product/**").hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, "/product/**").hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, "/product/**").hasRole(ADMIN_ROLE)
        .antMatchers("/review/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
        .antMatchers(HttpMethod.GET, "/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)

        .antMatchers("/actuator/**").permitAll()
        .antMatchers(generateConfigurationWhitelist()).permitAll()

        .anyRequest()
        .authenticated()

        .and()
        .csrf().disable()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);

    httpSecurity.csrf().disable()
        .headers().frameOptions().disable();

    return httpSecurity.build();
  }

  private String[] generateConfigurationWhitelist() {
    List<String> whitelist = securityWhitelistProperties.getWhitelist();
    return whitelist.toArray(new String[0]);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10, new SecureRandom());
  }

}
