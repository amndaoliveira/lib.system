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
        if(leitoresFila.isEmpty()){ // Verificar se a fila de leitores está vazia.
            System.out.println("Falha: fila de leitores vazia"); // nao pode emprestar
            return false;
        } 
       // Leitor leitor = leitoresFila.getFirst();
        Leitor leitor = leitoresFila.removeFirst();
        Livro livro = livros.get(numeroDoLivroEmprestado);

        /**
         * Verificar se o leitor já possui um livro emprestado
         * - mostrar uma mensagem de erro caso possua.
         */
        if (leitor.possuiLivroEmprestado()) {
            System.out.println("Falha: o leitor já possui livro emprestado");
            return false;
        }

        /**
         * Verificar se há algum livro disponível no índice indicado
         * - mostrar uma mensagem de erro caso não haja.
         */

        if(livro == null) {
            System.out.println("Falha: livro não encontrado");
            return false;
        }

        if(livro == null || livro.isEmprestado() ){ // Verifica se o livro existe ou ja está emprestado
            System.out.println("Falha: livro já está emprestado");
            return false;
        }
        // Empresta o livro ao leitor da 1 posição e remove-lo da fila
        leitor.realizarEmprestimo(livro);
        livro.setEmprestado(true); // livro emprestado
        //adicionar o 1º leitor no fim da fila
        leitoresFila.addLast(leitor);
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
        if (numeroDoLivroEmprestado <0 || numeroDoLivroEmprestado >=livros.size()){
            System.out.println("Falha: índice de livro inválido");
            return false;
        }
        //s eo livro já está emprestado
        Livro livro = livros.get(numeroDoLivroEmprestado);
        if (!livro.isEmprestado()) {
            System.out.println("Falha: livro não está emprestado");
        return false;
        } 
        Leitor leitor = leitoresFila.remove();
        livro.setEmprestado(false); // atribui como não emprestado, ja que foi devolvido
        leitor.realizarDevolucao();
        System.out.println("Livro devolvido pelo leitor: " + leitor.getNome()); // mostra quem devolveu o livro
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