package mojiayi.learn.commons.lang;

import org.apache.commons.lang.StringUtils;

public class StringHelper {
	public static boolean isEmpty(String str) {
		if ("null".equalsIgnoreCase(str) || StringUtils.isBlank(str)) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
