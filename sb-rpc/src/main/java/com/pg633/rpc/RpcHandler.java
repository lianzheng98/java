/**
 * @(#)RpcHandler.java, 8月 24, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.rpc;

import com.pg633.thrift.EchoServiceThrift;
import org.apache.thrift.TException;

/**
 * @author lianzhengbj
 */
public class RpcHandler implements EchoServiceThrift.Iface {

    @Override
    public String echo(String message) throws TException {
        return message;
    }
}