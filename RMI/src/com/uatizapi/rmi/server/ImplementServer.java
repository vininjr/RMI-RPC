package com.uatizapi.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.uatizapi.rmi.client.Client;

public class ImplementServer extends UnicastRemoteObject implements Server {

	private static final long serialVersionUID = 1L;
	private ArrayList<Client> clients;
	private String lastMessage = "";
	private static int id = 0;

	protected ImplementServer() throws RemoteException {
		this.clients = new ArrayList<Client>();
	}

	@Override
	public synchronized void registerClient(Client c) throws RemoteException {
		System.out.println("Um novo usuário se conectou");
		c.setInt(id++);
		this.clients.add(c);
	}

	@Override
	public synchronized void broadcastMessage(String str) throws RemoteException {
		System.out.println("msg = " + str);
		System.out.println("size = " + this.clients.size());

		this.lastMessage = str;
	}

	@Override
	public String getLastMensage() {
		return lastMessage;
	}

}
