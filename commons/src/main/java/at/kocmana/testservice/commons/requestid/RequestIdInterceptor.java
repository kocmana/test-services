package at.kocmana.testservice.commons.requestid;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

public class RequestIdInterceptor<T> implements HandlerInterceptor {

  private final Supplier<T> idGenerator;

  public RequestIdInterceptor(Supplier<T> idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    T requestId = idGenerator.get();
    setIdInMdcContext(requestId);
    setIdAsRequestAttribute(request, requestId);
    setIdAsResponseHeader(response, requestId);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    request.removeAttribute(RequestId.REQUEST_ID_KEY);
    MDC.remove(RequestId.REQUEST_ID_HEADER_NAME);
  }

  private void setIdInMdcContext(T requestId) {
    MDC.put(RequestId.REQUEST_ID_KEY, requestId.toString());
  }

  private void setIdAsRequestAttribute(HttpServletRequest request, T requestId) {
    request.setAttribute(RequestId.REQUEST_ID_KEY, requestId.toString());
  }

  private void setIdAsResponseHeader(HttpServletResponse response, T requestId) {
    response.addHeader(RequestId.REQUEST_ID_HEADER_NAME, requestId.toString());
  }

}
