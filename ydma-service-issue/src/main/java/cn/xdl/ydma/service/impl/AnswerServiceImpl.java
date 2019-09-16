package cn.xdl.ydma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.AnswerMapper;
import cn.xdl.ydma.entity.Answer;
import cn.xdl.ydma.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerMapper AnswerDao;

	@Override
	@Cacheable(value = "answer",key="#root.methodName+'['+#context+','+#uid+','+#iid+']'")
	public YdmaResult insertAnswer(String context, int uid, int iid) {
		YdmaResult result = new YdmaResult();
		Answer answer = new Answer();
		answer.setContext(context);
		answer.setIssueId(iid);
		answer.setUserId(uid);
		answer.setPublishTime(new Date());

		int i = AnswerDao.insert(answer);

		if (i == 0) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.INERT_ANSWER_ERROR);
		} else {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.INERT_ANSWER_SUCCESS);
		}
		return result;
	}

	@Override
	@Cacheable(value = "answer",key="#root.methodName+'['+#iid+','+#uid+'")
	public YdmaResult selectAnswerByIidAndUid(int iid, int uid) {
		YdmaResult result = new YdmaResult();
		List<Answer> list = AnswerDao.selectByiidAnduid(iid, uid);

		if (list.isEmpty()) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_EMPTY_MSG);
		} else {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.QUERY_SUCCESS_MSG);
			result.setData(list);
		}
		return result;
	}



}
