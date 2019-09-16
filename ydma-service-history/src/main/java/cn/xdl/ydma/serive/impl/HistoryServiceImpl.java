package cn.xdl.ydma.serive.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.BrowsingHistoryMapper;
import cn.xdl.ydma.entity.BrowsingHistory;
import cn.xdl.ydma.serive.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private BrowsingHistoryMapper historyDao;
	
	
	@Override
	@Cacheable(value = "history",key="#root.methodName+'['+#id+']'")
	public YdmaResult selectByid(int id) {
		YdmaResult result = new YdmaResult();
		List<BrowsingHistory> list = historyDao.selectByUid(id);
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
	@Cacheable(value = "history",key="#root.methodName+'['+#uid+','+#cid+','+#vid+']'")
	public void insert(int uid, int cid, int vid) {
		BrowsingHistory hb = new BrowsingHistory();
		hb.setBrowseTime(new Date());
		hb.setCourseId(cid);
		hb.setVideoId(vid);
		hb.setUseId(uid);
		
		historyDao.insert(hb);
	}

}
