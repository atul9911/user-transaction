package routes;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import pojo.UserPojo;
import service.BaseServiceRegistry;
import service.UserService;
import utils.CommonUtil;

class RoutesHandler {

  private static final UserService userService;

  static  {
    userService = (UserService) BaseServiceRegistry.getService("user");
  }


  public static void createUserHandler(HttpServerExchange httpServerExchange) throws Exception {
    httpServerExchange.startBlocking();
    UserPojo userPojo = CommonUtil
        .getClassObjectFromStream(IOUtils
                .toString(httpServerExchange.getInputStream(), StandardCharsets.UTF_8.name()),
            UserPojo.class);
    Integer id = userService.addUser(userPojo);
    Map<String,Integer> responseMap = new HashMap<>();
    responseMap.put("Id",id);
    httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
    httpServerExchange.getResponseSender()
        .send(CommonUtil.objectMapper.writeValueAsString(responseMap));
  }


  public static void validateUserHandler(HttpServerExchange exchange) {
    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
    exchange.getResponseSender().send("{res:\"Hello World\"}");
  }


  public static void checkUserStatement() {
  }


  public static void makeTransaction() {

  }


  public static void getWallet() {
  }

}
