package cn.xdl.ydma.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.UserMapper;
import cn.xdl.ydma.entity.User;
import cn.xdl.ydma.service.UserService;
import cn.xdl.ydma.util.JWTUtil;
import cn.xdl.ydma.util.PasswordUtil;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userDao;

	/**
	 * 根据id查
	 */
	@Override
	@Cacheable(value = "user",key="#root.methodName+'['+#id+']'")
	public YdmaResult loadUser(int id) {
		User user = userDao.selectByPrimaryKey(id);
		YdmaResult result = new YdmaResult();
		if (user != null) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.QUERY_SUCCESS_MSG);
			user.setPassword(null);
			user.setSalt(null);
			result.setData(user);
		} else {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_EMPTY_MSG);
		}

		System.out.println(result);
		return result;
	}

	/**
	 * 注册
	 */
	@Override
	@Cacheable(value = "user",key="#root.methodName+'['+#name+','+#password+']'")
	public YdmaResult addUser(String name, String password) {
		YdmaResult result = new YdmaResult();

		// 检查用户名是否占用
		User user = userDao.selectByName(name);

		if (user != null) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.REGIST_NAME_ERROR_MSG);
			return result;
		}

		String salt = PasswordUtil.salt();
		String md5Password = PasswordUtil.md5(password + salt);

		user = new User();
		user.setName(name);
		user.setSalt(salt);
		user.setRegtime(new Date());
		user.setPassword(md5Password);
		user.setRegtime(new Date());

		userDao.insert(user);

		// 返回成功
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.REGIST_SUCCESS_MSG);
		return result;
	}

	/**
	 * 登录
	 */
	@Override
	@Cacheable(value = "user",key="#root.methodName+'['+#name+','+#password+']'")
	public YdmaResult loginUser(String name, String password) {
		YdmaResult result = new YdmaResult();

		// 根据name查询user信息
		User user = userDao.selectByName(name);
		
		// 查无此人
		if (user == null) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.LOGIN_FAILED_MSG);
			return result;
		}

		String salt = user.getSalt();
		String passwordTrue = user.getPassword();

		// 使用工具编译密码
		String passwordEntry = PasswordUtil.md5(password + salt);

		// 比对
		// 错误
		// 返回1
		if (passwordTrue.equals(passwordEntry)) {
			// 生成token并返回
			String token = JWTUtil.createToken(user);
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.LOGIN_SUCCESS_MSG);
			result.setData(token);
		} else {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.LOGIN_FAILED_MSG);
		}
		return result;
	}

	/**
	 * 修改密码
	 */
	@Override
	@Cacheable(value = "user",key="#root.methodName+'['+#name+','+#password+','+#newPassword+']'")
	public YdmaResult changePassword(String name, String password, String newPassword) {
		User user = userDao.selectByName(name);
		YdmaResult result = new YdmaResult();

		String salt = user.getSalt();
		// 正确密码
		String passwordTrue = user.getPassword();

		// 使用工具编译密码
		// 比对
		String passwordEntry = PasswordUtil.md5(password + salt);

		// 如果输入的旧密码正确
		if (passwordTrue.equals(passwordEntry)) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.CHANGE_PASSWORD_SUCCESS);

			// 新密码加盐
			user.setPassword(PasswordUtil.md5(newPassword + salt));

			// 更新user
			userDao.updateByPrimaryKey(user);
		} else {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.CHANGE_PASSWORD_ERROR);
		}
		return result;
	}

	/**
	 * token验证
	 */
	@Override
	@Cacheable(value = "user",key="#root.methodName+'['+#token+']'")
	public YdmaResult checkUser(String token) {
		YdmaResult result = new YdmaResult();
		boolean ok = JWTUtil.isVerify(token);
		if (ok) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.TOKEN_SUCCESS_MSG);
		} else {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.TOKEN_ERROR_MSG);
		}
		return result;
	}

	/**
	 * 修改个人资料
	 */
	@Override
	@Cacheable(value = "user",key="#root.methodName+'['+#id+','+#nick_name+','+#position+','+#sex+','+#location+']'")
	public YdmaResult changeProfile(int id, String nick_name, String position, String sex, String location,
			String signature, String image) {
		YdmaResult result = new YdmaResult();
		User user = userDao.selectByPrimaryKey(id);

		user.setNickName(nick_name);
		user.setPosition(position);
		user.setSex(sex);
		user.setLocation(location);
		user.setSignature(signature);
		user.setImage(image);

		int i = userDao.insert(user);
		if (i > 0) {

			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.CHANGE_PROFILE_SUCCESS);

		} else {
			result.setCode(YdmaConstant.ERROR2);
			result.setMsg(YdmaConstant.CHANGE_PROFILE_ERROR);
		}

		return result;
	}
}
