### Arquitetura do projeto
Esse projeto é um **CHAT** distribuido em java, usando sockets e threads, realizando a comunicação entre clientes via um **SERVIDOR CENTRAL**, cada um rodando uma thread paralela.

Funcionamento:

Cliente:
- se conecta ao servidor via TCP(socket)
- envia e recebe mensagens 
- roda em uma thread separada, para permitir multitarefas 

Servidor:
- aceita conexões(ServerSocket)
- cria uma thread para cada cliente conectado
- envia as mensagens recebidas para todos via broadcast

Nesse projeto, é mostrado na prática os principais conceitos de redes e sistemas distribuidos, programação concorrente e comunicação via sockets TCP em java.



