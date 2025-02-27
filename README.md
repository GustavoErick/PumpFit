# 💪 PumpFit

PumpFit é um aplicativo móvel desenvolvido com Jetpack Compose para ajudar os usuários na organização e execução de treinos de musculação. O aplicativo foi projetado para ser simples, intuitivo e altamente funcional, oferecendo recursos como lista de exercícios, personalização de favoritos, reprodução de vídeos e muito mais.

---

## 📋 Funcionalidades

- **Autenticação e Credenciamento:**
  - Tela de Login, Cadastro e Recuperação de Senha.
  - Login via e-mail e autenticação com conta Google.

- **Tela Inicial – Lista de Grupos Musculares:**
  - Exibição de grupos musculares disponíveis.
  - Pesquisa por grupos específicos.
  - Acesso às telas de exercícios por meio de um menu de opções (três pontinhos).

- **Lista de Exercícios:**
  - Exibe exercícios organizados por grupos musculares.
  - Permite marcar/desmarcar exercícios como favoritos.
  - Navegação para detalhes do exercício.

- **Detalhes do Exercício:**
  - Informações detalhadas (grupo muscular, carga, séries, intervalo, metodologia e máquinas utilizadas).
  - Reprodução de vídeos demonstrativos.
  - Temporizador com notificação para controle do tempo de intervalo.

- **Menu e Navegação:**
  - Acesso rápido às telas: Tela Inicial, Favoritos, Configurações, Perfil e Ajuda.
  - Opção de logout.

- **Favoritos:**
  - Listagem de exercícios marcados como favoritos.
  - Remoção de exercícios favoritos diretamente na tela.

- **Configurações:**
  - Limpeza de todos os exercícios favoritos.
  - Alternância entre temas Claro e Escuro (incluindo uso do tema do sistema).
  - Ativação/desativação de notificações e animações.

- **Perfil e Suporte:**
  - Exibição das informações do usuário (nome, foto, peso, altura e objetivo).
  - Seção de FAQ para suporte e dúvidas frequentes.

---

## 🛠️ Estrutura do Projeto

O projeto está organizado de maneira modular para facilitar a manutenção e futuras expansões:

- **components:**  
  Componentes reutilizáveis como o menu de três pontinhos.

- **data:**  
  - **model:** Classes de modelo (ex.: `User`, `Exercise`, `MuscleGroup`) e repositórios/gerenciadores de dados.  
  - **mock:** Dados estáticos para testes e prototipagem (ex.: `MockExercises`, `MockUsers`).

- **viewmodels:**  
  Classes responsáveis pelo gerenciamento do estado e lógica de negócios da interface.

- **navigation:**  
  Gerenciamento das rotas e fluxo de navegação entre as telas (definido em `MainScreen.kt`).

- **network:**  
  Configuração de chamadas de rede e serviços (ex.: `RetrofitInstance` e `MuscleApiService`).

- **services:**  
  Serviços como o `TimerReceiver` para notificações e alarmes.

- **ui:**  
  - **screen:** Telas do aplicativo (ex.: `HomeScreen`, `ExerciseListScreen`, `DetailsScreen`, etc.).  
  - **theme:** Gerenciamento de temas, cores e estilos (ex.: `Color.kt`, `Theme.kt`).

- **util:**  
  Classes utilitárias, como `NotificationUtils` para o gerenciamento de notificações.

- **MainActivity.kt:**  
  Ponto de entrada da aplicação.

---

## 🧬 Evolução do Projeto

O desenvolvimento do PumpFit foi realizado em etapas, conforme as entregas dos trabalhos práticos e do trabalho final:

- **Versão 1.0 (08/01/2025):**  
  Funcionalidades F1 a F7 – Desenvolvimento inicial e criação das telas básicas.

- **Versão 2.0 (26/01/2025):**  
  Funcionalidades F9 a F12 – Incremento de novas funcionalidades e melhorias na interface.

- **Versão 3.0 (26/02/2025):**  
  Funcionalidades F14 a F17 – Versão final com integração de serviços, autenticação, persistência de dados e ajustes de usabilidade.

---

## 🧩 Tecnologias Utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Navigation Compose**
- **Material3**
- **VideoView** (para reprodução de vídeos)
- **Firebase Authentication** (para autenticação de usuários)
- **Retrofit** (para integração com API REST)

---

## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/GustavoErick/PumpFit.git
2. Abra o projeto no Android Studio.
3. Sincronize as dependências.
4. Execute o aplicativo em um emulador ou dispositivo físico.

---

## 👥 Colaboradores
**Gustavo Erick**: [LinkedIn](https://www.linkedin.com/in/gustavo-erick-806778313/) | [GitHub](https://github.com/GustavoErick)  
**Venicius Feitosa**: [GitHub](https://github.com/FeitosaVeni)

---

## ⚖️ Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

PumpFit © 2025 - Universidade Federal do Ceará, Campus Quixadá
