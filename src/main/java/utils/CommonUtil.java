package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {

  public static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }

  private CommonUtil() {

  }

  public static <T> T getClassObjectFromStream(String sting, Class<T> responseClass) throws Exception {
    return objectMapper.readValue(sting, responseClass);
  }

}
