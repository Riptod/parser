import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            Parser parser = new Parser();
            parser.htmlParser("https://www.aboutyou.de/maenner/bekleidung");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
