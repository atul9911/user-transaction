package routes;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.BlockingHandler;


public class Routes {
    public HttpHandler getRouters() {
        return Handlers.routing().get("/user", new BlockingHandler(RoutesHandler::validateUserHandler))
                .post("/user", new BlockingHandler(RoutesHandler::createUserHandler))
                .post("/transfer", new BlockingHandler(RoutesHandler::makeTransaction))
                .get("/wallet", new BlockingHandler(RoutesHandler::getWallet))
                .get("/statement", new BlockingHandler(RoutesHandler::checkUserStatement));
    }
}
