package com.hening.sale.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.hening.sale.common.constant.Constants;
import com.hening.sale.utils.SessionUtil;

public class UrlFilter implements Filter{

	public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
			throws IOException,ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		if (httpServletRequest.getSession().getAttribute(Constants.CURRENT_USER) != null) {
			filterChain.doFilter(request, response);
		} else {
			
			List<String> urlList = Constants.URL_LIST;
			String url = getRequestUrl( httpServletRequest );
			
			if(hasInincludes( urlList, url )){
				filterChain.doFilter(request, response);
			}else{
				SessionUtil.clearCurrentUser( httpServletRequest, httpServletResponse);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript' type='text/javascript'>");
				out.println("window.location.href='" + httpServletRequest.getContextPath() + "/login/login.login.html?_="+System.currentTimeMillis()+"'");
				out.println("</script>");
			}
			
		}
		
	}

	private boolean hasInincludes(List<String> urlList, String url) {
		if (urlList != null) {
			for (String str: urlList) {
				if (url.matches(str)) {
					return true;
				}
			}
		}
		return false;
	}

	private String getRequestUrl(HttpServletRequest request) {
		try{
			String contextPath = request.getContextPath(),
					requestUri = request.getRequestURI(),
					suffix = (String) requestUri.subSequence(requestUri.lastIndexOf("."), requestUri.length()),	
					requestUrl = StringUtils.replace(requestUri, contextPath, "", 1),
					lastrequestUrl = StringUtils.replace(requestUrl, suffix, "", 1);
			return lastrequestUrl;
		}catch( Exception e){
			String 	contextPath = request.getContextPath(),
					requestUri = request.getRequestURI(),
					requestUrl = StringUtils.replace(requestUri, contextPath, "", 1);
			return requestUrl;
		}
	}

	public void init(FilterConfig arg0) throws ServletException { }
}
