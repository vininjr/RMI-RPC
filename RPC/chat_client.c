#include "chat.h"
#include <pthread.h>
#include <unistd.h> 


int controle = 1; 
CLIENT *clnt;
mensagem  *result_1;
mensagem  *result_2;
int id_cliente = 0, count_mensagens = 0, 
messages_sent = 0;

void bate_papo_4(char *host){
#ifndef	DEBUG

    clnt = clnt_create (host, bate_papo, bate_papo, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}

void *ask(){
	while(1){
		controle = 1;
		sleep(0.8);
		mensagem  send_mensagem_4_arg;
		scanf("%[^\n]s", send_mensagem_4_arg.m);
		setbuf(stdin, NULL);
		controle = 0;
		send_mensagem_4_arg.id_cliente = id_cliente;
		messages_sent*=-1;
		send_mensagem_4_arg.id_mensagem = messages_sent;
		send_mensagem_4(&send_mensagem_4_arg, clnt);

	}		
}

void *answer(void *vargp){

    int findbymensagem_4_arg;
		while(1){
			sleep(0.8);
			if(controle == 1){
			findbymensagem_4_arg = id_cliente;
			result_2 = findbymensagem_4(&findbymensagem_4_arg, clnt);
			if(result_2 != NULL){
				if(result_2->id_mensagem > count_mensagens){
					count_mensagens = result_2->id_mensagem;
					printf("\nmensagem do cliente %d >> %s\n",result_2->id_cliente, result_2->m);
										
				}
			}
		}
    }
}	

void create_thread(){
	pthread_t controlerImput;
	pthread_t controlerOutput;
	int id_threads = id_cliente;
	pthread_create(&(controlerImput), NULL, ask, (void *)&(id_threads));
	pthread_create(&(controlerOutput), NULL, answer, (void *)&(id_threads));

	pthread_join(controlerImput, NULL);
	pthread_join(controlerOutput, NULL);

}	

#endif	
//----------------------------------
		if(id_cliente == 0){
			id_cliente =  *(cad_cliente_4(&id_cliente, clnt));
			messages_sent = id_cliente;
			printf("voce e cliente %d\n", id_cliente);
			printf("Digite suas mensagens no terminal:\n");
			create_thread();
		}

//----------------------------------
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif
}


int main (int argc, char *argv[]){
	char *host;

	if (argc < 2) {
		printf ("usage: %s server_host\n", argv[0]);
		exit (1);
	}
	host = argv[1];
	bate_papo_4 (host);
exit (0);
}


/*
	result_1 = send_mensagem_4(&send_mensagem_4_arg, clnt);
	if (result_1 == (mensagem *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_2 = findbymensagem_4(&findbymensagem_4_arg, clnt);
	if (result_2 == (mensagem *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	result_3 = cad_cliente_4(&cad_cliente_4_arg, clnt);
	if (result_3 == (int *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	*/