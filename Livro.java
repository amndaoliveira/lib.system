class Livro {
    private String titulo;
    private int anoPublicado;
    private boolean emprestado;
    
    public Livro(String titulo, int anoPublicado) {
        this.titulo=titulo; 
        this.anoPublicado = anoPublicado;
        this.emprestado = false; // livro começa sempre como nao emprestado
        regraDeValidacao();
    }
    
    public boolean isEmprestado(){ 
        return this.emprestado;
    }
    public void setEmprestado(boolean emprestado){
        this.emprestado = emprestado;
    }

    public String getTitulo(){
       return this.titulo;
    }
    public int getAnoPublicado(){
        return this.anoPublicado;
    }
    public String setTitulo(String titulo){
        return this.titulo = titulo;
    }
    public void setAnoPublicado(int anoPublicado){
        this.anoPublicado = anoPublicado; // verifica e ajusta, se necessário, assim que é alterado;
    }
    
    private boolean regraDeValidacao(){
        if (this.anoPublicado < 1440 ){
            System.out.println("Falha: ano de publicação inválido");
            setAnoPublicado(1440); //define o ano publicado como 1440
            return false;
        } else if (this.anoPublicado > 2024){ 
            System.out.println("Falha: ano de publicação inválido");
            setAnoPublicado(2024); //define o ano publicado como 2024
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String toString() {
        
        String str = emprestado ? "[-----]" : "[" + this.titulo + ", " + this.anoPublicado + "]";
        return str;
    }
}
