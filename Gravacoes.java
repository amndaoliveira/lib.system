public class Gravacoes extends ItemEmprestado{

  public Gravacoes(String titulo, int anoPublicado){
    super(titulo,anoPublicado, Genero.GRAVACOES);
    regraDeValidacao();
  }

//  @Override
//  public Genero getGenero(){
//    return this.genero; // definir o genero apenas para gravações, caso nao seja emitir msg de erro
//  }

  @Override
  public int getPrazoDevolucao(){
    return  7;
  }

  @Override
  public boolean regraDeValidacao(){
    if (super.anoPublicado < 1895 ){
        setAnoPublicado(1895);
        throw new MyException("Falha: não há gravação neste período.");

    } else if (super.anoPublicado > 2024){
        setAnoPublicado(2024);
        throw new MyException("Falha: não há gravações do futuro ;D. ");
    } else {
        return true;
    }
}

  @Override
  public String toString() {

      String str = isEmprestado() ? "[-----]" : "[" + getTitulo() + ", " + this.anoPublicado + ", "+ super.genero + "]";
      return str;
  }
}