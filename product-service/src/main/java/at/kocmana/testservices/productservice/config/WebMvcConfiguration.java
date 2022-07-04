package at.kocmana.testservices.productservice.config;

import at.kocmana.testservice.commons.requestid.RequestIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration {

  private final RequestIdInterceptor<?> requestIdInterceptor;

  @Autowired
  public WebMvcConfiguration(RequestIdInterceptor<?> requestIdInterceptor) {
    this.requestIdInterceptor = requestIdInterceptor;
  }


  @Bean
  WebMvcConfigurer webMvcConfigurerInterceptors() {

    return new WebMvcConfigurer() {
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIdInterceptor);
      }
    };
  }

}
