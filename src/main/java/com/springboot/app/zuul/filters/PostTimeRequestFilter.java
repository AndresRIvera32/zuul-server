package com.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class PostTimeRequestFilter extends ZuulFilter{
	
	private static Logger log = LoggerFactory.getLogger(PostTimeRequestFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Getting into post filter");

		Long initTime = (Long)request.getAttribute("initTime");
		Long finalTime = System.currentTimeMillis();
		Long executionTime = finalTime - initTime;
		
		log.info(String.format("Execution time in seconds %s seg", executionTime.doubleValue()/1000.00));
		log.info(String.format("Execution time in miliseconds %s ms", executionTime));

		request.setAttribute("initTime", initTime);
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	

}
