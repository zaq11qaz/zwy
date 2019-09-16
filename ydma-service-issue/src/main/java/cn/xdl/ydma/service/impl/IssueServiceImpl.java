package cn.xdl.ydma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.IssueMapper;
import cn.xdl.ydma.entity.Issue;
import cn.xdl.ydma.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	private IssueMapper issueDao;
	
	@Override
	@Cacheable(value = "issue",key="#root.methodName+'['+#title+','+#context+','+#uid+','+#vid+']'")
	public YdmaResult insertIssue(String title, String context, int uid,int vid) {
		YdmaResult result = new YdmaResult();
		Issue issue = new Issue();
		issue.setIssueContext(context);
		issue.setUserId(uid);
		issue.setVideoId(vid);
		issue.setHeadline(title);
		issue.setBrowseCount(0);
		issue.setAnswerCount(0);
		issue.setPublishTime(new Date());
		
		int i = issueDao.insert(issue);
		if (i==0) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.INERT_ISSUE_ERROR);
		}else {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.INERT_ISSUE_SUCCESS);
		}
		return result;
	}

	@Override
	@Cacheable(value = "issue",key="#root.methodName+'['+#vid+']'")
	public YdmaResult selectByVid(int vid) {
		YdmaResult result = new YdmaResult();
		List<Issue> list = issueDao.selectByVid(vid);
		if(list.isEmpty()) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_EMPTY_MSG );
		}else {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_SUCCESS_MSG );
			result.setData(list);
		}
		return result;
	}
	
	
	@Override
	@Cacheable(value = "issue",key="#root.methodName+'['+#iid+','+#aid+']'")
	public YdmaResult updataIssueByid(int iid, int aid) {
		YdmaResult result = new YdmaResult();
		Issue issue = issueDao.selectByPrimaryKey(iid);
		if (issue == null) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_EMPTY_MSG );
			return result;
		}
		
		issue.setRightAnswerId(aid);
		int i = issueDao.updateByPrimaryKey(issue);
		
		if (i == 0) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.UPDATA_ISSUE_BY_ANSWERID_ERROR );
		}else {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.UPDATA_ISSUE_BY_ANSWERID_SUCCESS );
		}
		
		return result;
	}

}
