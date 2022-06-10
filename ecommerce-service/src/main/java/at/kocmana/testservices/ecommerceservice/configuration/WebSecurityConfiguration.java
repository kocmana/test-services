package at.kocmana.testservices.ecommerceservice.configuration;

import java.security.SecureRandom;
import java.util.List;
import javax.sql.DataSource;
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
public class WebSecurityConfiguration {

  private static final String ADMIN_ROLE = "ADMIN";
  private static final String USER_ROLE = "USER";

  private final BasicAuthEntryPoint authenticationEntryPoint;
  private final SecurityWhitelistProperties securityWhitelistProperties;
  private final DataSource dataSource;

  @Autowired
  public WebSecurityConfiguration(BasicAuthEntryPoint authenticationEntryPoint,
                                  SecurityWhitelistProperties securityWhitelistProperties, DataSource dataSource) {
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
    httpSecurity.authorizeRequests()
        .antMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, "/price/**").hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, "/purchase/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
        .antMatchers(HttpMethod.GET, "/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)

        .antMatchers("/actuator/**").permitAll()
        .antMatchers(generateConfigurationWhitelist()).permitAll()

        .anyRequest()
        .authenticated()

        .and()
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
