package server.quotegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;


public class QuoteLoad {

    private static Path path;
    private static String line;

    public static ArrayList<String> load() {
        ArrayList<String> arrQuote = new ArrayList<>();
        path = Paths.get("QuoteBase.data");
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                arrQuote.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrQuote;
    }
}
