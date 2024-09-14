
class Leitor {
  private String nome;
  private Livro livroEmprestado;
  private int quantidadeEmprestimos;

  public Leitor(String nome) {
      this.nome = nome;
      this.livroEmprestado = null;
      this.quantidadeEmprestimos = 0;
  }

  public boolean possuiLivroEmprestado() {
      return livroEmprestado != null;
  }

  public String getNome() {
      return nome;
  }

  public int getQuantidadeEmprestimos() {
      return quantidadeEmprestimos;
  }

  public void realizarEmprestimo(Livro livro) {
      if (!possuiLivroEmprestado()) {
          this.livroEmprestado = livro;
          this.quantidadeEmprestimos++;
          System.out.println(nome + " já pegou " + quantidadeEmprestimos + " livro(s) emprestado(s)");
      } else {
          System.out.println("Falha: leitor já possui um livro"); // implementar o throws
      }
  }

  public Livro realizarDevolucao() {
      if (possuiLivroEmprestado()) {
          Livro livroDevolvido = livroEmprestado;
          livroEmprestado = null;
          return livroDevolvido;
      } else {
          System.out.println("Falha: leitor não possui um livro para devolver."); // implementar o throws
          return null;
      }
  }

  @Override
  public String toString() {
      return "[" + nome + (possuiLivroEmprestado() ? "+" : "-") + ", " + quantidadeEmprestimos + "]";
  }
}
