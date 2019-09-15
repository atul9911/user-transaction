package exception;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomExceptionHandler {
    public static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    public static ExceptionHandler exceptionHandler(HttpHandler handler){
        return Handlers.exceptionHandler((HttpServerExchange exchange) -> {
            try {
                handler.handleRequest(exchange);
            } catch (Throwable th) {
                log.error("exception thrown at " + exchange.getRequestURI(), th);
                throw th;
            }
        }).addExceptionHandler(Throwable.class,(httpServerExchange -> {
            httpServerExchange.getResponseSender().send("Error While parsinf Request");
        }));
    }
}
