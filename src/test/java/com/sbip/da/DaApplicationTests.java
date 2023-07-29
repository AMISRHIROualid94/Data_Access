package com.sbip.da;

import com.sbip.da.models.Course;
import com.sbip.da.repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@DataMongoTest
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class DaApplicationTests {

//	@Autowired private DataSource dataSource;
//	@Autowired private MongoTemplate mongoTemplate;
	@Autowired
	private CourseRepository courseRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
//		assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
//		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
//	}

//	@Test
//	public void givenObjectAvailableWhenSaveToCollectionThenExpectValue() throws MongoException{
//		DBObject dbObject = BasicDBObjectBuilder.start().add("Mongodb", "Mongodb is a NoSql Database").get();
//		mongoTemplate.save(dbObject,"collection");
//		assertThat(mongoTemplate.findAll(DBObject.class,"collection"))
//				.extracting("Mongodb")
//				.containsOnly("Mongodb is a NoSql Database");
//	}


}
