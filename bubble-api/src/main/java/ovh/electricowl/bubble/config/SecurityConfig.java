package ovh.electricowl.bubble.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

	@Value("${bubble-api.cors.enabled}")
	private boolean corsEnabled;

	private final ApiKeyFilter apiKeyFilter;
	private final RateLimitFilter rateLimitFilter;

	public SecurityConfig(ApiKeyFilter apiKeyFilter, RateLimitFilter rateLimitFilter) {
		this.apiKeyFilter = apiKeyFilter;
		this.rateLimitFilter = rateLimitFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(corsCustomizer()).csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> auth.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
				.addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	private Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer() {
		return corsEnabled ? Customizer.withDefaults() : cors -> cors.disable();
	}

}
