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

    //Adicionar livros na biblioteca sempre no primeiro espaço vazio da lista de livros.
    public boolean adicionarItem(Livro itemAdd) throws MyException {
        /**
         * Step 1 - se tem capacidade
                    1.1 - realizar validacao do livro antes de adicionar na lista
         */
         if (livros.size() < capacidade){
            livros.addLast(itemAdd);
            return true;
         }
        //System.out.println("Falha: biblioteca cheia");
        throw new MyException("Falha: biblioteca cheia");
    }

    // Registrar um leitor na fila de espera (sempre no final da fila de leitores)
    public void registrarLeitor(Leitor addLeitoresFimDaFila) {
        leitoresFila.addLast(addLeitoresFimDaFila);
    }

    // Emprestar um livro para o próximo (primeiro) leitor da fila
    public boolean emprestarItem(int numeroDoItemEmprestado) throws MyException {

        if (leitoresFila.isEmpty()) { // Verificar se a fila de leitores está vazia.
            throw new MyException("Falha:  fila de leitores vazia");
        } 

        if (livros.isEmpty()) {
            throw new MyException("Falha: biblioteca vazia");
        }
         /**
         * Verificar se há algum livro disponível no índice indicado
         * - mostrar uma mensagem de erro caso não haja.
         */
        if (numeroDoItemEmprestado < 0 || numeroDoItemEmprestado >= livros.size()){
            throw new MyException("Falha: indice do livro inválido");
        }

        Leitor leitor = leitoresFila.removeFirst();
        Livro livro = livros.get(numeroDoItemEmprestado);
         
        if (livro.isEmprestado()) {
            //System.out.println("Falha: livro já emprestado");
            leitoresFila.addLast(leitor);
            throw new MyException("Falha: item já emprestado");
            //return false;
        }
        /**
         * Verificar se o leitor já possui um livro emprestado
         * - mostrar uma mensagem de erro caso possua.
         * acho que está redundante, tem a mesmas infos em leitor
         */ 
        if (leitor.possuiLivroEmprestado()) {
            System.out.println("Falha: o leitor já um item emprestado");
            leitoresFila.addLast(leitor);
            return false;
        }
        // Empresta o livro ao leitor.
        try {
            leitor.realizarEmprestimo(livro);
            leitoresFila.addLast(leitor);
            return true;
        } catch (MyException e) {
            System.out.println(e.getMessage());
            leitoresFila.addLast(leitor); // Retorna o leitor à fila
            return false;
        }
    }

    //Receber o livro a ser devolvido pelo próximo (primeiro) leitor da fila
    public boolean receberItem(int numeroDoItemEmprestado) throws MyException {
        // se a fila de leitores estiver vazia: error
        if(leitoresFila.isEmpty()){
            throw new MyException("Falha:  fila de leitores vazia");
//            System.out.println("Falha: fila de leitores vazia");
//            return false;
        }
        //se o indice do livro é valido
        if (numeroDoItemEmprestado < 0 || numeroDoItemEmprestado >=livros.size()){
            //System.out.println("Falha: índice de item inválido");
            leitoresFila.addLast(leitoresFila.removeFirst()); // coloca o 1 da fila no fim da fila.
            throw new MyException("Falha: índice de item inválido");
            // return false;
        }
        // O leitor que está no início da fila
        Leitor leitorAtual = leitoresFila.remove(); // ou removeFirst();
        Livro livroDevolvido = livros.get(numeroDoItemEmprestado);
        
        if(!livroDevolvido.isEmprestado()){
            //System.out.println("Falha: o item não está emprestado");
            leitoresFila.addLast(leitorAtual);
            throw new MyException("Falha: o item não está emprestado");
            // return false;
        }
        //se o leitor não tiver livro para devolver 
        if(!leitorAtual.possuiLivroEmprestado()){
            //System.out.println("Falha: o leitor não possui  livro para devolver");
            leitoresFila.addLast(leitorAtual); // Move o leitor para o fim da fila
            throw new MyException("Falha: o leitor não possui  item para devolver");
            //return false;
        }        
        try {
            leitorAtual.realizarDevolucao(); // Tenta realizar a devolução
            livroDevolvido.setEmprestado(false); // Marca o item como não emprestado
            leitoresFila.addLast(leitorAtual);
            System.out.println(livroDevolvido + " devolvido por: " + leitorAtual.getNome());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            leitoresFila.addLast(leitorAtual); // Retorna o leitor à fila
            return false;
        }
    }

    @Override
    public String toString() { // ajustar
        int espacoDisponivel = capacidade - livros.size(); // dif capacidade e tam da lista
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