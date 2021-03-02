package com.java.rpc.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RimClient {
    /**
     * rmi://192.168.58.164:12312/Hello
     *
     * @param url rmi://192.168.58.164:12312/Hello
     */
    public void look(String url) throws RemoteException, NotBoundException, MalformedURLException {
        Remote lookup = Naming.lookup(url);
        
    }
}
