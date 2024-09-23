class ItemEmprestado {
  private string titulo;
  private boolean emprestado;

  public ItemEmprestado (String titulo){
    this.titulo = titulo;
    this.emprestado = false;
  }

  public boolean isEmprestado(){
    return emprestado;
  }

  public void setEmprestado(boolean emprestado){
    this.emprestado = emprestado;
  }
  public String getTitulo(){
    return titulo;
  }

  public abstract int getPrazoDevolucao();
  
}