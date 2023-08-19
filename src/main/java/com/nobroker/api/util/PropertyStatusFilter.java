package com.nobroker.api.util;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PropertyStatusFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Long propertyId = extractPropertyId(httpRequest);

        // Simulate property status retrieval from database
        String propertyStatus = getPropertyStatus(propertyId);

        if ("Available".equals(propertyStatus)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Property is not available");
        }
    }

    private Long extractPropertyId(HttpServletRequest request) {
        // Extract property ID from request, for example from path
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/api/properties/")) {
            String propertyIdStr = pathInfo.substring("/api/properties/".length());
            try {
                return Long.parseLong(propertyIdStr);
            } catch (NumberFormatException e) {
                // Handle parsing error
            }
        }
        return null;
    }

    private String getPropertyStatus(Long propertyId) {
        // Simulate fetching property status from database
        // Replace with your actual logic
        return "Available";
    }

    // Other methods if needed: init() and destroy()
}

