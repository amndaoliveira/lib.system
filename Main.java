import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca bib = new Biblioteca( 1 );

        while (true) {
            System.out.print("$");
            String line = scanner.nextLine();
            String[] parts = line.split(" "); 

            if      ( parts[0].equals("end")        )  { break;                                               }
            else if ( parts[0].equals("show")       )  { System.out.println( bib );                           }
            else if ( parts[0].equals("init")       )  { bib = new Biblioteca( Integer.parseInt(parts[1]) );  }
            else if ( parts[0].equals("leitor")     )  { bib.registrarLeitor( new Leitor( parts[1], 0 ) );    }
            else if ( parts[0].equals("emprestar")  )  { bib.emprestarLivro( Integer.parseInt(parts[1]) );    }
            else if ( parts[0].equals("receber")    )  { bib.receberLivro(Integer.parseInt(parts[1]));                                  }
            else                                      { System.out.println("fail: comando invalido");         }
        }
        scanner.close();
    }
}