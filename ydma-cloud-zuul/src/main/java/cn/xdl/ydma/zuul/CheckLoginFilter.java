package cn.xdl.ydma.zuul;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import cn.xdl.ydma.common.YdmaConstant;
import cn.xdl.ydma.common.YdmaResult;

@Component
public class CheckLoginFilter extends ZuulFilter {

	@Autowired
	private RestTemplate restTemplate;

	public String filterType() {
		return "pre";// 过滤器类型，pre、post、error、route
	}

	public int filterOrder() {
		return -999;// 过滤器执行顺序... -3、-2、-1、0、1、2、3
	}

	public boolean shouldFilter() {
		// 获取请求URI，不需要登录的就return false;需要登录的return true
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String uri = request.getRequestURI();
		List<String> checkUris = new ArrayList<String>();
		checkUris.add("/ydma-course/course/direction");
		System.out.println("检查" + uri + "是否需要进行拦截检查");
		if (checkUris.contains(uri)) {
			return true;// true调用filter
		}
		return false;// false不调用filter

	}

	public Object run() throws ZuulException {
		// 过滤器执行逻辑
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		String token = request.getParameter("token");
		if (token != null && !"".equals(token)) {
			String url = "http://YDMA-USER/user/token";
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("token", token);
			YdmaResult checkResult = restTemplate.postForObject(url, params, YdmaResult.class);
			if (checkResult.getCode() == YdmaConstant.SUCCESS) {
				ctx.setSendZuulResponse(true);// 允许继续路由调用服务
				ctx.setResponseStatusCode(200);
				return true;
			}
			return false;
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setContentType("text/html;charset=utf-8");
		ctx.setSendZuulResponse(false);// 阻止继续路由调用服务
		ctx.setResponseStatusCode(401);// response.setStatus(401);
		ctx.setResponseBody("{\"code\":-1,\"msg\":\"用户身份不合法，未登录\"}");// out.println("{\"code\":-1,\"msg\":\"用户身份不合法，未登录\"}");
		return null;
	}

}