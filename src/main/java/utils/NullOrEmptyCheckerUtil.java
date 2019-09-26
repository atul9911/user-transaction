package utils;

import org.apache.commons.lang3.StringUtils;

public class NullOrEmptyCheckerUtil {

  public static boolean isNullOrEmpty(String str) {
    return StringUtils.isBlank(str);
  }

  public static boolean isNullOrEmpty(Object object) {
    return object == null;
  }

}
