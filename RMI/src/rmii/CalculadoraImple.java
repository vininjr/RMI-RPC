package rmii;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraImple extends UnicastRemoteObject implements Calculadora {

	public CalculadoraImple() throws RemoteException {
		super();
		int a, b;
	}

	@Override
	public int add(int a, int b) throws RemoteException {
		return a + b;
	}

	@Override
	public int sub(int a, int b) throws RemoteException {
		return a - b;
	}

	@Override
	public int mult(int a, int b) throws RemoteException {
		return a * b;
	}

	@Override
	public int div(int a, int b) throws RemoteException {
		if (b != 0)
			return a / b;
		else
			return -1;
	}

}
