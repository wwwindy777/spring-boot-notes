package com.example.core.logger.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * 将日志所需参数填充至MDC
 *
 * @author wangzhihao
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/**", filterName = RequestLoggingFilter.FILTER_NAME)
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

  static final String FILTER_NAME = "RequestLoggingFilter";

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {
    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress == null) {
      ipAddress = request.getRemoteAddr();
    }
    MDC.put("request_ip", ipAddress);
    MDC.put("request_url", request.getRequestURI());
    MDC.put("request_user_id", "<get userId from service>");
  }

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    MDC.remove("request_ip");
    MDC.remove("request_url");
    MDC.remove("request_user_id");
  }
}
