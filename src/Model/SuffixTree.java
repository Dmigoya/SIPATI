package Model;

import java.util.*;

public class SuffixTree {

    private Node root=new Node("");

    public void insertSuffixes(String document, String documentDir) {
        for (int i = 0; i < document.length(); i++) {
            String suffix = document.substring(i);
            insertSuffix(suffix, documentDir, root);
        }
    }

    private void insertSuffix(String suffix, String documentDir, Node node) {
        if(node.getDirs()!=null){
            if(!node.getDirs().contains(documentDir))
                node.addDir(documentDir);
        }else{
        node.addDir(documentDir);
        }
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

    public List<String> search(String query) {
        Node node = root;
        for (char c : query.toCharArray()) {
            int ind=indexOfChild(node,""+c);
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
        if(node.getNodes()!=null)
            for (int i = 0; i < node.getNodes().size(); i++) {
                if(node.getNodes().get(i).getValue().equals(value))
                    return i;
            }
        return -1;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        SuffixTree suffixTree =new SuffixTree();
        List<String> documents = Arrays.asList("hola mundo", "mundo cruel", "hola hola");
        for (int i = 0; i < documents.size(); i++) {
            suffixTree.insertSuffixes(documents.get(i), "f_"+i);
        }
        List<String> result = suffixTree.search("hola");
        System.out.println(result); // Output: [0, 2]
    }

}