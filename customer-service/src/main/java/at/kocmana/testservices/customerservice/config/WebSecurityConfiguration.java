package at.kocmana.testservices.customerservice.config;

import at.kocmana.testservices.customerservice.interceptor.ApiKeyAuthenticationFilter;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

  private final AuthenticationManager authenticationManager;
  private final ApiKeyProperties apiKeyProperties;

  @Autowired
  public WebSecurityConfiguration(ApiKeyProperties apiKeyProperties) {
    this.apiKeyProperties = apiKeyProperties;
    authenticationManager = generateAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity, HttpSession httpSession) throws Exception {
    var filter = new ApiKeyAuthenticationFilter(apiKeyProperties.header());
    filter.setAuthenticationManager(authenticationManager);

    httpSecurity
        .csrf(Customizer.withDefaults())
        .sessionManagement(Customizer.withDefaults())
        .addFilter(filter)
        .authorizeRequests()
        .anyRequest().authenticated();

    return httpSecurity.build();
  }

  AuthenticationManager generateAuthenticationManager() {
    return authentication -> {
      var principal = (String) authentication.getPrincipal();
      if (!apiKeyProperties.values().contains(principal)) {
        throw new BadCredentialsException("Invalid Credentials.");
      }
      authentication.setAuthenticated(true);
      return authentication;
    };
  }

}
