import static io.undertow.Handlers.path;


import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.util.Headers;
import routes.Routes;

public class MoneyTransferApplication {

  public static void main(String[] args) {
    Undertow.Builder builder = Undertow.builder();
    Routes routes = new Routes();
    builder.setIoThreads(10);
    builder.setWorkerThreads(10);
    builder.addHttpListener(3002, "localhost");
    PathHandler staticHandler = new PathHandler(routes.getRouters());
    builder.setHandler(path()
        .addPrefixPath("/", exchange -> {
          exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
          exchange.getResponseSender().send("{res:\"Hello World\"}");
        })
    );
    builder.setHandler(httpServerExchange -> {

    }).setHandler(staticHandler);
    Undertow server = builder.build();
    server.start();
  }
}
