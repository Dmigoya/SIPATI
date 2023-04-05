import Model.PatTree;
import Service.TextIndexer;

import java.util.List;

public class Main {

    //"SIPATI" (Sufix and PATricia Tree Indexer)
    public static void main(String[] args) {
        TextIndexer textIndexer = new TextIndexer("C:\\Users\\Davex\\Documents\\GitHub\\SIPATI\\files\\");
        List<String> dirs = textIndexer.getPatriciaTree().findWord("princesa");
        if (dirs != null && !dirs.isEmpty()){
            dirs.forEach(System.out::println);
        }else{
            System.out.println("Fail");
        }
    }
}