package com.sbip.da;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DaApplicationTests {

	@Autowired DataSource dataSource;

	@Test
	void contextLoads() {
	}

	@Test
	public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
		assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
	}
}
