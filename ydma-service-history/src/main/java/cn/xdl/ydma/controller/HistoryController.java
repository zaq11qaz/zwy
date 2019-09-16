package cn.xdl.ydma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.serive.HistoryService;

@RestController
public class HistoryController {

	@Autowired
	private HistoryService historyDao;
	
	@RequestMapping("/history/select")
	public YdmaResult SelectById(@RequestParam("id")int id) {
		return historyDao.selectByid(id);
	}
	
	
}
