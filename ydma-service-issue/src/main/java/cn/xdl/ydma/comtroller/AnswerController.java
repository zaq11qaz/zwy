package cn.xdl.ydma.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.service.AnswerService;

@RestController
public class AnswerController {

	@Autowired
	private AnswerService issueDao;
	
	@GetMapping("/answer/insert")
	public YdmaResult collectNote(
			@RequestParam("uid")int uid,
			@RequestParam("context")String context,
			@RequestParam("iid")int iid
			) {
		return issueDao.insertAnswer(context , uid , iid);
	}
	
	@GetMapping("/answer/select")
	public YdmaResult selectAnswerByIidAndUid(
			@RequestParam("uid")int uid,
			@RequestParam("iid")int iid
			) {
		return issueDao.selectAnswerByIidAndUid(iid ,uid);
	}
	
	
}
