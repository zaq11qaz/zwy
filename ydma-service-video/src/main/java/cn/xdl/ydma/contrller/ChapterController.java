package cn.xdl.ydma.contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.service.ChapterService;

@RestController
public class ChapterController {

	@Autowired
	private ChapterService chapterDao;

	@GetMapping("chapter/id")
	public YdmaResult findChapterByCourseId(int id) {
		return chapterDao.findChapterByCourseId(id);
	}
	
}
