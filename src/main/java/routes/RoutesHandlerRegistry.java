package routes;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import pojo.Statement;
import pojo.Transaction;
import pojo.User;
import pojo.Wallet;

public class RoutesHandlerRegistry {
    private HttpString header;
    private String value;
    private HttpHandler next;

    public void SetHeaderHandler(final HttpHandler next, final String header, final String value) {
        this.next = next;
        this.value = value;
        this.header = new HttpString(header);
    }


    public static User createUserHandler(HttpServerExchange httpServerExchange) throws Exception {
        return null;
    }


    public static User validateUserHandler(HttpServerExchange httpServerExchange) throws Exception {
        return null;
    }


    public static Statement checkUserStatement(HttpServerExchange httpServerExchange) throws Exception {
        return null;
    }


    public static Transaction makeTransaction(HttpServerExchange httpServerExchange) throws Exception {
        return null;
    }


    public static Wallet getWallet(HttpServerExchange httpServerExchange) throws Exception {
        return null;
    }
}
