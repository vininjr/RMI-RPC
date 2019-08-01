package rmii;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class CalculadoraServidor {
	CalculadoraServidor() {
		try {
			//Criando o registro do servidor na porta 5000
			Registry r = java.rmi.registry.LocateRegistry.createRegistry(5001);
			r.rebind("Calculadora", new CalculadoraImple());
			System.setProperty("java.rmi.server.hostname", "127.0.1.1");
			//LocateRegistry.createRegistry(5000);
			Calculadora c = new CalculadoraImple();
			Naming.bind("CalculadoraService", (Remote) c);
			// exportObject(servidor, 5000);
		} catch (RemoteException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (AlreadyBoundException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new CalculadoraServidor();
	}
}
