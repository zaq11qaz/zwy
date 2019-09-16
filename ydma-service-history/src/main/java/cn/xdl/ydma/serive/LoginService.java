package cn.xdl.ydma.serive;

import cn.xdl.ydma.common.YdmaResult;

public interface LoginService {

	public YdmaResult selectByid(int id);
	
	public void insert(int uid , String city , String ip);
}
