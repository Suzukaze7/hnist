package org.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        String encoding = servletRequest.getCharacterEncoding();
        if (encoding == null || !encoding.equalsIgnoreCase("UTF-8"))
            try {
                servletRequest.setCharacterEncoding("UTF-8");
                servletResponse.setCharacterEncoding("UTF-8");
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void destroy() {

    }
}
