public abstract class ItemEmprestado {
  protected String titulo;
  protected boolean emprestado;
  protected int anoPublicado;
  protected Genero genero;

  public ItemEmprestado (String titulo, int anoPublicado, Genero genero){
    this.titulo = titulo;
    this.anoPublicado = anoPublicado;
    this.genero = genero;
    this.emprestado = false;

  }
  public void setAnoPublicado(int anoPublicado){
    this.anoPublicado = anoPublicado; // verifica e ajusta, se necessário, assim que é alterado;
  }
  public boolean isEmprestado(){
    return emprestado;
  }

  public void setEmprestado(boolean emprestado){
    this.emprestado = emprestado;
  }

  public String setTitulo(String titulo){
      return this.titulo = titulo;
  }
  public String getTitulo(){
    return this.titulo;
  }

  //Método abstrato que será implementado nas subclasses
  public abstract int getPrazoDevolucao();
  public abstract boolean regraDeValidacao();

  
}