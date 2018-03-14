package com.hening.sale.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hening.sale.common.constant.Constants;
import com.hening.sale.corecode.login.entity.User;

public class SessionUtil {
	

	/**
	 * 
	 *@author (作者): luozg 
	 *@date 日期： 2013年12月27日下午7:04:31.
	 *@method:setCurrentUser
	 *@description 此方法描述的是：向session 添加 当前用户
	 */
	public static void setCurrentUser(HttpServletRequest request, 
			HttpServletResponse response, User user) {
		request.getSession().setAttribute(Constants.CURRENT_USER, user);
		HttpSession sess = request.getSession();
		Cookie cookie = new Cookie("JSESSIONID", sess.getId());
		cookie.setMaxAge(sess.getMaxInactiveInterval());
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	/**
	 * 
	 *@description 此方法描述的是：想session 添加临时属性
	 *@author mf-luozg 
	 *@version 2014年12月6日上午12:12:43.
	 *@param [String] key 
	 *@param [String] value 
	 */
	public static void setCurrentTemp(HttpServletRequest request, 
			HttpServletResponse response, String key, Object value ) {
		request.getSession().setAttribute( key , value);
		HttpSession sess = request.getSession();
		Cookie cookie = new Cookie("JSESSIONID", sess.getId());
		cookie.setMaxAge(sess.getMaxInactiveInterval());
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 
	 *@description 此方法描述的是：得到session 保存user
	 *@author  mf [lijie] 
	 *@version 2013-12-26下午01:51:06.
	 */

	public static User getCurrenUser(HttpServletRequest request){
		Object o  = request.getSession().getAttribute(Constants.CURRENT_USER);
		return o == null ? null : (User) o;
	}
	
	public static Object getCurrenTemp(HttpServletRequest request, String key){
		Object obj  = request.getSession().getAttribute( key );
		return obj;
	}
	
	/**
	 * 
	 *@author (作者): luozg 
	 *@date 日期： 2013年12月26日下午4:16:52.
	 *@method:clearCurrentUser
	 *@description 此方法描述的是：删除session 中当前用户
	 */
	public static void clearCurrentUser( HttpServletRequest request ,HttpServletResponse response ){
		request.getSession().invalidate();
		clear(request, response);
	}
	/**
	 * 
	 *@author (作者): luozg 
	 *@date 日期： 2016年12月12日上午11:06:29.
	 *@method:setCurrSchoolConfig
	 *@description 此方法描述的是：
	 */
	public static void setCurrSchoolConfig(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> sm) {
		request.getSession().setAttribute(Constants.CURRENT_SCHOOL_CONFIG, sm);
	}
	/**
	 * 
	 *@author (作者): luozg 
	 *@date 日期： 2016年12月12日上午10:24:32.
	 *@method:getStylePath
	 *@description 此方法描述的是：获取学校的样式报路径
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getStylePath( HttpServletRequest request ) {
		Map<String, Object> map = (Map<String, Object>)request.getSession().getAttribute(Constants.CURRENT_SCHOOL_CONFIG);
		return map;
	}
	/**
	 * 
	 *@author (作者): luozg 
	 *@date 日期： 2014年1月3日上午11:24:00.
	 *@method:clear
	 *@description 此方法描述的是：清除全部cookies 
	 */
	public static void clear(HttpServletRequest req,HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		if( cookies!=null ){
			for(int i = 0,len = cookies.length; i < len; i++) {
				Cookie cookie = new Cookie(cookies[i].getName(),null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				res.addCookie(cookie);
			}
		}
	}

	public static String getIpAddr( HttpServletRequest request ) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
            	//根据网卡取本机配置的IP
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ip= inet.getHostAddress();  
            } 
        }
        //多重代理下获取IP
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15  
            if(ip.indexOf(",")>0){  
                ip = ip.substring(0,ip.indexOf(","));  
            }  
        }  
        return ip;
    }

	
	 /**
	  * 
	  * @description 此方法描述的是 ：删除图片缓存
	  * @author mf-liyp
	  * @version 2014年6月30日
	  */
	public static void deletePrizePicCache(HttpSession session)
	{
		if(session.getAttribute("inputStream") != null)
		{
			InputStream inputStream = (InputStream) session.getAttribute("inputStream");
			File picFile = (File) session.getAttribute("picFile");
			try {
				inputStream.close();
				if(picFile.exists())
				{
					System.out.println(picFile.delete());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally
			{
				session.removeAttribute("inputStream");
				session.removeAttribute("picFile");
			}
		}
	}
	
}
