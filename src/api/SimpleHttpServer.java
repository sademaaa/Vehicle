package api;

import com.sun.net.httpserver.HttpServer;
import domain.Vehicle;
import service.RentingService;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleHttpServer {
    public static void startServer(int port, RentingService service) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);


        server.createContext("/", exchange -> {
            byte[] response = Files.readAllBytes(Paths.get("index.html"));
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(response); }
        });


        server.createContext("/style.css", exchange -> {
            byte[] response = Files.readAllBytes(Paths.get("style.css"));
            exchange.getResponseHeaders().set("Content-Type", "text/css");
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(response); }
        });


        server.createContext("/vehicles", exchange -> {
            List<Vehicle> list = service.getAllSorted();
            String json = "[" + list.stream()
                    .map(v -> String.format("{\"brand\":\"%s\",\"price\":%d}", v.getBrand(), v.getPrice()))
                    .collect(Collectors.joining(",")) + "]";

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, json.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(json.getBytes()); }
        });

        server.start();
        System.out.println("Server ready! Open: http://localhost:" + port);
    }
}