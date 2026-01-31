package ovh.electricowl.bubble.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

	@Bean(name = "sourceBubble")
	@ConfigurationProperties(prefix = "ds.db.bubble")
	public DataSource postgreSQLDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "bubbleJdbcTemplate")
	public JdbcTemplate postgreSQLJdbcTemplate(
			@Qualifier("sourceBubble") DataSource postgreSQLDataSource) {
		return new JdbcTemplate(postgreSQLDataSource);
	}

}
