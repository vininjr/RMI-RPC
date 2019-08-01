#include "chat.h"
#include <string.h>
#include <unistd.h> 
#include <semaphore.h>

int count_clients = 0, count_mensagens = 0;
int controller_message = 0;

pthread_mutex_t memory;
typedef struct buffer{
	char mensagem[256];
	int id_recept;
	int id_mensage;
	int vet[10];
}Buffer;

Buffer *BD = NULL;


void innit_BD(){
     BD = (Buffer*) malloc(sizeof(Buffer));
 } 

void *send_mensagem_4_svc(mensagem *argp, struct svc_req *rqstp){
	char l[256];
	strcpy(l, argp->m);
	if(controller_message != argp->id_mensagem){
			strcpy(BD->mensagem, argp->m);
			BD->id_mensage = ++count_mensagens;
			BD->id_recept = argp->id_cliente;
			controller_message = argp->id_mensagem;
			printf("mensagem >>%s\n", BD->mensagem);
			sleep(0.8);
			int i;
			for(i = 1; i <= count_clients; i++){
				if(BD->vet[i] == 0)
					printf("cliente %d desconectou\n", i);
				BD->vet[i] = 0;

			}
	
		}
}

mensagem *findbymensagem_4_svc(int *argp, struct svc_req *rqstp){
    
    
	static mensagem  result;
	if(BD->vet[*argp] == 0){
	   BD->vet[*argp] = 1;
	   printf("cliente %d online\n",*argp);
    }
    BD->vet[*argp] = 1;
	if(count_mensagens > 0){
	  if(*argp != BD->id_recept){
			strcpy(result.m, BD->mensagem);
			result.id_cliente = BD->id_recept;
			result.id_mensagem = count_mensagens;
			return &result;
		}
	}

		return NULL;	
}



int *cad_cliente_4_svc(int *argp, struct svc_req *rqstp){
	static int  result;
	if(count_clients == 0)
		innit_BD();

	count_clients++;
	BD->vet[count_clients] = 1;
	result = count_clients;
	printf("cliente %d entrou.\n",count_clients );
	return &result;
}
