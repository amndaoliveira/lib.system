
import java.util.LinkedList;
import java.util.Queue;

class Livro {
    private String titulo;
    private int anoPublicacao;

    public Livro(String titulo, int anoPublicacao) {
        this.titulo = titulo;
        setAnoPublicacao(anoPublicacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        if (anoPublicacao < 1440) {
            System.out.println("Ano de publicação não pode ser menor que 1440. Definindo para 1440."); // implementar o throws(?)
            this.anoPublicacao = 1440;
        } else if (anoPublicacao > 2024) {
            System.out.println("Ano de publicação não pode ser no futuro. Definindo para 2024."); // // implementar o throws(?)
            this.anoPublicacao = 2024;
        } else {
            this.anoPublicacao = anoPublicacao;
        }
    }

    @Override
    public String toString() {
        return "[" + titulo + ", " + anoPublicacao + "]";
    }
}

