<p align="center">
  <h3 align="center">HealTracking</h3>
  <p align="center">
    Sistema Inteligente de Gerenciamento de Resíduos de Serviços de Saúde (RSS)
  </p>


<p align="center">
  <img src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge" alt="Status">
</p>

---

##Sobre o Projeto

O **HealTracking** é uma solução digital desenvolvida para otimizar o controle, rastreabilidade e gerenciamento de **Resíduos de Serviços de Saúde (RSS)** em clínicas, laboratórios e hospitais. 

O sistema visa garantir a conformidade com as normas ambientais e sanitárias vigentes (como RDC ANVISA e CONAMA), auxiliando na gestão desde a geração do resíduo, armazenamento temporário, até a destinação final e o descarte adequado.

---

##Funcionalidades Principais

* **Rastreabilidade de Resíduos:** Acompanhamento do ciclo de vida dos resíduos por grupos (Grupo A, B, C, D e E).
* **Gestão de Inventário e Pesagem:** Registro de peso, volume e data de geração por setor do estabelecimento de saúde.
* **Controle de Coleta e Transporte:** Agendamento e monitoramento de coletas por empresas terceirizadas especializadas.
* **Relatórios e Conformidade:** Geração automática de relatórios gerenciais e MTR (Manifesto de Transporte de Resíduos) para auditorias.
* **Controle de Acesso por Perfis:** Níveis de permissão distintos para administradores, equipe de limpeza, gerentes de sustentabilidade e órgãos fiscalizadores.

---
##  Como Executar o Projeto

Siga os passos abaixo para clonar e executar o projeto em sua máquina local:

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente:
* [Git](https://git-scm.com/)
* [Node.js](https://nodejs.org/) (ou ambiente correspondente ao seu stack)
* Gerenciador de pacotes (`npm`, `yarn` ou `pnpm`)

### Passo a Passo

1. **Clone o repositório:**
   '''bash
   git clone [https://github.com/anamancilha/HealTracking.git](https://github.com/anamancilha/HealTracking.git)


2. **Acesse a pasta do projeto:**

'''Bash
cd HealTracking

---

3. **Instale as dependências:**

'''Bash
npm install

---

4. **Configure as variáveis de ambiente:**

Duplique o arquivo .env.example e renomeie-o para .env.

Preencha as credenciais do banco de dados e chaves de API necessárias.

---
5. **Inicie o servidor de desenvolvimento:**

'''Bash
npm run dev
