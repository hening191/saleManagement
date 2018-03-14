package com.hening.sale.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 字符串的一些处理方法UTIL类
 */
public final class UtilString {
	/** HTML code 中的'...' */
	public static String ELLIPSES = "&#133";

	public static final String EMPTY_STRING = "";	
	
	/**
	 * 分割关键字
	 * @param keys
	 * @return
	 */
	public static String [] splitKeyword(String keys) {
		return keys.split("(\\,|\\s|，|\\.|。)+");
	}
	/**
	 * 将Clob类型转换成String类型
	 * @param c Clob类型数据
	 * @return 转换后的字符串数据
	 */
	public static String colbToString(Clob c) {
		String result = "";
		try {
			long len;
			len = c.length();
			char b[] = new char[(int) len];
			Reader is = c.getCharacterStream();
			is.read(b);
			result = new String(b);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 比较两个字符串的大小
	 * @param str1
	 * @param str2
	 * @return 如果str1与str2相同返回0<br> 
	 *         如果str1大于str2返回大于0数<br>
	 *         如果str1小于str2返回小于0的数<br>
	 *         如果str1 == null&&str2！= null 返回小于0的数
	 */
	public static int compareString(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return 0;
		}
		if(str1 == null) {
			return -1;
		}
		if(str2 == null) {
			return 1;
		}
		return str1.compareTo(str2);
	} 

	/**
	 * 判断两个字符串是否相同
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEqual(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * 使用字符串代表true/false时的转换, true为y, yes, true, 或1
	 * 
	 * @param theString
	 *            转为boolean 的字符串
	 * @return boolean 转换结果
	 */
	public static boolean toBoolean(String theString) {
		if (theString == null) {
			return false;
		}

		theString = theString.trim();
		if (theString.equalsIgnoreCase("y")
				|| theString.equalsIgnoreCase("yes")
				|| theString.equalsIgnoreCase("true")
				|| theString.equalsIgnoreCase("1")) {
			return true;
		}

		return false;
	}

	/**
	 * 将一个字符串的某个子串全部替换
	 * 
	 * @param s
	 *            原始字符串
	 * @param sub
	 *            要被替换的子串
	 * @param with
	 *            替换旧子串的新子串
	 * @return 被替换后的新字符串
	 */
	public static String replace(String s, String sub, String with) {
		int c = 0;
		int i = s.indexOf(sub, c);

		if (i == -1) {
			return s;
		}

		StringBuffer buf = new StringBuffer(s.length() + with.length());
		do {
			buf.append(s.substring(c, i));
			buf.append(with);
			c = i + sub.length();
		} while ((i = s.indexOf(sub, c)) != -1);

		if (c < s.length()) {
			buf.append(s.substring(c, s.length()));
		}

		return buf.toString();
	}

	/**
	 * 将一个字符串格式化为一个XML/XHTML escaped character， 如替换掉> <
	 * 
	 * @param s
	 *            要被格式化的字符串
	 * @return 格式化后的字符串
	 */
	public static String xmlEscape(String s) {
		int length = s.length();
		StringBuffer fsb = new StringBuffer(length);

		for (int i = 0; i < length; i++) {
			fsb = printEscaped(s.charAt(i), fsb);
		}

		return fsb.toString();
	}

	/**
	 * 截取字符串, 用于一些显示时控制长度使用, 截取后加上"..."
	 * 
	 * @param str
	 *            要被截取的字符串
	 * @param len
	 *            要保留的最大长度
	 * @return 截取后的字符串
	 */
	public static String truncate(String str, int len) {
		String result = str;
		if (str.length() > len) {
			result = str.substring(0, len) + ELLIPSES;
		}
		return result;
	}

	/**
	 * 比较两个数值型的 string
	 * 
	 * @param num1
	 *            要比较的数值1
	 * @param num2
	 *            要比较的数值2
	 * @return 返回 1 , 如果 num1 > num2<BR>
	 *         返回 0 ,如果 num1 == num2<BR>
	 *         返回 -1 ,如果 num1 < num2
	 * @throws ParseException
	 *             无法转换成数值
	 */
	public static int compareNumber(String num1, String num2) throws ParseException {
		BigDecimal dec1 = new BigDecimal(num1);
		BigDecimal dec2 = new BigDecimal(num2);
		return dec1.compareTo(dec2);
	}

	/**
	 * 检查是否是符合条件的字符组成的字符串, 条件是数值0-9和字母a-zA-Z
	 * 
	 * @param s
	 *            被检查的字符串
	 * @return 每一个字符都符合条件返回true
	 */
	public static boolean isAlphaNumeric(String s) {
		return isAlphaNumeric(s, "");
	}

	/**
	 * 检查是否是符合条件的字符组成的字符串, 默认条件是数值0-9和字母a-zA-Z,同时传入一个额外的许可范围
	 * 
	 * @param str
	 *            被检查的字符串
	 * @param otherChars
	 *            额外的字符许可范围
	 * @return 每一个字符都符合条件返回true
	 */
	public static boolean isAlphaNumeric(String str, String otherChars) {
		String alphaNum = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" + otherChars;
		for (int i = 0; i < str.length(); i++) {
			if (alphaNum.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串的值是否是BigDecimal
	 * 
	 * @param s
	 *            被检查的字符串
	 * @return 是时返回true
	 */
	public static boolean isDecimal(String s) {
		try {
			new BigDecimal(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 检查字符串的值是否是Integer
	 * 
	 * @param str
	 *            被检查的字符串
	 * @return 是时返回true
	 */
	public static boolean isInteger(String str) {
		try {
			new BigInteger(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 检查字符串的值是否是一个数值
	 * 
	 * @param str
	 *            被检查的字符串
	 * @return 是时返回true
	 */
	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 检查字符串是否为空
	 */
	public static boolean isEmpty(String s) {
		return ((s == null) || (s.length() == 0));
	}

	/**
	 * 检查字符串是否是一个有效的email地址
	 * 
	 * @param email
	 *            被检查的字符串
	 * @return 有效时返回0, 无效时返回非0 (1表示不是由@分隔的两部分组成, 2表示第二部分没有"."号 int 0 if valid
	 *         address, 1 more than 2 tokens (".", "@")<BR>
	 *         and 2 if the second token is not "." .
	 */
	public static int isEmail(String email) {
		StringTokenizer st = new StringTokenizer(email, "@");

		if (st.countTokens() != 2) {
			return 1;
		}

		st.nextToken();
		if (st.nextToken().indexOf(".") == -1) {
			return 2;
		}

		return 0;
	}

	/**
	 * 将一个逗号分隔好的字符串转为一个list
	 * 
	 * @param s
	 *            被转换的字符串, 用逗号分隔
	 * @return List
	 */
	public static List<String> string2List(String s) {
		return string2List(s, ",");
	}

	/**
	 * 将一个分隔好的字符串转为一个list
	 * 
	 * @param s
	 *            被转换的字符串
	 * @param sep
	 *            分隔符
	 * @return List
	 */
	public static List<String> string2List(String s, String sep) {
		return string2List(s, sep, s != null ? s.length() : Integer.MAX_VALUE);
	}

	/**
	 * 将一个分隔好的字符串转为一个list
	 * 
	 * @param s
	 *            被转换的字符串
	 * @param sep
	 *            分割的字符
	 * @param maxSize
	 *            the maximum size of the list
	 * @return List
	 */
	public static List<String> string2List(String s, String sep, int maxSize) {
		List<String> l = null;
		if (s != null) {
			l = new Vector<String>();
			for (int i = 0; i < maxSize;) {
				int index = s.indexOf(sep, i);
				String token;
				if (index != -1) {
					token = s.substring(i, index);
				} else {
					token = s.substring(i);
				}
				if (token.length() > 0 && !token.equals(sep)) {
					l.add(token.trim());
				}
				i += token.length() + sep.length();
			}
		}
		return l;
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 *            要小写首字母的字符串
	 * @return String
	 */
	public static String unUpperFirstChar(String str) {
		StringBuffer fsb = new StringBuffer();

		fsb.append(Character.toLowerCase(str.charAt(0)));
		fsb.append(str.substring(1));
		return fsb.toString();
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            要大写首字母的字符串
	 * @return String
	 */
	public static String upperFirstChar(String str) {
		StringBuffer fsb = new StringBuffer();

		fsb.append(Character.toTitleCase(str.charAt(0)));
		fsb.append(str.substring(1));
		return fsb.toString();
	}

	/**
	 * 将一个字符串拷贝n份组成一个新串
	 * 
	 * @param str
	 *            要被拷贝的字符串
	 * @param n
	 *            拷贝次数
	 * @return String
	 */
	static public String repeatString(String str, int n) {
		StringBuffer buffer = new StringBuffer();

		int val = n * str.length();
		if (val > buffer.capacity()) {
			buffer.ensureCapacity(val);
		}

		for (int i = 0; i < n; i++) {
			buffer.append(str);
		}
		return buffer.toString();

	}

	/**
	 * 将字符串前后对称加空格,使达到需要的长度,如"abc"变成" abc "
	 * 
	 * @param str
	 *            String str string to center padding
	 * @param n
	 *            需要的字符串的长度
	 * @return String Result
	 */
	static public String centerPad(String str, int n) {
		return centerPad(str, n, " ");
	}

	/**
	 * 将字符串前后对称加指定的字符,使达到需要的长度,如"abc"变成"***abc***"
	 * 
	 * @param str
	 *            String str string to pad with
	 * @param n
	 *            需要的字符串的长度
	 * @param delim
	 *            要添加的字符
	 * @return String result of the center padding
	 */
	static public String centerPad(String str, int n, String delim) {
		int sz = str.length();
		int p = n - sz;
		if (p < 1) {
			return str;
		}
		str = leftPad(str, sz + p / 2, delim);
		str = rightPad(str, n, delim);
		return str;
	}

	/**
	 * 将字符串后面加指定的字符,使达到需要的长度,如"abc"变成"abc***"
	 * 
	 * @param str
	 *            String
	 * @param n
	 *            需要的字符串的长度
	 * @param delim
	 *            要添加的字符
	 * @return String padding string
	 */
	static public String rightPad(String str, int n, String delim) {
		int sz = str.length();
		n = (n - sz) / delim.length();
		if (n > 0) {
			str += repeatString(delim, n);
		}
		return str;
	}

	/**
	 * 将字符串后面加空格,使达到需要的长度,如"abc"变成"abc "
	 * 
	 * @param str
	 *            String
	 * @param n
	 *            需要的字符串的长度
	 * @return String
	 */
	static public String rightPad(String str, int n) {
		return rightPad(str, n, " ");
	}

	/**
	 * 将字符串前面加空格,使达到需要的长度,如"abc"变成" abc"
	 * 
	 * @param str
	 *            String
	 * @param n
	 *            需要的字符串的长度
	 * @return String
	 */
	static public String leftPad(String str, int n) {
		return leftPad(str, n, " ");
	}

	/**
	 * 将字符串前面加指定的字符,使达到需要的长度,如"abc"变成" abc"
	 * 
	 * @param str
	 *            String
	 * @param n
	 *            需要的字符串的长度
	 * @param delim
	 *            要添加的字符
	 * @return String
	 */
	static public String leftPad(String str, int n, String delim) {
		int sz = str.length();
		n = (n - sz) / delim.length();
		if (n > 0) {
			str = repeatString(delim, n) + str;
		}
		return str;
	}

	/**
	 * 如果字符串为空,用""表示
	 */
	public static String nullToEmpty(String s) {
		if (s == null || s.equalsIgnoreCase("null")) {
			return "";
		} else {
			return s;
		}
	}

	/**
	 * 如果字符串为空,用"0"表示
	 */
	public static String nullToZero(Object s) {
		if (s == null || (String.valueOf(s)).equalsIgnoreCase("null"))
			return "0";
		else
			return String.valueOf(s).trim();
	}

	/**
	 * 从头获取一定长度的子串,如果为空,或需要的长度不够,返回null
	 * 
	 * @param str
	 *            原始字符串
	 * @param lg
	 *            需要的子串长度
	 * @return 子串
	 */
	public static String substring(String str, int lg) {
		return substring(str, 0, lg);
	}

	/**
	 * 获取子串,如果为空,或需要的长度不够,返回null
	 * 
	 * @param str
	 *            原始字符串
	 * @param start
	 *            子串的开始位置
	 * @param end
	 *            子串的结束位置
	 * @return 子串
	 */
	public static String substring(String str, int start, int end) {
		if (str == null || str.length() <= start) {
			return null;
		} else if (str.length() >= end) {
			return str.substring(start, end);
		} else {
			return str.substring(start);
		}
	}

	/**
	 * 反转字符串, 如"abc"变成"cba"
	 * 
	 * @param str
	 *            要反转的字符串
	 * @return 反转后的字符串
	 */
	public static String reverseString(String str) {
		StringBuffer fsb = new StringBuffer();
		try {
			fsb.append(str);
			return fsb.reverse().toString();
		} finally {

		}
	}

	/**
	 * 反转字符串
	 */
	public static String swapCase(String str) {
		int sz = str.length();
		StringBuffer buffer = new StringBuffer();

		try {
			if (sz > buffer.capacity()) {
				buffer.ensureCapacity(sz);
			}
			boolean whiteSpace = false;
			char ch = 0;
			char tmp = 0;
			for (int i = 0; i < sz; i++) {
				ch = str.charAt(i);
				if (Character.isUpperCase(ch)) {
					tmp = Character.toLowerCase(ch);
				} else if (Character.isTitleCase(ch)) {
					tmp = Character.toLowerCase(ch);
				} else if (Character.isLowerCase(ch)) {
					if (whiteSpace) {
						tmp = Character.toTitleCase(ch);
					} else {
						tmp = Character.toUpperCase(ch);
					}
				}
				buffer.append(tmp);
				whiteSpace = Character.isWhitespace(ch);
			}
			return buffer.toString();
		} finally {
		}
	}

	/**
	 * 产生一个随机字符串
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @return 随机字符串
	 */
	public static String random(int count) {
		return random(count, false, false);
	}

	/**
	 * 产生一个随机ASCII码字符串,如1c6x^9X7\G
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @return 随机字符串
	 */
	public static String randomAscii(int count) {
		return random(count, 32, 127, false, false);
	}

	/**
	 * 产生一个随机字符串, 只由字母组成, 如lKQpeyoACJ
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @return 随机字符串
	 */
	public static String randomAlphabetic(int count) {
		return random(count, true, false);
	}

	/**
	 * 产生一个随机字符串, 只由数值和字母组成, 如yFBPfo9oF9
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @return 随机字符串
	 */
	public static String randomAlphanumeric(int count) {
		return random(count, true, true);
	}

	/**
	 * 产生一个随机字符串, 只由数值组成, 如8879114164
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @return 随机字符串
	 */
	public static String randomNumeric(int count) {
		return random(count, false, true);
	}

	/**
	 * 产生一个随机字符串
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @param set
	 *            许可的字符集
	 * @return 随机字符串
	 */
	public static String random(int count, String set) {
		return random(count, set.toCharArray());
	}

	/**
	 * 替换所有html的tag, 检查总长度是否超过了系统的许可,以防止黑客长字符的攻击
	 * 
	 * @param strSrc
	 *            要替换的字符串
	 * @param lngStrLen
	 *            系统容许的最大长度
	 */
	public static String getFilterStr(String strSrc, int lngStrLen) {
		String strRst = strSrc;
		// 替换不合法字符
		strRst = replace(strRst, "<", "&lt;");
		strRst = replace(strRst, ">", "&gt;");
		strRst = replace(strRst, " ", "&nbsp;");
		strRst = replace(strRst, "\n", "<br>");
		strRst = replace(strRst, "\r", "");

		// 截取不必要的长度
		if (lngStrLen > 0) {
			if (lngStrLen > strRst.length()) {
				lngStrLen = strRst.length();
			}
			strRst = strRst.substring(0, lngStrLen);
		}
		return strRst;
	}

	/**
	 * 产生一个随机字符串
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @param letters
	 *            是否需要字母
	 * @param numbers
	 *            是否需要数字
	 * @return 随机字符串
	 */
	private static String random(int count, boolean letters, boolean numbers) {
		return random(count, 0, 0, letters, numbers);
	}

	/**
	 * 产生一个随机字符串
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @param start
	 *            int minimum 'value' of the character
	 * @param end
	 *            maximum 'value' of the character
	 * @param letters
	 *            是否需要字母
	 * @param numbers
	 *            是否需要数字
	 * @return 随机字符串
	 */
	private static String random(int count, int start, int end, boolean letters, boolean numbers) {
		return random(count, start, end, letters, numbers, null);
	}

	/**
	 * Create a random numeric string where you have control over size, and
	 * whether you want letters, numbers, as well as ANSI minimum and maximum
	 * values of the characters.
	 * 
	 * @param count
	 *            the size of the string
	 * @param start
	 *            int minimum 'value' of the character
	 * @param end
	 *            maximum 'value' of the character
	 * @param letters
	 *            是否需要字母
	 * @param numbers
	 *            是否需要数字
	 * @param set
	 *            the set of possible characters that you're willing to let the
	 *            string contain. may be null if all values are open.
	 * @return 随机字符串
	 */
	private static String random(int count, int start, int end, boolean letters, boolean numbers, char[] set) {
		if ((start == 0) && (end == 0)) {
			end = 'z';
			start = ' ';
			if (!letters && !numbers) {
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}
		Random rnd = new Random();
		StringBuffer buffer = new StringBuffer();
		try {
			int gap = end - start;
			while (count-- != 0) {
				char ch;
				if (set == null) {
					ch = (char) (rnd.nextInt(gap) + start);
				} else {
					ch = set[rnd.nextInt(gap) + start];
				}
				if ((letters && numbers && Character.isLetterOrDigit(ch)) || (letters && Character.isLetter(ch)) || (numbers && Character.isDigit(ch)) || (!letters && !numbers)) {
					buffer.append(ch);
				} else {
					count++;
				}
			}
			return buffer.toString();
		} finally {
		}
	}

	/**
	 * 产生一个随机字符串
	 * 
	 * @param count
	 *            需要的字符串的长度
	 * @param set
	 *            许可的字符集
	 * @return 随机字符串
	 */
	private static String random(int count, char[] set) {
		return random(count, 0, set.length - 1, false, false, set);
	}

	/**
	 * Formats a particular character to something workable in xml Helper to
	 * xmlEscape()
	 * 
	 * @param ch
	 *            the character to print.
	 * @param fsb
	 *            The StringBuffer to add this to.
	 * @return a StringBuffer that is modified
	 */
	protected static StringBuffer printEscaped(char ch, StringBuffer fsb) {
		String charRef;

		// If there is a suitable entity reference for this
		// character, print it. The list of available entity
		// references is almost but not identical between
		// XML and HTML.
		charRef = getEntityRef(ch);

		if (charRef != null) {
			fsb.append('&');
			fsb.append(charRef);
			fsb.append(';');
		} else if ((ch >= ' ' && ch < 0xFF && ch != 0xF7) || ch == '\n' || ch == '\r' || ch == '\t') {
			// If the character is not printable, print as character reference.
			// Non printables are below ASCII space but not tab or line
			// terminator, ASCII delete, or above a certain Unicode threshold.
			if (ch < 0x10000) {
				fsb.append(ch);
			} else {
				fsb.append((char) (((ch - 0x10000) >> 10) + 0xd800));
				fsb.append((char) (((ch - 0x10000) & 0x3ff) + 0xdc00));
			}
		} else {
			fsb.append("&#x");
			fsb.append(Integer.toHexString(ch));
			fsb.append(';');
		}

		return fsb;
	}

	/**
	 * Helper to xmlEscape()
	 * 
	 * @param ch
	 *            the character to escape
	 * @return A modified string representing the tanlation of the character or
	 *         null if there is no translation for it.
	 */
	protected static String getEntityRef(int ch) {
		// Encode special XML characters into the equivalent character
		// references.
		// These five are defined by default for all XML documents.
		switch (ch) {
		case '<':
			return "lt";
		case '>':
			return "gt";
		case '"':
			return "quot";
		case '\'':
			return "apos";
		case '&':
			return "amp";
		}
		return null;
	}

	/**
	 * 将字符集转换成整型
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return int 转化后的整数
	 */
	public static int toInt(String str) {
		int lpResult = 0;
		try {
			lpResult = Integer.parseInt(str);
		} catch (NumberFormatException notint) {
		}
		return lpResult;
	}

	/**
	 * 将字符串转化为float类型
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return float 转化后的数值
	 */
	public static float toFloat(String str) {
		float lpResult = 0;

		try {
			lpResult = Float.parseFloat(nullToZero(str));
		} catch (NumberFormatException nfe) {
		}
		return lpResult;
	}

	/**
	 * 将字符串转化为long类型
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return long 转化后的数值
	 */
	public static short toShort(String str) {
		short lpResult = 0;

		try {
			lpResult = Short.parseShort(nullToZero(str));
		} catch (NumberFormatException nfe) {
		}
		return lpResult;
	}

	/**
	 * 将字符串转化为long类型
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return long 转化后的数值
	 */
	public static long toLong(String str) {
		long lpResult = 0;

		try {
			lpResult = Long.parseLong(nullToZero(str));
		} catch (NumberFormatException nfe) {
		}
		return lpResult;
	}

	/**
	 * 将字符串转化为double类型
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return double 转化后的数值
	 */
	public static double toDouble(String str) {
		double lpResult = 0;

		try {
			lpResult = Double.parseDouble(nullToZero(str));
		} catch (NumberFormatException nfe) {
		}
		return lpResult;
	}

	/**
	 * 将字符串转化为BigDecimal类型
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return BigDecimal 转化后的数值
	 */
	public static BigDecimal toBigDecimal(String str) {
		java.math.BigDecimal lpReturnValue = null;; 
		try {
			lpReturnValue = new BigDecimal(nullToZero(str));
		} catch (Exception nfe) {
			lpReturnValue = new java.math.BigDecimal("0");
		}
		return lpReturnValue;
	}

	/**
	 * 将整数转化为指定长度字符串(如：1 --> 00001，则lpMaxLength为5)
	 * 
	 * @param lpInt
	 *            整数值
	 * @param lpMaxLength
	 *            字符串长度
	 * @return String 转化后的字符串
	 */
	public static String intToStr(int lpInt, int lpMaxLength) {
		int length, i;
		String returnValue = "";

		length = Integer.toString(lpInt).length();
		if (length < lpMaxLength) {
			i = lpMaxLength - length;
			while (i > 0) {
				returnValue = returnValue + "0";
				i--;
			}
			returnValue = returnValue + Integer.toString(lpInt);
		} else {
			returnValue = Integer.toString(lpInt);
		}
		return returnValue;
	}
	
	/**
	 * 
	 * @param in 输入的String
	 * @param maxSize 拆分的长度
	 * @return list
	 */
	public static List<String> stringToList( String in,int maxSize){
		int listSize = in.length();
		int CycTimes = listSize/maxSize;
		int residual = listSize%maxSize;
		if( residual == 0){
			CycTimes--;
		}
		List<String> li = new ArrayList<String>();
		for(int j=0;j<=CycTimes;j++){
			if(j!=CycTimes){
				if(residual==0){
					li.add(in.substring(maxSize*j,  listSize-(maxSize*(CycTimes-j))));
				}else{
					li.add(in.substring(maxSize*j,  listSize-(maxSize*(CycTimes-j-1))-residual));
				}
			}else{
				li.add(in.substring(maxSize*j, listSize));
				}
		}
		return li;
	}
	
	/**
     * 将blob转为byte[]
     * 
     * @param blob
     * @return
     */
    public static byte[] blobToBytes(Blob blob){
        BufferedInputStream is = null;
        try{
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0){
                offset += read;
            }
            return bytes;
        } catch (Exception e){
        	System.out.println(e);
            return null;
        } finally{
            try{
                is.close();
                is = null;
            } catch (IOException e){
                return null;
            }

        }
    }
    
    /**
     * 增加单引号，转db2 保持数据一致性，如：11114,11115,34343  转化为 '11114','11115','34343'
     * @param str
     * @return
     */
    public static String addSingleQuote(String str) {
    	if (null == str || "".equals(str) || (-1 < str.indexOf("'"))) return str;
    	boolean hasBracket = false;
    	//增加对有"()"的处理
    	if (-1 < str.indexOf("(") && -1 < str.indexOf(")")) {
    		hasBracket = true;
    		str = str.substring(1,str.length() - 1);
    	}
    	String[] strArr = str.split(",");
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < strArr.length; i++) {
    		sb.append("'").append(strArr[i]).append("',");
    	}
    	if (hasBracket) {
        	return "(" + sb.substring(0, sb.length() - 1) + ")";
    	}
    	return sb.substring(0, sb.length() - 1);
    }
    
    /**
     * 删除单引号，转db2 保持数据一致性，如：'11114','11115','34343'  转化为 11114,11115,34343
     * @param str
     * @return
     */
    public static String delSingleQuote(String str) {
    	if (null == str || "".equals(str)) return str;
    	return str.replaceAll("'", "");
    }
    
    /**
	 * 把金额单位为分时转化成元的字符串
	 * @param str
	 * @return
	 */
	public static String getMoneyStr(String str){
		String temp = "";
		String mark = "";
		
		if(isEmpty(str)){
			return "0";
		} 
		else {
			str = str.trim();
			if (-1 < str.indexOf("-")) {
				str = str.substring(1);
				mark = "-";
			}
		}
		int len = str.length();		
		if(len == 1){
			temp = "0.0"+str;
		}
		else if(len == 2){
			temp = "0."+str;
		}
		else if(len > 2){
			temp = str.substring(0, len-2) + "." + str.substring(len-2);
		}
		temp = mark + temp;
		return temp;
	}

	/**
	 * 对返回的金额单位为元的数据进行处理
	 * @param str
	 * @return
	 */
	public static String getMoneyStr2(String str){
		String temp = "";
		BigDecimal money = null;
		
		if(str==null || "".equals(str.trim())){
			return "0";
		} else {
			str = str.trim();
		}
		temp = str;
		money = toBigDecimal(temp);
		temp = money.toString();
		return temp;
	}
	
    /**
     * 从目标字符串中查找子字符串
     * @param str 目标字符串
     * @param substr 待查找的子字符串
     * @return
     */
    public static boolean isExist(String str, String substr) {
        return isExist(str, substr, ",");
    }	
    /**
     * 从目标字符串中查找指定的子字符串
     * @param str  目标字符串
     * @param substr 要查找的字符串
     * @param sepatator 分隔符
     * @return
     */
    public static boolean isExist(String str, String substr, String sepatator) {
        if (str == null || str.trim().equals("")) return false;
        if (substr == null || substr.trim().equals("")) return false;
        String[] strArr = str.split(sepatator);
        int size = strArr.length;
        for (int i = 0; i < size; i++) {
            if (strArr[i].equals(substr)) return true;
        }
        return false;
    }
}