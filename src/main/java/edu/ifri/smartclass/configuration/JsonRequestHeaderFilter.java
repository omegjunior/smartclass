package edu.ifri.smartclass.configuration;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

@Component
public class JsonRequestHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public Enumeration<String> getHeaders(String name) {
                if (name.equals("Accept")) {
                    Set<String> customHeaders = new HashSet<>();
                    Enumeration<String> curHeaders = super.getHeaders(name);
                    while (curHeaders.hasMoreElements()) {
                        String header = curHeaders.nextElement();
                        customHeaders.add(MediaType.APPLICATION_JSON_VALUE.concat(";").concat(header));
                    }

                    return Collections.enumeration(customHeaders);
                }
                return super.getHeaders(name);
            }
        };

        chain.doFilter(requestWrapper, response);
    }
}
