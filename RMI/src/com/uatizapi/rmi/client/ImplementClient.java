package com.uatizapi.rmi.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.uatizapi.rmi.server.Server;

public class ImplementClient extends UnicastRemoteObject implements Client, Runnable {

	private static final long serialVersionUID = 1L;
	private Server server;
	private String nome = null;
	private String ip = null;
	private int id = 0;

	protected ImplementClient(String nome, Server server) throws RemoteException {
		try {
			this.nome = nome;
			this.server = server;
			this.ip = InetAddress.getLocalHost().getHostAddress();
			new Thread(new ImplementClientAsk(this.server)).start();
		} catch (UnknownHostException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		this.server.registerClient(this);
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public void showMessage(String str) throws RemoteException {
		System.out.println(str);
	}

	// PEGAR O IP DA MÁQUINA -> InetAddress.getLocalHost().getHostAddress()

	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		String mensagem;
		while (true) {
			System.out.println("Digite sua mensagem");
			mensagem = scan.nextLine();
			try {
				server.broadcastMessage(nome + ": " + mensagem);
			} catch (RemoteException e) {
				System.out.println("Não foi possível enviar a mensagem");
			}
		}

	}

	@Override
	public int getInt() throws RemoteException {
		return this.id;
	}

	@Override
	public void setInt(int id) throws RemoteException {
		this.id = id;
	}

	public class ImplementClientAsk extends UnicastRemoteObject implements Runnable {

		private static final long serialVersionUID = 1L;
		private Server server;
		private String lastMessage = "";

		protected ImplementClientAsk(Server server) throws RemoteException {
			this.server = server;
		}

		@Override
		public void run() {
			while (true) {
				String messageReturn;
				try {
					messageReturn = server.getLastMensage();

					if (!messageReturn.equals(this.lastMessage)) {
						System.out.println(messageReturn);
						this.lastMessage = messageReturn;
					}
				} catch (RemoteException e1) {
					System.out.println("deu ruim");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					continue;
				}
			}
		}
	}
}
