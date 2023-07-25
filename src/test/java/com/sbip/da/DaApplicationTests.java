package com.sbip.da;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class DaApplicationTests {

//	@Autowired private DataSource dataSource;
	@Autowired private MongoTemplate mongoTemplate;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
//		assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
//		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
//	}

	@Test
	public void givenObjectAvailableWhenSaveToCollectionThenExpectValue() throws MongoException{
		DBObject dbObject = BasicDBObjectBuilder.start().add("Mongodb", "Mongodb is a NoSql Database").get();
		mongoTemplate.save(dbObject,"collection");
		assertThat(mongoTemplate.findAll(DBObject.class,"collection"))
				.extracting("Mongodb")
				.containsOnly("Mongodb is a NoSql Database");
	}
}
