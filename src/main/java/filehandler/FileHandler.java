package filehandler;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {
    /**
     * Liest ein File von Ressource ein und liefert es als String zurück
     * @param fileName Name des Files was eingelesen wird
     * @return Inhalt des Files
     * @throws FileNotFoundException Tritt auf wenn das File in ressource nicht exestiert
     */
    public static String readFileFromRessourceAsString(@NotNull String fileName) throws FileNotFoundException{
        StringBuilder builder = new StringBuilder();
        String str;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(FileHandler.readFileFromRessourceAsInputStream(fileName), StandardCharsets.UTF_8));) {
            while((str = br.readLine()) != null) {
                builder.append(str);
            }
        }
        catch(IOException e){
            throw new FileNotFoundException("File in ressource not found");
        }
        return builder.toString();
    }

    /**
     * Liest ein File von Ressource ein und liefert es als InputStream zurück
     * @param fileName Name des Files was eingelesen wird
     * @return Inhalt des Files als InputStream
     * @throws FileNotFoundException Tritt auf wenn das File in ressource nicht exestiert
     */
    public static InputStream readFileFromRessourceAsInputStream(@NotNull String fileName) throws FileNotFoundException{
        InputStream is;
        is = FileHandler.class.getClassLoader().getResourceAsStream(fileName);
        if(is == null){
            throw new FileNotFoundException("File in resource not found");
        }
        return is;
    }
}
