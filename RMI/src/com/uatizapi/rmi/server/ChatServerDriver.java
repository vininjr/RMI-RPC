package com.uatizapi.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ChatServerDriver {
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		System.setProperty("java.rmi.server.hostname", "192.168.1.11");
		Naming.rebind("RMIServer", new ImplementServer());
	}
}
