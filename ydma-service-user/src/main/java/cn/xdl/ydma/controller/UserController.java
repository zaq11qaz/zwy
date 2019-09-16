package cn.xdl.ydma.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.service.UserService;

@RestController
//@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST})
public class UserController {

	@Autowired
	private UserService userService;
	
	//通过id获取信息
	@GetMapping("/user/get")
	public YdmaResult load(int id) {
		return userService.loadUser(id);
	}
	
	//注册
	@PostMapping("/user/regist")
	public YdmaResult regist(String name,String password) {
		return userService.addUser(name, password);
	}
	
	//登录
	@PostMapping("/user/login")
	public YdmaResult login(String name,String password) {
		return userService.loginUser(name, password);
	}
	
	//验证密码
	@PostMapping("/user/token")
	public YdmaResult login(String token) {
		return userService.checkUser(token);
	}
	
	//修改密码
		@PostMapping("/user/changePassword")
		public YdmaResult changePassword(String name,String password,String newPassword) {
			return userService.changePassword(name, password,newPassword);
		}
	
		@PostMapping("/user/changeProfile")
		public YdmaResult changeProfile(int id,String nick_name,String position,
				String sex,String location,String signature,String image) {
			return userService.changeProfile(id, nick_name, position, sex, location,
					 signature, image);
		}
}
