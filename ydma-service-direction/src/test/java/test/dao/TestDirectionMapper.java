package test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xdl.RunDirectionBoot;
import cn.xdl.ydma.dao.DirectionMapper;
import cn.xdl.ydma.entity.Direction;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RunDirectionBoot.class)
public class TestDirectionMapper {

	@Autowired
	private DirectionMapper directionDao;
	
	@Test
	public void test() {
		List<Direction> list = directionDao.selectAll();
		System.out.println(list);
	}
	
}
