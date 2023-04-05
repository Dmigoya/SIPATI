package Model;

import Service.UserfulText;

import java.util.List;

public class PatTree {

    private Node root;

    public PatTree() {
        this.root = new Node("");
    }

    public void addText(String text, String dir){
        String[] processedText = UserfulText.preprocessText(text);
        for (String word : processedText){
            addWord(word, dir);
        }
    }

    private void addWord(String word, String dir){
        Node node = root;
        Node nodeTmp = null;
        List<Node> nodes = node.getNodes();
        for (char c : word.toCharArray()){
            nodeTmp = new Node(c);
            if (nodes != null && !nodes.isEmpty()){
                int index = nodes.indexOf(nodeTmp);
                if (index == -1){
                    node.addNode(nodeTmp);
                    nodes = nodeTmp.getNodes();
                    node = nodeTmp;
                }else{
                    node = nodes.get(index);
                    nodes = node.getNodes();
                }
            }else{
                node.addNode(nodeTmp);
                nodes = nodeTmp.getNodes();
                node = nodeTmp;
            }
        }
        if (node.getDirs() != null && !node.getDirs().isEmpty()){
            if (!node.getDirs().contains(dir)){
                node.addDir(dir);
            }
        }else {
            node.addDir(dir);
        }
    }

    public List<String> searchWord(String word){
        Node node = searchNodeWord(word);
        return node != null ? node.getDirs() : null;
    }

    public void removeWord(String word){
        Node node = searchNodeWord(word);
        node.getDirs().clear();
    }
    private Node searchNodeWord(String word){
        Node node = root;
        Node nodeTmp = null;
        List<Node> nodes = node.getNodes();
        for (char c : word.toCharArray()){
            nodeTmp = new Node(c);
            if (nodes != null && !nodes.isEmpty()){
                int index = nodes.indexOf(nodeTmp);
                if (index == -1){
                    return null;
                }else{
                    node = nodes.get(index);
                    nodes = node.getNodes();
                }
            }else{
                return null;
            }
        }
        return node;
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

}
