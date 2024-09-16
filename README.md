Gerenciamento de uma biblioteca
desenvolva um sistema de gerenciamento de uma biblioteca. O sistema deve ser capaz de registrar livros, controlar os empréstimos e devoluções dos livros, e gerenciar a fila de espera dos leitores.

**classes implementadas**

**Classe Livro**
    * Atributos privados: titulo (String) e anoPublicacao (int).
    * Construtor que inicializa titulo e anoPublicacao.
    * Métodos públicos para obter (getTitulo, getAnoPublicacao) e definir (setTitulo, setAnoPublicacao) esses atributos.
        * Regra de validação:
            * O ano de publicação não deve ser menor que 1440 (ano em que a imprensa foi inventada) e não pode ser no futuro. Caso contrário, mostrar mensagem de erro e corrigir o ano para 1440 ou 2024.
    * Método público toString.
        * Formato: [Iracema, 1865]

**Classe Leitor**
    * Atributos privados: nome (String), livroEmprestado (Livro) e quantidadeEmprestimos (int).
    * Construtor que inicializa nome, define livroEmprestado como null e quantidadeEmprestimos como 0.
    * Métodos públicos para:
        * Verificar se já possui um livro emprestado (possuiLivroEmprestado)
        * Realizar o empréstimo de um livro (realizarEmprestimo)
            * A quantidade de empréstimos deve ser atualizada dentro do método realizarEmprestimo quando um livro é emprestado ao leitor.
            * Mostrar a quantidade atualizada junto com o nome do leitor que realizou o empréstimo.
        * Realizar a devolução do livro emprestado (realizarDevolucao)
            * Deve retornar o livroEmprestado e torná-lo null.
    * Método público toString.
        * Formato: [Jorge-, 2]
        * O nome de cada leitor deve ser acompanhado por + ou -, indicando se ele possui ou não um livro, respectivamente.

**Classe Biblioteca**
    * Atributos privados:
        * Lista de livros (livros) de tamanho fixo.
        * Fila de leitores (filaLeitores) de tamanho variável.
    * Construtor que inicializa a lista de livros com um tamanho fixo com todas as posições iguais a null e a fila de leitores como uma lista vazia.
        * O tamanho fixo da lista de livros deve ser um parâmetro de entrada do construtor.
    * Métodos públicos para:
        * Adicionar livro (adicionarLivro)
            * Um livro deve ser adicionado no primeiro espaço vazio encontrado na lista de livros.
            * Verificar se ainda cabe algum livro na lista e mostrar uma mensagem de erro caso não caiba.
        * Registrar leitor na fila (registrarLeitor)
            * Um leitor deve ser adicionado no final da fila de leitores.
        * Emprestar livro (emprestarLivro)
            * Ao emprestar um livro, deve-se atender sempre o primeiro leitor da fila.
            * Verificar se há algum leitor na fila e mostrar uma mensagem de erro caso não haja.
            * Verificar se o leitor já possui um livro emprestado e mostrar uma mensagem de erro caso possua.
            * Verificar se há algum livro disponível no índice indicado e mostrar uma mensagem de erro caso não haja.
                * O índice da lista de livros deve ser um parâmetro de entrada deste método.
            * Apenas os leitores atendidos devem ir para o final da fila.
        * Receber livro (receberLivro)
            * Ao receber um livro, deve-se atender sempre o primeiro leitor da fila.
            * Verificar se há algum leitor na fila e mostrar uma mensagem de erro caso não haja.
            * Verificar se o leitor está sem livro emprestado e mostrar uma mensagem de erro caso esteja.
            * Apenas os leitores atendidos devem ir para o final da fila.
    * Método público toString.
        * Formato: Lista de Livros: { [-----] [Iracema, 1865] [Sagarana, 1946] } Fila de Leitores: { [Alice+, 1] [Jorge-, 2] }



  Casos de teste

#__case iniciar
$init 2
$show
Livros: { [-----] [-----] }
Fila de Leitores: { }
$end


#__case livros
$init 4
$show
Livros: { [-----] [-----] [-----] [-----] }
Fila de Leitores: { }
$livro Iracema 1865
$livro Sagarana 1946
$livro Tropicália 2024
$show
Livros: { [Iracema, 1865] [Sagarana, 1946] [Tropicália, 2024] [-----] }
Fila de Leitores: { }

