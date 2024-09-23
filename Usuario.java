abstract class Usuario {
  protected String nome;
  protected int quantidadeEmprestimos;

  public Usuario (String nome){
    this.nome = nome;
    this.quantidadeEmprestimos = 0;
  }
  
  public String getNome(){
    reeturn nome;
  }

  public int getQuantidadeEmprestimos(){
    return quantidadeEmprestimos;
  }

  /* método abstrato
  * cada tipo de usuário implementa 
  * da sua forma específica */
  public abstract void realizarEmprestimo(ItemEmprestado item) throws ItemEmprestimoIndisponivelException; 
  public abstract void realizarDevolucao() throws ItemEmprestimoIndisponivelException;

  @Override
  public String toString(){
    return "[" + nome + ", "+ quantidadeEmprestimos + "]";
  }
}