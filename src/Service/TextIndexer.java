package Service;

import Model.PatTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TextIndexer {

    private PatTree patriciaTree;
    private File dir;

    public TextIndexer(String dir) {
        this.patriciaTree = new PatTree();
        this.dir = new File(dir);
        index();
    }

    private void index() {
        File [] files = this.dir.listFiles();
        if (files != null && files.length > 0)
            Arrays.stream(files).forEach(f ->{
                try {
                    String text = extractTextFromFile(f.getAbsolutePath());
                    patriciaTree.addText(text, f.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
    }

    public void printTree(){
        System.out.println(patriciaTree.toString());
    }

    public String extractTextFromFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n"); // Agregamos una nueva línea ya que readLine() elimina los caracteres de nueva línea
            }
        }
        return sb.toString();
    }

}
