struct mensagem{
	char m[255];
	int id_mensagem;
	int id_cliente;
};

program bate_papo{
	version bate_papo{
		 void send_mensagem(mensagem)=1;
		 mensagem findByMensagem(int id_cliente)= 2;
		 int cad_cliente(int id_cliente)=3;

	}=4;


} = 5;
