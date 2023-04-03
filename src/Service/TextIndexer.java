package Service;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextIndexer {

    private Trie<String, Integer> patriciaTree;

    public TextIndexer(String text) {
        this.patriciaTree = new PatriciaTrie<>();
        indexText(text);
    }

    private void indexText(String text) {
        String[] words = preprocessText(text);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            patriciaTree.put(word, i);
        }
    }

    public boolean searchWord(String word) {
        return patriciaTree.containsKey(word);
    }

    public void removeWord(String word) {
        patriciaTree.remove(word);
    }

    private String[] preprocessText(String text) {
        // Expresión regular para eliminar caracteres vacíos como coma, punto, etc.
        String cleanedText = text.replaceAll("[^\\w\\s]+", " ");

        // Eliminar palabras vacías
        String[] words = cleanedText.split("\\s+");

        return words;
    }

}
