// import error.MyException;

abstract class Usuario {
  protected String nome; // posso mudar para private
  protected int quantidadeEmprestimos;

  public Usuario (String nome){
    this.nome = nome;
    this.quantidadeEmprestimos = 0;
  }
  
  public String getNome(){
    return nome;
  }

  public int getQuantidadeEmprestimos(){
    return quantidadeEmprestimos;
  }

  /* método abstrato
  * cada tipo de usuário implementa 
  * da sua forma específica */
  public abstract void realizarEmprestimo(ItemEmprestado item) throws MyException; 
  public abstract Livro realizarDevolucao() throws MyException;

  @Override
  public String toString(){
    return "[" + nome + ", "+ quantidadeEmprestimos + "]";
  }
}