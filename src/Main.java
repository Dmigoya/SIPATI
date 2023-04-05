import Service.TextIndexer;

import java.util.List;

public class Main {

    //"SIPATI" (Sufix and PATricia Tree Indexer)
    public static void main(String[] args) {
        TextIndexer textIndexer = new TextIndexer("/mnt/gaveton/Programming/Workspaces/Idea/SIPATI/files");
        List<String> dirs = textIndexer.getPatriciaTree().searchWord("princesa");
        if (dirs != null && !dirs.isEmpty()){
            dirs.forEach(System.out::println);
        }else{
            System.out.println("Fail");
        }
        System.out.println("-----------------------------");
        dirs = textIndexer.getSuffixTree().search("princesa");
        if (dirs != null && !dirs.isEmpty()){
            dirs.forEach(System.out::println);
        }else{
            System.out.println("Fail");
        }
    }
}