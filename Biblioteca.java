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
        if(livros.size()< capacidade){
            livros.add(livroAdd);
            return true;
        }
        System.out.println("Falha: biblioteca cheia");
        return false;

        // for(int i=0; i< livros.length; i++){  // percorre todas as posições 
        //     if(livros[i]==null){ // ao encontrar a 1a posição vazia, 
        //         livros[i] = livroAdd; // adiciona o livro na primeira posição vazia
        //         return true; // retorna true indicando que o livro foi adicionado na posição [i] de livros
        //     }
        // }
        // System.out.println("Falha: biblioteca está cheia"); // se não encontrar nenhuma "vazia"
        // return false; // sinalizando que não foi possivel add pq a linkedlist está cheia
    }
    // Registrar um leitor na fila de espera (sempre no final da fila de leitores)
    public void registrarLeitor(Leitor addLeitoresFimDaFila) {
        leitoresFila.addLast(addLeitoresFimDaFila); // se usar so o add(), mudaria algo?
    }
    // Emprestar um livro para o próximo (primeiro) leitor da fila
    public boolean emprestarLivro(int numeroDoLivroEmprestado) {
        // Remover o leitor da fila após o empréstimo.
        // Garantir que o livro ainda não tenha sido emprestado.

        if(leitoresFila.isEmpty()){ // Verificar se a fila de leitores está vazia.
            System.out.println("Falha: fila de leitores vazia"); // nao pode emprestar
            return false;
        } 

        if(numeroDoLivroEmprestado < 0 || numeroDoLivroEmprestado >= livros.size() ) { // Verificar se o índice do livro é válido
            System.out.println("Falha: índice de livro inválido");
            return false;
        }

        Livro livro = livros.get(numeroDoLivroEmprestado); //explicar oq isso faz
        if(livro == null || livro.isEmprestado() ){ // Verifica se o livro existe ou (ja está emprestado)<- fazer essa parte 
            System.out.println("Falha: livro já está emprestado");
            return false;
        }
        // Empresta o livro ao leitor da 1 posição e remove-lo da fila
        Leitor leitor = leitoresFila.removeFirst();
        livro.setEmprestado(true); // livro emprestado
        leitor.realizarEmprestimo(livro);
        System.out.println("Livro emprestado ao leitor: " + leitor.getNome()); // mostra que o livro foi emprestado ao respectivo leitor
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
        String str = "Livros: {";
        for (Livro livro : livros){
            str+= (livro != null ? livro.toString() : "[-----]") + " ";
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