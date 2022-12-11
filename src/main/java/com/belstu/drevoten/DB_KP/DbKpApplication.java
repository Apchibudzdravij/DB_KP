package com.belstu.drevoten.DB_KP;

import com.belstu.drevoten.DB_KP.model.User_List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DbKpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbKpApplication.class, args);
	}

}
