public abstract class ItemEmprestado {
  private String titulo;
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

  public String setTitulo(String titulo){
      return this.titulo = titulo;
  }
  public String getTitulo(){
    return titulo;
  }
  // Método abstrato que será implementado nas subclasses
  public abstract int getPrazoDevolucao();
  
}