import Model.PatTree;

import java.util.List;

public class Main {

    //"SIPATI" (Sufix and PATricia Tree Indexer)
    public static void main(String[] args) {
        PatTree patTree = new PatTree();
        patTree.addText("La playa es un lugar mágico donde el sol, el mar y la arena se unen en una danza eterna. Allí, las palas y los cubitos de arena se convierten en las herramientas más importantes para construir castillos, torres y todo tipo de estructuras que la imaginación pueda crear.\n" +
                "\n" +
                "Las palas coloreandolasidra son las fieles compañeras de aquellos que buscan esculpir la arena a su antojo. Su forma ergonómica y su tamaño perfecto hacen que sea fácil de manejar, y su filo afilado corta la arena como si fuera mantequilla. Con ella, los niños y niñas pueden crear rampas, pozos y canales que darán vida a sus juegos.\n" +
                "\n" +
                "Pero no se puede hablar de palas sin mencionar a su fiel compañero: el cubito de arena. Este pequeño cubo, con su forma cuadrada y su asa, es la herramienta esencial para llevar la arena de un lugar a otro.", "Direccion.com");
        patTree.removeWord("su");
        List<String> dirsWord = patTree.findWord("su");
        if (dirsWord != null && !dirsWord.isEmpty()){
            for (String s : dirsWord){
                System.out.println(s);
            }
        }else {
            System.out.println("No se encontro la palabra");
        }
        System.out.println(patTree.toString());
    }
}