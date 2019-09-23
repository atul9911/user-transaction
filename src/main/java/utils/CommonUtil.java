package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

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
