package com.uatizapi.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import com.uatizapi.rmi.server.Server;

@SuppressWarnings("resource")
public class ChatClientDriver {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String serverUrl = "rmi://192.168.1.11/RMIServer", name;
		Server server = (Server) Naming.lookup(serverUrl);
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite seu nick:");
		name = scan.nextLine();
		new Thread(new ImplementClient(name, server)).start();
	}
}
