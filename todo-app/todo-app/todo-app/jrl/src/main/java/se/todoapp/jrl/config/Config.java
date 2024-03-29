package se.todoapp.jrl.config;
 
	import java.util.TimeZone;

import javax.persistence.EntityManagerFactory;
	import javax.sql.DataSource;

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
	import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
	import org.springframework.orm.jpa.JpaTransactionManager;
	import org.springframework.orm.jpa.JpaVendorAdapter;
	import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
	import org.springframework.orm.jpa.vendor.Database;
	import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
	import org.springframework.transaction.annotation.EnableTransactionManagement;

	import com.zaxxer.hikari.HikariConfig;
	import com.zaxxer.hikari.HikariDataSource;

	@Configuration
	@EnableJpaAuditing
	@EnableJpaRepositories("se.todoapp.jrl.repository")
	@EnableTransactionManagement
	class Config {

		// Data Source
		@Bean
		DataSource dataSource() {

			HikariConfig config = new HikariConfig();
			config.setDriverClassName("com.mysql.cj.jdbc.Driver");
			config.setJdbcUrl("jdbc:mysql://localhost:3306/DB-NAME?serverTimezone=" + TimeZone.getDefault().getID());
			config.setUsername("login");
			config.setPassword("password");

			return new HikariDataSource(config);
		}

		// Transaction Manager
		@Bean
		JpaTransactionManager transactionManager(EntityManagerFactory factory) {
			return new JpaTransactionManager(factory);
		}

		// JPA Vendor
		@Bean
		JpaVendorAdapter jpaVendorAdapter() {
			HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			adapter.setDatabase(Database.MYSQL);
			adapter.setGenerateDdl(true);
			return adapter;
		}

		// Entity Manager Factory
		@Bean
		LocalContainerEntityManagerFactoryBean entityManagerFactory() {

			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setDataSource(dataSource());
			factory.setJpaVendorAdapter(jpaVendorAdapter());
			factory.setPackagesToScan("se.todoapp.jrl.model");

			return factory;
		}
	}

