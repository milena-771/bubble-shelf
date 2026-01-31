package ovh.electricowl.bubble.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final ApiKeyFilter apiKeyFilter;
	private final RateLimitFilter rateLimitFilter;

	public SecurityConfig(ApiKeyFilter apiKeyFilter, RateLimitFilter rateLimitFilter) {
		this.apiKeyFilter = apiKeyFilter;
		this.rateLimitFilter = rateLimitFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> auth.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
				.addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
