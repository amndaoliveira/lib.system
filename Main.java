// import java.util.Scanner;

// class Main {
//     public static void main (String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         Biblioteca bib = new Biblioteca( 1 );

//         while (true) {
//             System.out.print("$");
//             String line = scanner.nextLine();
//             String[] parts = line.split(" "); 

//             if      ( parts[0].equals("end")        )  { break;                                               }
//             else if ( parts[0].equals("show")       )  { System.out.println( bib );                           }
//             else if ( parts[0].equals("init")       )  { bib = new Biblioteca( Integer.parseInt(parts[1]) );  }
//             else if ( parts[0].equals("leitor")     )  { bib.registrarLeitor( new Leitor( parts[1], 0 ) );    }
//             else if ( parts[0].equals("livro")     )  {  bib.adicionarLivro( new Livro( parts[1], Integer.parseInt(parts[2])) );    }
//             else if ( parts[0].equals("emprestar")  )  { bib.emprestarLivro( Integer.parseInt(parts[1]) );    }
//             else if ( parts[0].equals("receber")    )  { bib.receberLivro(Integer.parseInt(parts[1]));                                  }
//             else                                      { System.out.println("fail: comando invalido");         }
//         }
//         scanner.close();
//     }
// }

import java.util.Scanner;

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
                    if (validateArgs(parts, 3)) {
                        bib.adicionarLivro(new Livro(parts[1], Integer.parseInt(parts[2])));
                    }
                    break;

                case "emprestar":
                    if (validateArgs(parts, 2)) {
                        bib.emprestarLivro(Integer.parseInt(parts[1]));
                    }
                    break;

                case "receber":
                    if (validateArgs(parts, 2)) {
                        bib.receberLivro(Integer.parseInt(parts[1]));
                    }
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
