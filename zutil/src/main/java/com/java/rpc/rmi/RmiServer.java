package com.java.rpc.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    private Registry registry;

    public void start() throws RemoteException, MalformedURLException, AlreadyBoundException {
        ServerInterface<String, String> sin = s -> {
            System.out.println(s);
            return s;
        };
        Naming.bind("rmi://hhh", sin);
    }

    /**
     * 注册服务
     *
     * @param obj
     * @param url
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    public void register(Remote obj, String url) throws RemoteException, AlreadyBoundException {
        if (registry == null) {
            registry = LocateRegistry.createRegistry(10081);
        }
        registry.bind(url, obj);
    }
}
