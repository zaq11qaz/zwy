package cn.xdl.ydma.service;

import cn.xdl.ydma.common.YdmaResult;

public interface IssueService {

	//插入提问
	public YdmaResult insertIssue(String title,String context,int uid,int vid);
	//选择问题根据视频id
	public YdmaResult selectByVid(int id);
	//采纳
	public YdmaResult updataIssueByid(int id , int aid);
}
