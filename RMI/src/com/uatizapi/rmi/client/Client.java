package com.uatizapi.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {

	void showMessage(String str) throws RemoteException;

	int getInt() throws RemoteException;

	void setInt(int id) throws RemoteException;
}
