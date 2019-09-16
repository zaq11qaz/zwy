package test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xdl.RunUserBoot;
import cn.xdl.ydma.dao.UserMapper;
import cn.xdl.ydma.entity.User;


@RunWith(SpringRunner.class)//指定一个junit启动器，可以在启动时创建Spring容器对象
@SpringBootTest(classes=RunUserBoot.class)
public class TestUserMapper {
	
	@Autowired
	private UserMapper userDao;
	
	@Test
	public void test1() {
		User user = userDao.selectByName("paopao");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void test2() {
		User user = userDao.selectByName("aaa");
		Assert.assertNull(user);
	}
	
}
