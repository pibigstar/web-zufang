package com.pibigstar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pibigstar.zufang.domain.RentHouse;
import com.pibigstar.zufang.repository.RentHouseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ZufangApplicationTests {

	@Autowired  
	private RentHouseRepository repository;  

	@Test
	public void testMongo() {
		final Logger logger = LoggerFactory.getLogger(getClass());
		RentHouse rentHouse = new RentHouse();
		rentHouse.setId(1);
		rentHouse.setName("郑州轻工业学院");
		rentHouse.setLocal("郑州轻工业学院中原区");
		rentHouse.setUrl("http://www.baidu.com");
		repository.save(rentHouse);
		logger.info("********存储成功********");
	}

}
