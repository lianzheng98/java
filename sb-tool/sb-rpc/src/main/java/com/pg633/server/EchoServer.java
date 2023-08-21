/**
 * @(#)EchoServer.java, 8月 24, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.server;

/**
 * @author lianzhengbj
 */
public interface EchoServer {

    String SERVICE_NAME = "echo-service";

    /**
     * 启动 echo 服务
     *
     * @param port 服务监听的端口
     */
    void start(int port);

    /**
     * 关闭 echo 服务
     */
    void stop();
}