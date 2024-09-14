import java.util.*;

class Main{
    public static void main(String[] args ){
      Biblioteca bib = new Biblioteca(1);

      // reformular essa parte para java nativo
      while (true){
        String[] line = IO.inputPartsPrintingLine();
        //static public String[] inputPartsPrintingLine() {
        // String line = IO.input();
        // IO.println("$" + line);
        // return line.split(" ");
        if      ( line[0].equals("end")        )  { break;                                                            }
        else if ( line[0].equals("show")       )  { IO.println( bib );                                                }
        else if ( line[0].equals("init")       )  { bib = new Biblioteca( IO.strToInt(line[1]) );                     }
        else if ( line[0].equals("leitor")     )  { bib.registrarLeitor( new Leitor( line[1] ) );                     }
        else if ( line[0].equals("emprestar")  )  { bib.emprestarLivro( IO.strToInt(line[1]) );                       }
        else if ( line[0].equals("receber")    )  { bib.receberLivro();                                               }
        else                                      { IO.println("fail: comando invalido");                             }
    }

  }
}