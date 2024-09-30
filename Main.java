import java.util.Scanner;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca bib = new Biblioteca(1);

        while (true) {
            System.out.print("$");
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");

            if (parts.length == 0 || parts[0].isEmpty()) {
                System.out.println("fail: comando inválido");
                continue;
            }

            switch (parts[0]) {
                case "end":
                    return;

                case "show":
                    System.out.println(bib);
                    break;

                case "init":
                    if (validateArgs(parts, 2)) {
                        bib = new Biblioteca(Integer.parseInt(parts[1]));
                    }
                    break;

                case "leitor":
                    if (validateArgs(parts, 2)) {
                        bib.registrarLeitor(new Leitor(parts[1], 0));
                    }
                    break;

                case "livro":
                    if (validateArgs(parts, 4)) {
                        Genero genero = Genero.valueOf(parts[3].toUpperCase()); // Converter o gênero para maiúsculas
                        try {
                            bib.adicionarItem(new Livro(parts[1], Integer.parseInt(parts[2]), genero));
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "gravacao":
                    if (validateArgs(parts, 4)) {
                        Genero genero = Genero.valueOf(parts[3].toUpperCase()); // Converter o gênero para maiúsculas
                        try {
                            bib.adicionarItem(new Livro(parts[1], Integer.parseInt(parts[2]), genero));
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;


                case "emprestar":
                    if (validateArgs(parts, 2)) {
                        try {
                            bib.emprestarItem(Integer.parseInt(parts[1]));
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "receber":
                    if (validateArgs(parts, 2)) {
                        try{
                            bib.receberItem(Integer.parseInt(parts[1]));
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "bd":
                    bib = new Biblioteca(10);
                    Livro livroA = new Livro("Sagarana", 1800, Genero.LITERATURA);
                    Livro livroB = new Livro("iracema", 1986, Genero.ROMANCE);
                    Livro livroC = new Livro("CasaAZul", 1567, Genero.FICCAO);

                    Leitor leitorA = new Leitor("joao", 0);
                    Leitor leitorB = new Leitor("theo", 0);
                    Leitor leitorC = new Leitor("caio", 0);
                    Leitor leitorD = new Leitor("melissa", 0);


                    bib.registrarLeitor(leitorA);
                    bib.registrarLeitor(leitorB);
                    bib.registrarLeitor(leitorC);
                    bib.registrarLeitor(leitorD);

                    bib.adicionarItem(livroA);
                    bib.adicionarItem(livroB);
                    bib.adicionarItem(livroC);
                    System.out.println("Dados inseridos com sucesso!");
                    break;
                default:
                    System.out.println("fail: comando inválido");
            }
        }
    }

    // Função que valida se o número de argumentos está correto
    private static boolean validateArgs(String[] parts, int expectedLength) {
        if (parts.length < expectedLength) {
            System.out.println("fail: parâmetros insuficientes para o comando '" + parts[0] + "'");
            return false;
        }
        return true;
    }
}
