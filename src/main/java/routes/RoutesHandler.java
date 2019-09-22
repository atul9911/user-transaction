package routes;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import pojo.UserPojo;
import service.BaseServiceRegistry;
import service.UserService;
import utils.CommonUtil;

public class RoutesHandler {

  static UserService userService;

  static  {
    userService = (UserService) BaseServiceRegistry.getService("user");
  }

  private HttpString header;
  private String value;
  private HttpHandler next;

  public void setHeaderHandler(final HttpHandler next, final String header, final String value) {
    this.next = next;
    this.value = value;
    this.header = new HttpString(header);
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


  public static void validateUserHandler(HttpServerExchange exchange) throws Exception {
    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
    exchange.getResponseSender().send("{res:\"Hello World\"}");
  }


  public static void checkUserStatement(HttpServerExchange httpServerExchange)
      throws Exception {
  }


  public static void makeTransaction(HttpServerExchange httpServerExchange)
      throws Exception {

  }


  public static void getWallet(HttpServerExchange httpServerExchange) throws Exception {
  }

}
