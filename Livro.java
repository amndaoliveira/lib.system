class Livro extends ItemEmprestado {

    private int anoPublicado;
    private Genero genero;  // enum para gênero
    /* 
    * private String titulo; 
    * private boolean emprestado;
    */ 

    public Livro(String titulo, int anoPublicado, Genero genero) {
        super(titulo);
        this.anoPublicado = anoPublicado;
        this.genero = genero;
        regraDeValidacao();
    }
    public int getPrazoDevolucao(){
        return 30; //prazo de devolução será de 30 dias;
    }
    
    public Genero getGenero() {
        return this.genero;
    }
    // public String getTitulo(){
    //     return this.getTitulo();
    //  }
    // public boolean isEmprestado(){ 
    //     return this.emprestado;
    // }
    public int getAnoPublicado(){
        return this.anoPublicado;
    }

    public void setAnoPublicado(int anoPublicado){
        this.anoPublicado = anoPublicado; // verifica e ajusta, se necessário, assim que é alterado;
    }
    
    private boolean regraDeValidacao(){
        if (this.anoPublicado < 1440 ){
            // System.out.println("Falha: ano de publicação inválido");
            setAnoPublicado(1440); //define o ano publicado como 1440
            throw new MyException("Falha: ano de publicação inválido");

        } else if (this.anoPublicado > 2024){ 
            // System.out.println("Falha: ano de publicação inválido");
            setAnoPublicado(2024); //define o ano publicado como 2024
            throw new MyException("Falha: ano de publicação inválido");
        } else {
            return true;
        }
    }
    
    @Override
    public String toString() {
        
        String str = isEmprestado() ? "[-----]" : "[" + getTitulo() + ", " + this.anoPublicado + "]";
        return str;
    }
}
