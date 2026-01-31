package ovh.electricowl.bubble.config;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

	private static final long TIME_WINDOW_MS = 1000; // 1 sec
	private static final int MAX_REQUESTS = 5; // max requests per IP per window

	private final Map<String, RequestTracker> requestMap = new ConcurrentHashMap<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		String ip = request.getRemoteAddr();

		RequestTracker tracker = requestMap.computeIfAbsent(ip, k -> new RequestTracker());
		long now = Instant.now().toEpochMilli();

		if ((tracker.timestamp + TIME_WINDOW_MS) < now) {
			tracker.count = 0;
			tracker.timestamp = now;
		}

		tracker.count++;

		if (tracker.count > MAX_REQUESTS) {
			response.setStatus(429);
			response.getWriter().write("Too many requests. Please wait.");
			return;
		}

		filterChain.doFilter(request, response);
	}

	private static class RequestTracker {
		long timestamp = Instant.now().toEpochMilli();
		int count = 0;
	}

}
