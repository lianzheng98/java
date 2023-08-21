/**
 * @(#)RegistryManager.java, Jul 12, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.registry;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

public interface Registry {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    class Info {

        private String host;

        private int port;
    }

    void clear(String service);

    void register(String server, Info info);

    void deregister(String service, Info info);

    List<Info> getServiceInfos(String service);
}