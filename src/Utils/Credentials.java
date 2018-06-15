package Utils;

import org.javatuples.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Credentials {

    public static Pair getCredentials() throws IOException {
        String user = "", pass = "";
        File credentials = new File("credentials.txt");
        BufferedReader credReader = new BufferedReader(new FileReader(credentials));
        String line;
        String[] parts;
        while ((line = credReader.readLine()) != null) {
            parts = line.split("=");
            if (parts[0].equals("USERNAME")) {
                user = parts[1];
            } else if (parts[0].equals("PASSWORD")) {
                pass = parts[1];
            }
        }
        Pair<String, String> creds = new Pair<String, String>(user, pass);
        return creds;
    }
}