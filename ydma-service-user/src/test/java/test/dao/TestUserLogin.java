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
import cn.xdl.ydma.util.JWTUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunUserBoot.class)
public class TestUserLogin {

	@Autowired
	private UserController controller;

	@Test // 测试SpringMVC处理流程
	public void test1() throws Exception {
		// 创建MockMvc对象，可以发送HTTP请求，接收响应结果
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		// 使用mock对象发送POST请求
		RequestBuilder request = MockMvcRequestBuilders.post("/user/login").param("name", "1").param("password",
				"1");
		MvcResult result = mock.perform(request).andReturn();
		// 获取返回的结果
		String jsonStr = result.getResponse().getContentAsString();

		// 将返回jsonStr结果转成YdmaResult对象，然后使用断言比对结果和预期
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(jsonStr, YdmaResult.class);
		Assert.assertEquals(YdmaConstant.SUCCESS, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.LOGIN_SUCCESS_MSG, ydmaResult.getMsg());
		System.out.println(ydmaResult.getData());
		System.out.println("验证token: "+JWTUtil.isVerify(ydmaResult.getData().toString()));
	}
	
	@Test//测试SpringMVC处理流程
	public void test4() throws Exception {
		//创建MockMvc对象，可以发送HTTP请求，接收响应结果
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		//使用mock对象发送POST请求
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/token")
			.param("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ4ZGwiLCJ1aWQiOjI5LCJleHAiOjE1Njc1NzkzMjN9.jwvpnZna1if7VdWdfXHMF_ymWgEuMT0yCFmIRpu81KU");
		MvcResult result = mock.perform(request).andReturn();
		//获取返回的结果
		String jsonStr = result.getResponse().getContentAsString();
		System.out.println(jsonStr);
	}
	
}
