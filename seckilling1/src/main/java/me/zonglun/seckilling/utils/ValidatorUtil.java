package me.zonglun.seckilling.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidatorUtil {

	/**
	 * 手机号码正则表达式
	 */
	private static final String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

	private static final Pattern mobile_pattern = Pattern.compile(PHONE_NUMBER_REG);
	
	public static boolean isMobile(String src) {
		if(StringUtils.isEmpty(src)) {
			return false;
		}
		Matcher m = mobile_pattern.matcher(src);
		return m.matches();
	}
	
	/*public static void main(String[] args) {
			System.out.println(isMobile("18912341234"));
			System.out.println(isMobile("15423456670"));
			System.out.println(isMobile("15377601252"));
			System.out.println(isMobile("1891234123"));
	}*/
}
