# CI/CD Multicloud – Stage & Produção com Kubernetes + Monitoramento

Aqui ficará armazenada uma documentação com os seguintes itens:
 - Fluxo de branches
 - Estrutura do pipeline
 - Instruções para rodar

 👻 Stack Utilizada:

- **Backend:** Java + SpringBoot
- **Banco de Dados:** PostgreSQL
- **Infraestrutura:** Kubernetes (GKE)
- **CI/CD:** GitHub Actions
- **Docker Registry:** Docker Hub
- **Monitoramento:** Grafana + Prometheus
- **Provisionamento:** Terraform

:suspect: Estrutura das Branches:

- **Main:** Versão estável em produção
- **Developer:** Ambiente de desenvolvimento e testes
- **Release:** Criada a partir de dev, usada para empacotar nova versão completa 

📡 Rodando a Pipeline:

1. Provisionamento da infraestrutura automatizado utilizando o Terraform
2. Configuração do cluster Kubernetes (GKE)
3. Coleta e visualização de monitoramento (Prometheus + Grafana)
4. Build da imagem Docker da aplicação (stage e/ou production)
5. Push da imagem para o Artifact Registry
6. Deploy automático no cluster através de `kubectl apply`
7. Validação com `kubectl rollout status`
