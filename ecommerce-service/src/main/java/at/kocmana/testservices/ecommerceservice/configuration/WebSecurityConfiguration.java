package at.kocmana.testservices.ecommerceservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.List;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    private final SecurityWhitelistProperties securityWhitelistProperties;
    private final DataSource dataSource;

    @Autowired
    public WebSecurityConfiguration(SecurityWhitelistProperties securityWhitelistProperties, DataSource dataSource) {
        this.securityWhitelistProperties = securityWhitelistProperties;
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsManager users() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.POST, "/price/**").hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.POST, "/purchase/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                        .requestMatchers(HttpMethod.GET, "/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)

                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(generateConfigurationWhitelist()).permitAll()

                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(Customizer.withDefaults());

        return httpSecurity.build();
    }

    private String[] generateConfigurationWhitelist() {
        List<String> whitelist = securityWhitelistProperties.whitelist();
        return whitelist.toArray(new String[0]);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

}
