/**
 * @(#)SimpleEchoServer.java, 8月 24, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.server;

import com.pg633.rpc.RpcHandler;
import com.pg633.thrift.EchoServiceThrift;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import java.util.Objects;

/**
 * @author lianzhengbj
 */
@Slf4j
public class SimpleEchoServer implements EchoServer {

    private TServer server;

    @Override
    public void start(int port) {
        try {
            EchoServiceThrift.Processor<RpcHandler> processor = new EchoServiceThrift.Processor<>(new RpcHandler());
            TServerSocket socket = new TServerSocket(port);
            TSimpleServer.Args args = new TSimpleServer.Args(socket);
            args.processor(processor);
            args.protocolFactory(new TBinaryProtocol.Factory());
            server = new TSimpleServer(args);
            server.serve();
        } catch (Exception e) {
            log.error("网络错误, message={}", e.getMessage());
        }
    }

    @Override
    public void stop() {
        if (Objects.nonNull(server)) {
            server.stop();
        }
    }
}