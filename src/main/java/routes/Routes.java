package routes;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;


public class Routes {
    public HttpHandler getRouters() {
        return Handlers.routing().get("/user", RoutesHandlerRegistry::validateUserHandler)
                .post("/user", RoutesHandlerRegistry::createUserHandler)
                .post("/transfer", RoutesHandlerRegistry::makeTransaction)
                .get("/wallet", RoutesHandlerRegistry::getWallet)
                .get("/statement", RoutesHandlerRegistry::checkUserStatement);
    }
}
