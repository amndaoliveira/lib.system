import java.util.Queue; // como fazer sem isso?
import java.util.LinkedList;

class Biblioteca {
    private Livro[] livros;
    private Queue<Leitor> filaLeitores;

    public Biblioteca(int tamanhoLivros) {
        this.livros = new Livro[tamanhoLivros];
        this.filaLeitores = new LinkedList<>();
    }

    public void adicionarLivro(Livro livro) {
        for (int i = 0; i < livros.length; i++) {
            if (livros[i] == null) {
                livros[i] = livro;
                return;
            }
        }
        System.out.println("Falha: a biblioteca está cheia"); // implementar o throws
    }

    public void registrarLeitor(Leitor leitor) {
        filaLeitores.add(leitor);
    }

    public void emprestarLivro(int indiceLivro) {
        if (filaLeitores.isEmpty()) {
            System.out.println("Falha: fila de leitores vazia"); // implementar o throws
            return;
        }

        Leitor leitor = filaLeitores.peek();

        if (leitor.possuiLivroEmprestado()) {
            System.out.println("Falha: leitor já possui um livro"); // implementar o throws
            return;
        }

        if (indiceLivro < 0 || indiceLivro >= livros.length || livros[indiceLivro] == null) {
            System.out.println("Falha: livro indisponível"); // implementar o throws
            return;
        }

        leitor.realizarEmprestimo(livros[indiceLivro]);
        livros[indiceLivro] = null;
        filaLeitores.poll();
        filaLeitores.add(leitor);
    }

    public void receberLivro() {
        if (filaLeitores.isEmpty()) {
            System.out.println("Falha: fila de leitores vazia"); // implementar o throws
            return;
        }

        Leitor leitor = filaLeitores.peek();

        if (!leitor.possuiLivroEmprestado()) {
            System.out.println("Falha: leitor não possui um livro para devolver."); // implementar o throws
            return;
        }

        Livro livroDevolvido = leitor.realizarDevolucao();

        for (int i = 0; i < livros.length; i++) {
            if (livros[i] == null) {
                livros[i] = livroDevolvido;
                break;
            }
        }
        filaLeitores.poll();
        filaLeitores.add(leitor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Livros: { ");
        for (Livro livro : livros) {
            sb.append(livro != null ? livro.toString() : "[-----]").append(" ");
        }
        sb.append("}\nFila de Leitores: { ");
        for (Leitor leitor : filaLeitores) {
            sb.append(leitor.toString()).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}