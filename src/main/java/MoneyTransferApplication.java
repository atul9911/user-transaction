import io.undertow.Undertow;
import io.undertow.util.Headers;

import static io.undertow.Handlers.path;

public class MoneyTransferApplication {

    public static void main(String[] args) {
        Undertow.Builder builder = Undertow.builder();
        builder.setIoThreads(10);
        builder.setWorkerThreads(10);
        builder.addHttpListener(3002, "localhost");
        builder.setHandler(path()
                .addPrefixPath("/", exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,"application/json");
                    exchange.getResponseSender().send("{res:\"Hello World\"}");
                })
        );
        Undertow server = builder.build();
        server.start();
    }
}
