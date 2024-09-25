//import error.MyException;

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
    @Override
    public void realizarEmprestimo(ItemEmprestado livroEmprestado) throws MyException {
        if (possuiLivroEmprestado()) { // Se o leitor já tem um livro emprestado
            throw new MyException ("O leitor já possui livro emprestado");
            // System.out.println(this.nome + " já possui um livro emprestado.");    
        }
        if (livroEmprestado.isEmprestado()) { // Se o livro já está emprestado para outra pessoa
            throw new MyException ("não foi possível realizar o empréstimo, o livro encontra-se indisponível");
            // System.out.println("Falha: livro indisponível.");
        }
       
        // this.livroEmprestado = (Livro) livroEmprestado; // Empresta o livro ao leitor
        // this.quantidadeEmprestimos++; // Incrementa o número de empréstimos
        // livroEmprestado.setEmprestado(true); // Marca o livro como emprestado
        // System.out.println(super.getNome() + "realizou o empréstimo: " + livroEmprestado.getTitulo());
        // System.out.println(this.nome + " realizou o empréstimo") + livroEmprestrado.getTitulo());
    }
    @Override
    public Livro realizarDevolucao() throws MyException {
         //opção de solução para a segunda entrega

         if (!possuiLivroEmprestado()){
            throw new MyException("O leitor não possui nenhum livro emprestado para devolver.");
         }
          Livro livroDevolvido = this.livroEmprestado;
          livroDevolvido.setEmprestado(false); // Marca o livro como devolvido
          this.livroEmprestado = null; // remove o livro do leitor
          return livroDevolvido;
        // return null; item implementado na 1 entrega
    }

    public void setRemoverLivroEmprestado(){
        this.livroEmprestado = null;
    }

    @Override
    public String toString() {
        String possuiLivro = possuiLivroEmprestado() ? "+" : "-"; 
        return "[" + super.getNome() + possuiLivro + ", " + this.quantidadeEmprestimos + "]";
        // super.getQuantidadeEmprestimos() = this.quantidadeEmprestimos
        //return "["+ this.nome + possuiLivro + ", " + this.quantidadeEmprestimos + "]";
        }
}