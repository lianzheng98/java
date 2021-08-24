package com.pg633.client;

import com.pg633.registry.FileRegistryImpl;
import com.pg633.registry.Registry;
import com.pg633.server.EchoServer;
import com.pg633.server.SimpleEchoServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SimpleEchoClientTest {

    private static final String REGISTRY_FILE_NAME =
            "/Users/lianzheng/work/pg633/java/sb-rpc/src/main/resources/registry_test.json";

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 9002;

    private static final Registry registry = new FileRegistryImpl(REGISTRY_FILE_NAME);

    private static final EchoServer echoServer = new SimpleEchoServer();

    @BeforeAll
    public static void init() {
        new Thread(() -> {
            echoServer.start(PORT);
        }).start();
        registry.register(EchoServer.SERVICE_NAME, new Registry.Info(HOST, PORT));
    }

    @AfterAll
    public static void destroy() {
        registry.deregister(EchoServer.SERVICE_NAME, new Registry.Info(HOST, PORT));
        echoServer.stop();
    }

    @Test
    public void testEcho() {
        EchoClient client = new SimpleEchoClient(registry);
        String testMessage = "42";
        String echoMessage = client.echo(testMessage);
        System.out.println(echoMessage);
    }
}