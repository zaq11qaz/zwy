package cn.xdl.ydma.service;

import cn.xdl.ydma.common.YdmaResult;

public interface UserService {

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public YdmaResult loadUser(int id); 

	/**
	 * 插入用户
	 * @param user 对象
	 * @return 
	 */
	public YdmaResult addUser(String name,String password);

	/**
	 * 登录
	 * @param name 账号
	 * @param password 密码
	 * @return
	 */
	public YdmaResult loginUser(String name,String password);
	
	/**
	 * token验证
	 * @param token
	 * @return
	 */
	public YdmaResult checkUser(String token);

	/**
	 * 修改密码
	 * @param name
	 * @param password
	 * @return
	 */
	public YdmaResult changePassword(String name,String password,String newPassword);

	public YdmaResult changeProfile(int id, String nick_name, String position, String sex, String location,
			String signature, String image);

	
	
}
