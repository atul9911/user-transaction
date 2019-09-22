package utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class NullOrEmptyCheckerUtil {

  public static boolean isNullOrEmpty(String str) {
    return StringUtils.isBlank(str);
  }

  public static <T> boolean isNullOrEmpty(List<T> list) {
    return (list == null || list.isEmpty());
  }

  public static boolean isNullOrEmpty(Map<?, ?> map) {
    return (map == null || map.isEmpty());
  }

  public static boolean isNullOrEmpty(Object object) {
    return object == null;
  }

  public static boolean isNullOrEmpty(Object[] arrayObject) {
    return (arrayObject == null || arrayObject.length == 0);
  }
}
