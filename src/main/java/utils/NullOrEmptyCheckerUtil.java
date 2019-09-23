package utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class NullOrEmptyCheckerUtil {

  public static boolean isNullOrEmpty(String str) {
    return StringUtils.isBlank(str);
  }

  public static boolean isNullOrEmpty(Object object) {
    return object == null;
  }

}
