package cn.xdl.ydma.serive.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.LoginHistoryMapper;
import cn.xdl.ydma.entity.LoginHistory;
import cn.xdl.ydma.serive.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginHistoryMapper loginDao;
	
	
	@Override
	@Cacheable(value = "login",key="#root.methodName+'['+#id+']'")
	public YdmaResult selectByid(int id) {
		YdmaResult result = new YdmaResult();
		List<LoginHistory> list = loginDao.selectByUserId(id);
		if (list.isEmpty()) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_EMPTY_MSG);
		}else {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_SUCCESS_MSG);
			result.setData(list);
		}
		return result;
	}


	@Override
	@Cacheable(value = "login",key="#root.methodName+'['+#uid+','+#city+','+#ip+']'")
	public void insert(int uid, String city, String ip) {
		LoginHistory lh = new LoginHistory();
		lh.setLoginTime(new Date());
		lh.setCity(city);
		lh.setIp(ip);
		lh.setUserId(uid);
		
		loginDao.insert(lh);
		
	}

}
