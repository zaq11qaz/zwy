package cn.xdl.ydma.service;

import cn.xdl.ydma.common.YdmaResult;

public interface AnswerService {

	//插入回答
	public YdmaResult insertAnswer(String context,int uid,int iid);
	//查询所有回答根据用户id
	public YdmaResult selectAnswerByIidAndUid(int iid , int uid );
	
}
