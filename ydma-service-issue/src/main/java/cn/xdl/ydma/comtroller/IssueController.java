package cn.xdl.ydma.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.service.IssueService;

@RestController
public class IssueController {

	@Autowired
	private IssueService issueDao;
	
	@GetMapping("/issue/insert")
	public YdmaResult collectNote(
			@RequestParam("id")int uid,
			@RequestParam("title")String title,
			@RequestParam("context")String context,
			@RequestParam("vid")int vid
			) {
		return issueDao.insertIssue(title, context, uid,vid);
	}
	
	@GetMapping("/issue/findbyvid")
	public YdmaResult selectByVid(
			@RequestParam("id")int vid
			) {
		return issueDao.selectByVid(vid);
	}
	
	@GetMapping("/issue/update")
	public YdmaResult selectAnswerByIidAndUid(
			@RequestParam("iid")int iid,
			@RequestParam("uid")int uid
			) {
		return issueDao.updataIssueByid(iid ,uid);
	}
}
