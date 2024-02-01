package com.integracao.main;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@SpringBootApplication(scanBasePackages={"com.integracao.main","com.integracao.main.controller","com.integracao.main.modelo","com.integracao.main.service"})
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.integracao.main","com.integracao.main.controller","com.integracao.main.modelo","com.integracao.main.service"})
public class ApiCrudIntegracaoApplication {

	public static void main(String[] args) {				
		SpringApplication.run(ApiCrudIntegracaoApplication.class, args);		
	}
	
	@SuppressWarnings("deprecation")
	@Bean(initMethod = "migrate")
	Flyway flywayStructure() {
	   final Flyway flyway = new Flyway();
	   flyway.setLocations("classpath:db/migration");
	   flyway.setBaselineOnMigrate(true);
	   flyway.setDataSource(data());
	   return flyway;
	}

    @Bean
    DataSource data() {
        DriverManagerDataSource bd = new DriverManagerDataSource();
        bd.setDriverClassName("com.mysql.jdbc.Driver");
        bd.setUrl("jdbc:mysql://localhost:3306/api");
        bd.setUsername("officer");
        bd.setPassword("mfs80");
        return bd;
    }

}
