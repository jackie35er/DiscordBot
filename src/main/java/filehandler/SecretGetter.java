package filehandler;

import enmus.Secrets;
import java.io.FileNotFoundException;

public class SecretGetter {
    Secrets secret;

    public SecretGetter(Secrets secret){
        this.secret = secret;
    }

    public final String getSecret(){
        if(this.secret == Secrets.TOKEN){
            try {
                return FileHandler.readFileFromRessourceAsString("secret/botToken");
            } catch (FileNotFoundException ignored) {
            }
        }
        return "";// Cant ever be reached
    }
}
