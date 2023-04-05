import Service.TextIndexer;

import java.util.List;
import java.util.Scanner;

public class Main {

    //"SIPATI" (Sufix and PATricia Tree Indexer)
    public static void main(String[] args) {
        System.out.println("Indexando...");
        TextIndexer textIndexer = new TextIndexer("/mnt/gaveton/Programming/Workspaces/Idea/SIPATI/files");
        System.out.println("¡Documentos indexados!");
        while(true){
            System.out.println("Menu:");
            System.out.println("1) PAT Tree");
            System.out.println("  a) Buscar palabra");
            System.out.println("  b) Eliminar palabra");
            System.out.println("  c) Ver arbol");
            System.out.println("2) Suffix Tree");
            System.out.println("  a) Buscar subcadena");
            System.out.println("  b) Ver arbol");
            Scanner scanner=new Scanner(System.in);
            String line=scanner.nextLine();
            List<String> dirs;
            switch (line.charAt(0)){
                case '1':
                    switch (line.charAt(1)){
                        case 'a': //Search word
                            System.out.println("Buscar: Escribe la palabra");
                            line=scanner.nextLine();
                            line=line.toLowerCase().trim();
                            dirs=textIndexer.getPatriciaTree().searchWord(line);
                            System.out.println("Documentos encontrados:");
                            System.out.println(dirs);
                            break;
                        case 'b': //Delete word
                            System.out.println("Eliminar: Escribe la palabra");
                            line=scanner.nextLine();
                            line=line.toLowerCase().trim();
                            textIndexer.getPatriciaTree().removeWord(line);
                            System.out.println("Palabra eliminada");
                            break;
                        case 'c': //Show tree
                            System.out.println("PAT Tree: mostrando...");
                            System.out.println(textIndexer.getPatriciaTree());
                            break;
                    }
                    break;
                case '2':
                    switch (line.charAt(1)){
                        case 'a': //Search substring
                            System.out.println("Buscar: Escribe la subcadena");
                            line=scanner.nextLine();
                            dirs=textIndexer.getSuffixTree().search(line);
                            System.out.println("Documentos encontrados:");
                            System.out.println(dirs);
                            break;
                        case 'b': //Show tree
                            System.out.println("Suffix Tree: mostrando...");
                            System.out.println(textIndexer.getSuffixTree());
                            break;
                    }
                    break;
            }

        }

    }
}