package cn.xdl.ydma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.ChapterMapper;
import cn.xdl.ydma.entity.Chapter;
import cn.xdl.ydma.service.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterMapper chapterDao;

	@Override
	@Cacheable(value = "video",key="#root.methodName+'['+#id+']'")
	public YdmaResult findChapterByCourseId(int id) {
		YdmaResult result = new YdmaResult();
		List<Chapter> list = chapterDao.findChapterByCourseId(id);

		if (list.isEmpty()) {
			result.setCode(YdmaConstant.ERROR);
			result.setMsg(YdmaConstant.QUERY_EMPTY_MSG);
		}else {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.QUERY_SUCCESS_MSG);
			result.setData(list);
		}

		return result;
	}

}
