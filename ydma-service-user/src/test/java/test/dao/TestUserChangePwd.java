package test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xdl.RunUserBoot;
import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunUserBoot.class)
public class TestUserChangePwd {

	@Autowired
	private UserController controller;

	@Test // 测试SpringMVC处理流程
	public void test1() throws Exception {
		// 创建MockMvc对象，可以发送HTTP请求，接收响应结果
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		// 使用mock对象发送POST请求
		RequestBuilder request = MockMvcRequestBuilders.post("/user/changePassword").param("name", "xdl").param("password",
				"123456").param("newPassword","123");
		MvcResult result = mock.perform(request).andReturn();
		// 获取返回的结果
		String jsonStr = result.getResponse().getContentAsString();

		// 将返回jsonStr结果转成YdmaResult对象，然后使用断言比对结果和预期
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(jsonStr, YdmaResult.class);
		Assert.assertEquals(YdmaConstant.SUCCESS, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.CHANGE_PASSWORD_SUCCESS, ydmaResult.getMsg());
	}
	
}
