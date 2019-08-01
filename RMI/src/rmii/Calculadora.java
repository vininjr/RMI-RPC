package rmii;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {
	/*
	 * Interface is remote interface so we extend remote class by extending the
	 * interface identifies itself as an interface whose methods can be invoked by
	 * other jvm
	 */

	public int add(int a, int b) throws RemoteException;

	public int sub(int a, int b) throws RemoteException;

	public int mult(int a, int b) throws RemoteException;

	public int div(int a, int b) throws RemoteException;
}
