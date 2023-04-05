package Service;

public abstract class UserfulText {

    public static String[] preprocessText(String text) {
        // Expresión regular para eliminar caracteres vacíos como coma, punto, etc.
        String cleanedText = text.replaceAll("[^\\w\\sáéíóúüñ]+", " ");

        // Eliminar palabras vacías
        String[] words = cleanedText.toLowerCase().trim().split("\\s+");

        return words;
    }
}
