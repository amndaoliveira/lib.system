import java.util.LinkedList;
import java.util.Scanner;

class Biblioteca {
    private LinkedList<Livro>livros;
    private LinkedList<Leitor>leitoresFila;
    private int capacidade;
    
    public Biblioteca(int capacidade) {
        this.livros = new LinkedList<>();
        this.leitoresFila = new LinkedList<>();
        this.capacidade = capacidade;
    }

    //Adicionar livros na biblioteca (sempre no primeiro espaço vazio da lista de livros).
    public boolean adicionarLivro(Livro livroAdd) {
        
        /**
         * Step 1 - se tem capacidade
                    1.1 - realizar validacao do livro antes de adicionar na lista
         */
         if (livros.size() < capacidade){
            livros.addLast(livroAdd);
            return true;
         }
         
        System.out.println("Falha: biblioteca cheia");
        return false;
    }

    // Registrar um leitor na fila de espera (sempre no final da fila de leitores)
    public void registrarLeitor(Leitor addLeitoresFimDaFila) {
        leitoresFila.addLast(addLeitoresFimDaFila); // se usar so o add(), mudaria algo?
    }
    // Emprestar um livro para o próximo (primeiro) leitor da fila
    public boolean emprestarLivro(int numeroDoLivroEmprestado) {

        if (leitoresFila.isEmpty()) { // Verificar se a fila de leitores está vazia.
            System.out.println("Falha: fila de leitores vazia"); // nao pode emprestar
            return false;
        } 

        if (livros.isEmpty()) {
            System.out.println("falha: biblioteca vazia");
            return false;
        }
         /**
         * Verificar se há algum livro disponível no índice indicado
         * - mostrar uma mensagem de erro caso não haja.
         */
        if (numeroDoLivroEmprestado < 0 || numeroDoLivroEmprestado >= livros.size()){
            System.out.println("Falha: indice do livro inválido");
            return false;
        }
        // Leitor leitor = leitoresFila.getFirst(); (test)
        Leitor leitor = leitoresFila.removeFirst();
        Livro livro = livros.get(numeroDoLivroEmprestado);
         
        if (livro.isEmprestado()) {
            System.out.println("Falha: livro já emprestado");
            leitoresFila.addLast(leitor);
            return false;
        }

        /**
         * Verificar se o leitor já possui um livro emprestado
         * - mostrar uma mensagem de erro caso possua.
         * acho que está redundante, tem a mesmas infos em leitor
         */ 
        if (leitor.possuiLivroEmprestado()) {
            System.out.println("Falha: o leitor já possui livro emprestado");
            //falha: livro indisponível
            leitoresFila.addLast(leitor);
            return false;
        }

        // Empresta o livro ao leitor da 1 posição e remove-lo da fila
        leitor.realizarEmprestimo(livro); // erro dos sinais de Roberto - ou +
        livro.setEmprestado(true); // livro emprestado
        leitoresFila.addLast(leitor); //adicionar o 1º leitor no fim da fila
        return true;
    }
    //Receber o livro a ser devolvido pelo próximo (primeiro) leitor da fila
    public boolean receberLivro(int numeroDoLivroEmprestado) {
        // se a fila de leitores estiver vazia: error
        if(leitoresFila.isEmpty()){
            System.out.println("Falha: fila de leitores vazia");
            return false;
        }
        //se o indice do livro é valido
        if (numeroDoLivroEmprestado < 0 || numeroDoLivroEmprestado >=livros.size()){
            System.out.println("Falha: índice de livro inválido");
            leitoresFila.addLast(leitoresFila.removeFirst()); // coloca o 1 da fila no fim da fila.
            return false;
        }
        // O leitor que está no início da fila
        Leitor leitorAtual = leitoresFila.remove(); // ou removeFirst();
        Livro livroDevolvido = livros.get(numeroDoLivroEmprestado);

        if(!livroDevolvido.isEmprestado()){
            System.out.println("Falha: o livro não está emprestado");
            leitoresFila.addLast(leitorAtual);
            return false;
        }

        //se o leitor não tiver livro para devolver 
        if(!leitorAtual.possuiLivroEmprestado()){
            System.out.println("Falha: o leitor não possui  livro para devolver");
            leitoresFila.addLast(leitorAtual); // Move o leitor para o fim da fila
            return false;
        }
    
        // try {
        //     livroDevolvido = livros.get(numeroDoLivroEmprestado);
        // } catch (Exception e){
        //     System.out.println("Falha: index errado");
        // }

        //se a devolução ocorrer 
        livroDevolvido.setEmprestado(false); // atribui como não emprestado, ja que foi devolvido
        leitorAtual.realizarDevolucao(); // passar o livro como parametro(?)
        leitorAtual.setRemoverLivroEmprestado();
        System.out.println("Livro devolvido pelo leitor: " + leitorAtual.getNome()); // mostra quem devolveu o livro
        leitoresFila.addLast(leitorAtual);
        return true;
    }

    @Override
    public String toString() {
        int espacoDisponivel = capacidade - livros.size(); // dif cpaacidade e tam da lista
        String str = "Livros: {";

        for (Livro livro : livros){    
            str+= (livro != null ? livro.toString() : "[-----]") + " ";
        }
        
        for (int i = espacoDisponivel; i > 0; i--) {
            str+= ("[-----]");
        }

        str+= "}\n";

        str+= "Fila de leitores: {";
        for (Leitor leitor : leitoresFila){
            str+= leitor.toString() + " ";
        }
        str+= "}";

        return str;
    }
}