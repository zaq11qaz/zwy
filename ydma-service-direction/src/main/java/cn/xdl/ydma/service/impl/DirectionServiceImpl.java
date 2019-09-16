package cn.xdl.ydma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;
import cn.xdl.ydma.dao.DirectionMapper;
import cn.xdl.ydma.entity.Direction;
import cn.xdl.ydma.feign.CourseFeign;
import cn.xdl.ydma.service.DirectionService;

@Service
public class DirectionServiceImpl implements DirectionService {

	@Autowired
	private DirectionMapper directionDao;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private CourseFeign courseFeign;
	
	/**
	 * 加载全部
	 */
	@Override
	@Cacheable(value = "direction")
	public YdmaResult loadAll() {

		List<Direction> list = directionDao.selectAll();
		for (Direction d : list) {
			// 通过调用/course/direction服务加载相关的推荐课程信息
//			String url = "http://localhost:7007/course/direction?id=" + d.getId() + "&size=2"+"&page=0";
//			YdmaResult courseResult = restTemplate.getForObject(url, YdmaResult.class);
			
			YdmaResult courseResult = courseFeign.loadCourseByDirectionId(d.getId(), 1, 4);
			if (courseResult.getCode() == YdmaConstant.SUCCESS) {
				Map data = (Map) courseResult.getData();
				List courses = (List) data.get("list");
				d.setCourses(courses);
			}
		}
		YdmaResult result = new YdmaResult();
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.QUERY_SUCCESS_MSG);
		result.setData(list);
		return result;
	}
}
