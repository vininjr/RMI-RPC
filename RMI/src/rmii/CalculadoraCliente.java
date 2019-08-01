package rmii;

import java.rmi.Naming;

public class CalculadoraCliente {
	public static void main(String[] args) {
		try {
			Calculadora c = (Calculadora) Naming.lookup("//localhost/CalculadoraService");
			System.out.println("Adição: " + c.add(10, 15));
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
