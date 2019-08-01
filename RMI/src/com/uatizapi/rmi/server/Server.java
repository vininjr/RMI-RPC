package com.uatizapi.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.uatizapi.rmi.client.Client;

public interface Server extends Remote {

	void registerClient(Client c) throws RemoteException;

	void broadcastMessage(String str) throws RemoteException;

	String getLastMensage() throws RemoteException;

}
