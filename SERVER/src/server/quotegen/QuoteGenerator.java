package server.quotegen;

import java.util.ArrayList;
import java.util.Random;

public class QuoteGenerator {
    public static String returnQuote() {
        String returnQuote=null;
        long numQuote = QuoteLoad.load().stream().count();
        long m = new Random().nextLong(numQuote);
        int count=0;
        for (String s : QuoteLoad.load()) {
            if(count==m) {
                returnQuote=s;
                break;
            } else {
                count++;
            }
        }
        return returnQuote;
    }
}
