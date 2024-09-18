class Leitor {
    private String nome;
    private int quantidadeEmprestimos;
    private Livro livroEmprestado;
    
    public Leitor(String nome, int quantidadeEmprestimos) {
        this.nome = nome;
        this.quantidadeEmprestimos = quantidadeEmprestimos;
        this.livroEmprestado = null; //inicializa sem livro emprestado
    }

    public String getNome() {
        return this.nome;
    }

    // Verificar se já possui um livro emprestado (possuiLivroEmprestado)
    public boolean possuiLivroEmprestado(){
        if(this.livroEmprestado!=null && livroEmprestado.isEmprestado()){ // se o array de livros nao estiver vazio, nao pode pegar o livro ou se o livro a ser emprestado já estiver emprestado
            return true;
        } 
        return false;
    }
    
    public void realizarEmprestimo(Livro livroEmprestado) {
        if (possuiLivroEmprestado()) { // Se o leitor já tem um livro emprestado
                System.out.println(this.nome + " já possui um livro emprestado.");    
        }
        if (livroEmprestado.isEmprestado()) { // Se o livro já está emprestado para outra pessoa
                System.out.println("Falha: livro indisponível.");
        }
       
        this.livroEmprestado = livroEmprestado; // Empresta o livro ao leitor
        this.quantidadeEmprestimos++; // Incrementa o número de empréstimos
        livroEmprestado.setEmprestado(true); // Marca o livro como emprestado
        System.out.println(this.nome + " realizou o empréstimo");
    }

    public Livro realizarDevolucao() {
        return null;
    }

    @Override
    public String toString() {
        String possuiLivro = possuiLivroEmprestado() ? "+" : "-"; 
        return "["+ this.nome + possuiLivro + ", " + this.quantidadeEmprestimos + "]";
        }
}