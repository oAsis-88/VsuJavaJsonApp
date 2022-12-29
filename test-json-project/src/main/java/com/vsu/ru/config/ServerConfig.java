package com.vsu.ru.config;

import com.vsu.ru.model.Currency;
import com.vsu.ru.model.Item;
import com.vsu.ru.model.Player;
import com.vsu.ru.model.Progress;
import com.vsu.ru.servlet.*;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerConfig {
    private static final ModelServletAbs<Long, Currency> currencyServlet = new CurrencyServlet();
    private static final ModelServletAbs<Long, Item> itemModelServletAbs = new ItemServlet();
    private static final ModelServletAbs<Long, Player> playerModelServletAbs = new PlayerServlet();
    private static final ModelServletAbs<Long, Progress> progressModelServletAbs = new ProgressServlet();

    private static Server build(){
        final Server server = new Server();
        final int port = 8080;
        final HttpConfiguration httpConfig = new HttpConfiguration();
        final HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfig);
        final ServerConnector serverConnector = new ServerConnector(server, httpConnectionFactory);
        serverConnector.setHost("localhost");
        serverConnector.setPort(port);
        server.setConnectors(new Connector[]{serverConnector});
        return server;
    }

    public static void initServer() throws Exception {
        final Server server = build();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder("players", playerModelServletAbs), "/player/*");
        context.addServlet(new ServletHolder("items", itemModelServletAbs), "/item/*");
        context.addServlet(new ServletHolder("currencies", currencyServlet), "/currency/*");
        context.addServlet(new ServletHolder("progresses", progressModelServletAbs), "/progress/*");
        server.setHandler(context);
        server.start();
    }
}
