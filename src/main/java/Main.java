import java.io.IOException;

public class Main {
    public static final String URL = "https://www.aboutyou.de/maenner/bekleidung";
    public static void main(String[] args) {
        Parser parser = new Parser();

        try {
            parser.htmlParser(URL);
        } catch (IOException e) {
            throw new RuntimeException("Can't parse URL - " + URL);
        }
    }
}