#__case leitores
$leitor Carla
$leitor Maria
$leitor Rubia
$show
Livros: { [Iracema, 1865] [Sagarana, 1946] [Tropicália, 2024] [-----] }
Fila de Leitores: { [Carla-, 0] [Maria-, 0] [Rubia-, 0] }

#__case emprestimos
$emprestar 1
Carla já pegou 1 livro(s) emprestado(s)
$show
Livros: { [Iracema, 1865] [-----] [Tropicália, 2024] [-----] }
Fila de Leitores: { [Maria-, 0] [Rubia-, 0] [Carla+, 1] }
$emprestar 0
Maria já pegou 1 livro(s) emprestado(s)
$show
Livros: { [-----] [-----] [Tropicália, 2024] [-----] }
Fila de Leitores: { [Rubia-, 0] [Carla+, 1] [Maria+, 1] }

#__case devolucoes
$emprestar 2
Rubia já pegou 1 livro(s) emprestado(s)
$show
Livros: { [-----] [-----] [-----] [-----] }
Fila de Leitores: { [Carla+, 1] [Maria+, 1] [Rubia+, 1] }
$receber
$show
Livros: { [Sagarana, 1946] [-----] [-----] [-----] }
Fila de Leitores: { [Maria+, 1] [Rubia+, 1] [Carla-, 1] }
$receber
$show
Livros: { [Sagarana, 1946] [Iracema, 1865] [-----] [-----] }
Fila de Leitores: { [Rubia+, 1] [Carla-, 1] [Maria-, 1] }
$end





#__case falha ano publicacao
$init 2
$show
Livros: { [-----] [-----] }
Fila de Leitores: { }
$livro Iracema 1865
$show
Livros: { [Iracema, 1865] [-----] }
Fila de Leitores: { }
$livro Tropicália 2026
Falha: ano de publicação inválido
$show
Livros: { [Iracema, 1865] [Tropicália, 2024] }
Fila de Leitores: { }

#__case falha biblioteca cheia
$livro Sagarana 1946
Falha: a biblioteca está cheia
$show
Livros: { [Iracema, 1865] [Tropicália, 2024] }
Fila de Leitores: { }

#__case falha fila de leitores vazia
$emprestar 1
Falha: fila de leitores vazia
$receber
Falha: fila de leitores vazia
$show
Livros: { [Iracema, 1865] [Tropicália, 2024] }
Fila de Leitores: { }

#__case falha emprestimo para leitor com livro
$leitor Roberto
$show
Livros: { [Iracema, 1865] [Tropicália, 2024] }
Fila de Leitores: { [Roberto-, 0] }
$emprestar 0
Roberto já pegou 1 livro(s) emprestado(s)
$show
Livros: { [-----] [Tropicália, 2024] }
Fila de Leitores: { [Roberto+, 1] }
$emprestar 1
Falha: leitor já possui um livro
$show
Livros: { [-----] [Tropicália, 2024] }
Fila de Leitores: { [Roberto+, 1] }

#__case falha emprestimo de livro indisponivel
$leitor Rebeca
$show
Livros: { [-----] [Tropicália, 2024] }
Fila de Leitores: { [Roberto+, 1] [Rebeca-, 0] }
$receber
$emprestar 0
Rebeca já pegou 1 livro(s) emprestado(s)
$show
Livros: { [-----] [Tropicália, 2024] }
Fila de Leitores: { [Roberto-, 1] [Rebeca+, 1] }
$emprestar 0
Falha: livro indisponível
$show
Livros: { [-----] [Tropicália, 2024] }
Fila de Leitores: { [Roberto-, 1] [Rebeca+, 1] }

#__case falha devolucao de leitor sem livro
$receber
Falha: leitor não possui um livro para devolver.
$show
Livros: { [-----] [Tropicália, 2024] }
Fila de Leitores: { [Roberto-, 1] [Rebeca+, 1] }
$emprestar 1
Roberto já pegou 2 livro(s) emprestado(s)
$show
Livros: { [-----] [-----] }
Fila de Leitores: { [Rebeca+, 1] [Roberto+, 2] }
$receber
$receber
$show
Livros: { [Iracema, 1865] [Tropicália, 2024] }
Fila de Leitores: { [Rebeca-, 1] [Roberto-, 2] }
$end

