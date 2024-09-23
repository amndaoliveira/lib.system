class Leitor extends Usuario {
    /* não se fazem mais necessários devido a herança
    private String nome;
    private int quantidadeEmprestimos;*/ 
    private Livro livroEmprestado;
    
    public Leitor(String nome, int quantidadeEmprestimos) {
        /*não se fazem mais necessários devido a herança
        this.nome = nome;
        this.quantidadeEmprestimos = quantidadeEmprestimos;*/
        super(nome);
        this.livroEmprestado = null; //inicializa sem livro emprestado
    }
    /*  não se faz necessário nessa nova fase devido a implementação da herança  
    public String getNome() {
        return this.nome;
    }*/

    // Verificar se já possui um livro emprestado (possuiLivroEmprestado)
    public boolean possuiLivroEmprestado(){
        return this.livroEmprestado!=null;
    }
    
    public void realizarEmprestimo(ItemEmprestado livroEmprestado) throws ItemEmprestimoIndisponivelException {
        if (possuiLivroEmprestado()) { // Se o leitor já tem um livro emprestado
            throw new ItemEmprestimoIndisponivelException ("O leitor já possui livro emprestado");
            // System.out.println(this.nome + " já possui um livro emprestado.");    
        }
        if (livroEmprestado.isEmprestado()) { // Se o livro já está emprestado para outra pessoa
            throw new ItemEmprestimoIndisponivelException ("não foi possível realizar o empréstimo, o livro encontra-se indisponível");
            // System.out.println("Falha: livro indisponível.");
        }
       
        this.livroEmprestado = (Livro) livroEmprestado; // Empresta o livro ao leitor
        this.quantidadeEmprestimos++; // Incrementa o número de empréstimos
        livroEmprestado.setEmprestado(true); // Marca o livro como emprestado
        System.out.println(this.nome + " realizou o empréstimo") + livroEmprestrado.getTitulo());
    }
    @Override
    public Livro realizarDevolucao() {
        // return null;
        
    }

    public void setRemoverLivroEmprestado(){
        this.livroEmprestado = null;
    }

    @Override
    public String toString() {
        String possuiLivro = possuiLivroEmprestado() ? "+" : "-"; 
        return "["+ this.nome + possuiLivro + ", " + this.quantidadeEmprestimos + "]";
        }
    /*
    @Override
    public String toString() {
        return super.toString() + (possuiLivroEmprestado() ? " + (livro emprestado)" : " -");
    }
     */
}