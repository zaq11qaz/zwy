package cn.xdl.ydma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.service.DirectionService;

@RestController
public class DirectionController {

	@Autowired
	private DirectionService DirectionImpl;

	@GetMapping("/direct/all")
	public YdmaResult loadAll() {
		return DirectionImpl.loadAll();
	}

}
