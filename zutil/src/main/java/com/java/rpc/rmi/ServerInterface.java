package com.java.rpc.rmi;

import java.rmi.Remote;

/**
 * 远程接口帮助类
 *
 * @param <P>
 * @param <R>
 */
@FunctionalInterface
public interface ServerInterface<P, R> extends Remote {
    R execute(P p);
}
