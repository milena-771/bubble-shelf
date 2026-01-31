package ovh.electricowl.bubble.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

	@Value("${bubble.api.key}")
	private String apiKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		String path = request.getRequestURI();
		if (path.startsWith("/bubble-api")) {
			String requestKey = request.getHeader("X-API-KEY");
			if ((requestKey == null) || !requestKey.equals(apiKey)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Unauthorized: Invalid API Key");
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

}
