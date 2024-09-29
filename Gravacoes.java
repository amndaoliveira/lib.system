public class Gravacoes extends ItemEmprestado{

  public Gravacoes(String titulo, int anoPublicado, Genero genero){
    super(titulo,anoPublicado, genero);
    regraDeValidacao();
  }

  @Override
  public Genero getGenero(){
    return this.genero; // definir o geneor apenas para gravações, caso nao seja emitir msg de erro
  }

  @Override
  public int getPrazoDevolucao(){
    return  7;
  }

  @Override
  protected boolean regraDeValidacao(){
    if (this.anoPublicado < 1895 ){
        setAnoPublicado(1895);
        throw new MyException("Falha: não há gravação neste período.");

    } else if (this.anoPublicado > 2024){ 
        setAnoPublicado(2024);
        throw new MyException("Falha: não há gravações do futuro ;D. ");
    } else {
        return true;
    }
}

  @Override
  public String toString() {
      
      String str = isEmprestado() ? "[-----]" : "[" + getTitulo() + ", " + this.anoPublicado + ", "+ getGenero() + "]";
      return str;
  }
}