package com.ecommerce.shoply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ShoplyApplicationTests {

	@Autowired
	DataSource dataSource;
	@Value("${test-name}")
	private String name;
	@Value("${test-year}")
	private String currentYear;


	@Test
	public void printValues(){
		assertThat(name).isEqualTo("Ehigie");
		assertThat(currentYear).isEqualTo("2021");
		log.info("Test name -> {}", name);
		log.info("Test year -> {}", currentYear);
	}

	@Test
	void connect_to_database_test() {
		assertThat(dataSource).isNotNull();
		log.info("Datasource properties -> {}", dataSource);

		try {
			Connection conn = dataSource.getConnection();
			assertThat(conn).isNotNull();
			log.info("Database -> {}", conn.getCatalog());
		} catch (SQLException e) {
			log.info("An exception occured ->{}", e.getMessage());
		}

	}

}
