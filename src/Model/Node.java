package Model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String value;
    private List<String> dirs;
    private List<Node> nodes;

    public Node(String value, String dir) {
        this.value = value;
        addDir(dir);
    }

    public Node(String value) {
        this.value = value;
    }

    public Node(char value) {
        this.value = String.valueOf(value);
    }
    public void addDir(String dir){
        if (dirs != null)
            dirs.add(dir);
        else {
            dirs = new ArrayList<>();
            dirs.add(dir);
        }
    }

    public void addNode(Node node){
        if (nodes != null)
            nodes.add(node);
        else{
            nodes = new ArrayList<>();
            nodes.add(node);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getDirs() {
        return dirs;
    }

    public void setDirs(List<String> dirs) {
        this.dirs = dirs;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean isWord() {
        return dirs != null && !dirs.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return node.getValue().equalsIgnoreCase(this.value);
    }
}
