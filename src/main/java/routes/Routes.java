package routes;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.BlockingHandler;


public class Routes {

  public HttpHandler getRouters() {
    return Handlers.routing().get("/user", new BlockingHandler(RoutesHandler::validateUserHandler))
        .post("/user", new BlockingHandler(RoutesHandler::createUserHandler))
        .post("/transfer/make", new BlockingHandler(RoutesHandler::makeTransaction))
        .post("/transfer/initiate", new BlockingHandler(RoutesHandler::initiateTransaction))
        .post("/wallet", new BlockingHandler(RoutesHandler::createWallet))
        .get("/balance", new BlockingHandler(RoutesHandler::checkUserBalance))
        .setFallbackHandler(RoutesHandler::setFailureResponse);
  }
}
