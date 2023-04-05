package Model;

import java.util.*;

public class SuffixTreeIndexer {

    private Node root=new Node("");

    private void insertSuffixes(String document, String documentDir) {
        for (int i = 0; i < document.length(); i++) {
            String suffix = document.substring(i);
            insertSuffix(suffix, documentDir, root);
        }
    }

    private void insertSuffix(String suffix, String documentDir, Node node) {
        node.addDir(documentDir);
        if (suffix.isEmpty()) {
            return;
        }
        char firstChar = suffix.charAt(0);
        Node childNode=null;
        if(node.getNodes()!=null){
            int ind=indexOfChild(node,firstChar+"");
            if(ind!=-1) {
                childNode = node.getNodes().get(ind);
            }
        }
        if (childNode == null) {
            childNode = new Node(firstChar+"");
            node.addNode(childNode);
        }
        insertSuffix(suffix.substring(1), documentDir, childNode);
    }

    private List<String> search(String query) {
        Node node = root;
        System.out.println("nodes: "+node.getNodes());
        for (char c : query.toCharArray()) {
            int ind=indexOfChild(node,""+c);
            System.out.println("char: "+c);
            System.out.println("here "+ind);
            if(ind!=-1) {
                node = node.getNodes().get(ind);
            }else
                return Collections.emptyList();
        }
        return node.getDirs();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb, "", "", root);
        return sb.toString();
    }

    private void buildString(StringBuilder sb, String prefix, String childrenPrefix, Node node) {
        sb.append(prefix);
        sb.append(node.getValue());
        sb.append("\n");
        if (node.getDirs() != null && !node.getDirs().isEmpty()) {
            sb.append(prefix);
            sb.append(" Dirs: ");
            sb.append(node.getDirs().toString());
            sb.append("\n");
        }
        if (node.getNodes() != null) {
            int size = node.getNodes().size();
            for (int i = 0; i < size; i++) {
                Node child = node.getNodes().get(i);
                if (i == size - 1) {
                    buildString(sb, childrenPrefix + "└── ", childrenPrefix + "    ", child);
                } else {
                    buildString(sb, childrenPrefix + "├── ", childrenPrefix + "│   ", child);
                }
            }
        }
    }

    private int indexOfChild(Node node, String value){
        for (int i = 0; i < node.getNodes().size(); i++) {
            if(node.getNodes().get(i).getValue().equals(value))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        SuffixTreeIndexer suffixTreeIndexer=new SuffixTreeIndexer();
        List<String> documents = Arrays.asList("hola mundo", "mundo cruel", "hola hola");
        for (int i = 0; i < documents.size(); i++) {
            suffixTreeIndexer.insertSuffixes(documents.get(i), "f_"+i);
        }
        List<String> result = suffixTreeIndexer.search("hola");
        System.out.println(result); // Output: [0, 2]
    }

}