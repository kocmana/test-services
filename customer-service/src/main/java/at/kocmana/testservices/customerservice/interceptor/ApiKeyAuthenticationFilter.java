package at.kocmana.testservices.customerservice.interceptor;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

  private final String principalRequestHeader;

  public ApiKeyAuthenticationFilter(String principalRequestHeader) {
    this.principalRequestHeader = principalRequestHeader;
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return request.getHeader(principalRequestHeader);
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }

}
