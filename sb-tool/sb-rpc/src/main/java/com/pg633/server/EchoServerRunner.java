/**
 * @(#)EchoServerRunner.java, 8月 24, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.server;

import com.pg633.registry.FileRegistryImpl;
import com.pg633.registry.Registry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author lianzhengbj
 */
@Component
@Slf4j
public class EchoServerRunner implements ApplicationRunner {

    private EchoServer server;

    private Registry registry;

    private static final int PORT = 333;

    private static final String HOST = "127.0.0.1";

    private static final String REGISTRY_FILE_NAME = "registry.json";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        registry = new FileRegistryImpl(REGISTRY_FILE_NAME);
        server = new SimpleEchoServer();

        registry.register(EchoServer.SERVICE_NAME, new Registry.Info(HOST, PORT));
        log.info("# register server={} ,info={}", EchoServer.SERVICE_NAME, new Registry.Info(HOST, PORT));
        server.start(PORT);
    }
}