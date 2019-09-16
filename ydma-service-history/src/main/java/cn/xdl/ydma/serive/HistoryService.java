package cn.xdl.ydma.serive;

import cn.xdl.ydma.common.YdmaResult;

public interface HistoryService {

	public YdmaResult selectByid(int id);
	
	public void insert(int uid , int cid , int vid);
}
