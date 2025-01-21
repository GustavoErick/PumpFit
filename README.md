# PumpFit

PumpFit é um aplicativo móvel desenvolvido com Jetpack Compose para ajudar os usuários na organização e execução de treinos de musculação. O aplicativo foi projetado para ser simples, intuitivo e altamente funcional, oferecendo recursos como lista de exercícios, personalização de favoritos, reprodução de vídeos e muito mais.

---

## 📋 Funcionalidades

- **Tela Inicial:**
  - Exibe os grupos musculares disponíveis.
  - Permite a pesquisa por grupos musculares específicos.
  - Acessa diferentes telas por meio de um menu de opções (três pontinhos).
  
- **Lista de Exercícios:**
  - Mostra exercícios organizados por grupos musculares.
  - Permite marcar/desmarcar exercícios como favoritos.
  - Navega para a tela de detalhes do exercício.

- **Detalhes do Exercício:**
  - Exibe informações completas sobre o exercício, como carga, séries, intervalo e metodologia.
  - Mostra as máquinas associadas ao exercício.
  - Permite assistir a vídeos demonstrativos.

- **Favoritos:**
  - Lista os exercícios marcados como favoritos.
  - Opção para remover favoritos diretamente.

- **Perfil:**
  - Exibe as informações do usuário (nome, peso, altura e objetivo).
  - Interface minimalista e organizada.

- **Configurações:**
  - Botão para limpar todos os exercícios favoritos.
  - Alternância entre temas Claro e Escuro.

- **Ajuda:**
  - FAQ com perguntas frequentes sobre o uso do aplicativo.

---

## 🛠️ Estrutura do Projeto

O projeto é organizado de forma modular para facilitar a manutenção e futuras expansões.

### **Principais Diretórios**
- **`model`**: Modelos de dados (`Exercise`, `User`, `MuscleGroup`) e mocks (`MockExercises`, `MockMuscleGroups`, `MockUsers`) usados para simulações.
- **`ui`**: Contém as telas (`HomeScreen`, `ExerciseListScreen`, etc.) e componentes reutilizáveis (`Menu`).
- **`navigation`**: Gerencia o fluxo de navegação entre as telas.
- **`components`**: Contém elementos reutilizáveis como a barra de navegação.

---

## 🧩 Tecnologias Utilizadas

- **Kotlin**: Linguagem principal do projeto.
- **Jetpack Compose**: Framework para construção da interface do usuário.
- **Navigation Compose**: Gerenciamento de navegação.
- **Material3**: Design system baseado no Material Design.
- **VideoView**: Reproduz vídeos na tela de detalhes.

---

## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/GustavoErick/PumpFit.git
2. Abra o projeto no Android Studio.
3. Sincronize as dependências.
4. Execute o aplicativo em um emulador ou dispositivo físico.

---

## 📂 Estrutura de Navegação
O fluxo do aplicativo segue um esquema claro e organizado:

- **HomeScreen**: Tela inicial -> Navega para a lista de exercícios.
- **ExerciseListScreen**: Lista de exercícios -> Navega para os detalhes do exercício.
- **ExerciseDetailsScreen**: Detalhes do exercício.
- **Favoritos, Configurações, Perfil e Ajuda**: Acessados via menu de opções ou barra de navegação.

---

## 👥 Colaboradores
**Gustavo Erick**: [LinkedIn](https://www.linkedin.com/in/gustavo-erick-806778313/) | [GitHub](https://github.com/GustavoErick)  
**Venicius Feitosa**: [GitHub](https://github.com/FeitosaVeni)

---

## 📄 Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para obter mais informações.


