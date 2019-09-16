package cn.xdl.ydma.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.xdl.ydma.common.YdmaResult;

@FeignClient(name="YDMA-COURSE")//对应注册的服务名
public interface CourseFeign {

	@GetMapping("/course/direction")
	public YdmaResult loadCourseByDirectionId(
			@RequestParam("id")int directionId,
			@RequestParam(name="page",required=false,defaultValue="1")Integer page,
			@RequestParam(name="size",required=false,defaultValue="4")Integer size);

}
