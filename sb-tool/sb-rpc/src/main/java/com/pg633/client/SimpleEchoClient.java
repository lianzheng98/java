/**
 * @(#)SimpleEchoClient.java, Jul 12, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.client;

import com.pg633.registry.Registry;
import com.pg633.server.EchoServer;
import com.pg633.thrift.EchoServiceThrift;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Slf4j
public class SimpleEchoClient implements EchoClient {

    private Registry registry;

    private Random random;

    private static final Integer TIMEOUT = 30000;

    public SimpleEchoClient(Registry registry) {
        this.registry = registry;
        random = new Random();
    }

    @Override
    public String echo(String message) {
        List<Registry.Info> serviceInfos = registry.getServiceInfos(EchoServer.SERVICE_NAME);
        if (CollectionUtils.isEmpty(serviceInfos)) {
            throw new RuntimeException("service not found");
        }
        Registry.Info info = serviceInfos.get(random.nextInt(serviceInfos.size()));
        try {
            TSocket tSocket = new TSocket(info.getHost(), info.getPort(), TIMEOUT);
            tSocket.open();
            return new EchoServiceThrift.Client(new TBinaryProtocol(tSocket)).echo(message);
        } catch (TException e) {
            log.error("网络错误, message={}", e.getMessage());
        }
        return null;
    }
}