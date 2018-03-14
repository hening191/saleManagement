package com.hening.sale.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 *@company 美福科技
 *@ClassName StringUtils
 *@author mf-luozg 
 *@date 2014年10月14日上午1:16:44
 */
@SuppressWarnings("all")
public final class StringUtils {

	/**生成四位验证码
	 * @return   
	*/
	public static String createCode() {
		Random random = new Random();
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		for (int i = 0; i < 4; i++) {
			String strRand = String.valueOf(random.nextInt(10));
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	/**文件上传
	 * 获得上传文件名字（毫秒+随机数）,有后缀名
	 * @param fileName 
	 * @return
	 */
	public static String getNewName(String oldFileName) {
		String newName = null;
		String format = oldFileName.substring(oldFileName.lastIndexOf(".")+1);
		newName = getFileName() + "." + format;
		return newName;
	}

	/**文件上传
	 * 获得上传文件名字（毫秒+随机数）,无后缀名
	 * @return
	 */
	public static String getFileName() {
		String fileName = null;
		String strDate = DateUtil.dateFormatString(new Date(), "yyyyMMddhhmmssSSS");
		fileName = strDate + getRandom();
		return fileName;
	}

	/**文件上传
	 * 获得随机数
	 * @return
	 */
	public static String getRandom() {
		Double douRandom = Math.random() * 10000000;
		String strRandom = douRandom.toString();
		return strRandom.substring(0, 5).replace(".", "");
	}

	/**
	 * 默认的空值
	 */
	public static final String EMPTY = "";

	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 截取并保留标志位之前的字符串
	 * @param str 字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringBefore(String str, String expr) {
		if (isEmpty(str) || expr == null) {
			return str;
		}
		if (expr.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(expr);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * 截取并保留标志位之后的字符串
	 * @param str 字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringAfter(String str, String expr) {
		if (isEmpty(str)) {
			return str;
		}
		if (expr == null) {
			return EMPTY;
		}
		int pos = str.indexOf(expr);
		if (pos == -1) {
			return EMPTY;
		}
		return str.substring(pos + expr.length());
	}

	/**
	 * 截取并保留最后一个标志位之前的字符串
	 * @param str 字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringBeforeLast(String str, String expr) {
		if (isEmpty(str) || isEmpty(expr)) {
			return str;
		}
		int pos = str.lastIndexOf(expr);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * 截取并保留最后一个标志位之后的字符串
	 * @param str
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringAfterLast(String str, String expr) {
		if (isEmpty(str)) {
			return str;
		}
		if (isEmpty(expr)) {
			return EMPTY;
		}
		int pos = str.lastIndexOf(expr);
		if (pos == -1 || pos == (str.length() - expr.length())) {
			return EMPTY;
		}
		return str.substring(pos + expr.length());
	}

	/**
	 * 把字符串按分隔符转换为数组
	 * @param string 字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String[] stringToArray(String string, String expr) {
		return string.split(expr);
	}

	/**
	 * 去除字符串中的空格
	 * @param str
	 * @return
	 */
	public static String noSpace(String str) {
		str = str.trim();
		str = str.replace(" ", "_");
		return str;
	}

	/**
	 * 将CLOB数据转化成STRING型
	 * @param clob
	 * @return
	 * String
	 */
	public static String clobToString(Clob clob) {
		StringBuffer sbResult = new StringBuffer();
		Reader isClob = null;
		if (clob != null) {
			try {
				isClob = clob.getCharacterStream();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			BufferedReader bfClob = new BufferedReader(isClob);
			String strClob = "";
			try {
				strClob = bfClob.readLine();
				while (strClob != null) {
					sbResult.append(strClob + "\n");
					strClob = bfClob.readLine();
				}
				bfClob.close();
				isClob.close();
			} catch (IOException ex1) {
			}
		}
		return sbResult.toString();
	}

	/**
	 * 将text型转化成html
	 * @param sourcestr
	 * @return
	 * String
	 */
	public static String TextToHtml(String sourcestr) {
		int strlen;
		String restring = "", destr = "";
		strlen = sourcestr.length();
		for (int i = 0; i < strlen; i++) {
			char ch = sourcestr.charAt(i);
			switch (ch) {
			case '<':
				destr = "&lt;";
				break;
			case '>':
				destr = "&gt;";
				break;
			case '\"':
				destr = "&quot;";
				break;
			case '&':
				destr = "&amp;";
				break;
			case '\n':
				destr = "<br>";
				break;
			case '\r':
				destr = "<br>";
				break;
			case 32:
				destr = "&nbsp;";
				break;
			default:
				destr = "" + ch;
				break;
			}
			restring = restring + destr;
		}
		return "" + restring;
	}

	public static String htmlEsacpe(String s) {
        if (s == null || s.length() == 0) return s;
        s = replace(s, "&", "&amp;");
        s = replace(s, "\"", "&#34;");
        s = replace(s, "'", "&#39;");
        s = replace(s, "<", "&#60;");
        s = replace(s, ">", "&#62;");
        s = replace(s, " ", "&nbsp;");
        s = replace(s, "\n", "<br/>");
        return s;
    }
	
	public static String replace(String s, String oldSymbol, String newSymbol) {
        if (s == null || s.indexOf(oldSymbol) == -1) return s;
        int oldLength = oldSymbol.length();
        StringBuilder sb = new StringBuilder("");
        int i = s.indexOf(oldSymbol,0);
        int lastIndex = 0;
        while (i != -1) {
            sb.append(s.substring(lastIndex,i)).append(newSymbol);
            lastIndex = i + oldLength;
            i = s.indexOf(oldSymbol, lastIndex);
        }
        sb.append(s.substring(lastIndex));
        return sb.toString();
    }
	
	public static String htmlToText(String inputString) {
		if( inputString == null ) return "";
		String htmlStr = inputString;
		String textStr = "";
		String scriptRegEx = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		String styleRegEx = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		String htmlRegEx1 = "<[^>]*>";
		String htmlRegEx2 = "<[^>]*";
		try {
			Pattern scriptPattern = Pattern.compile(scriptRegEx,
					Pattern.CASE_INSENSITIVE);
			Matcher scriptMatcher = scriptPattern.matcher(htmlStr);
			htmlStr = scriptMatcher.replaceAll("");
			Pattern stylePattern = Pattern.compile(styleRegEx,
					Pattern.CASE_INSENSITIVE);
			Matcher styleMatcher = stylePattern.matcher(htmlStr);
			htmlStr = styleMatcher.replaceAll("");
			Pattern htmlPattern1 = Pattern.compile(htmlRegEx1,
					Pattern.CASE_INSENSITIVE);
			Matcher htmlMatcher1 = htmlPattern1.matcher(htmlStr);
			htmlStr = htmlMatcher1.replaceAll("");
			Pattern htmlPattern2 = Pattern.compile(htmlRegEx2,
					Pattern.CASE_INSENSITIVE);
			Matcher htmlMatcher2 = htmlPattern2.matcher(htmlStr);
			htmlStr = htmlMatcher2.replaceAll("");
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("->htmlToText(String inputString):" + e.getMessage());
		}
		textStr = textStr.replaceAll("&acute;", "\'");
		textStr = textStr.replaceAll("&quot;", "\"");
		textStr = textStr.replaceAll("&lt;", "<");
		textStr = textStr.replaceAll("&gt;", ">");
		textStr = textStr.replaceAll("&nbsp;", " ");
		textStr = textStr.replaceAll("&amp;", "&");
		return textStr;
	}
	
	
	private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
        "W", "X", "Y", "Z" };  
	public static String generateShortUuid() {  
		StringBuffer shortBuffer = new StringBuffer();  
		String uuid = UUID.randomUUID().toString().replace("-", "");  
		for (int i = 0; i < 8; i++) {  
		    String str = uuid.substring(i * 4, i * 4 + 4);  
		    int x = Integer.parseInt(str, 16);  
		    shortBuffer.append(chars[x % 0x3E]);  
		}  
		return shortBuffer.toString();  
	} 
	
	public static int compareVersion(String version1, String version2) throws Exception {  
	    if (version1 == null || version2 == null) {  
	        throw new Exception("compareVersion error:illegal params.");  
	    }  
	    String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；  
	    String[] versionArray2 = version2.split("\\.");  
	    int idx = 0;  
	    int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
	    int diff = 0;  
	    while (idx < minLength  
	            && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
	            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符  
	        ++idx;  
	    }  
	    //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
	    diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;  
	    return diff;  
	}  
	
	
	public static void main(String[] args) {
		//System.out.println(substringAfterLast("fwun.ixref.pdf", "."));
		//System.out.println(isEmpty(null));
		//System.out.println(createCode());
		try {
			System.out.println(compareVersion("1.12.111", "1.2.1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String s = "1.2.3"; 
		String[] ss = s.split("\\.");
		System.out.println(ss.length);
	}

}
