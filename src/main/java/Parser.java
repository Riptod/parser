import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
    private ColorUtils colorUtils = new ColorUtils();

    public synchronized void htmlParser(String url) throws IOException {
        int numberOfConnection = 0;
        List<Item> items = new ArrayList<>();
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        numberOfConnection++;
        Elements elements = doc.select("a[data-test-id='ProductTile']");
        for (Element e : elements) {
            String itemURL = e.attr("href");
            Document itemDoc = Jsoup.connect("https://www.aboutyou.de" + itemURL).maxBodySize(0).get();
            numberOfConnection++;
            String productName = itemDoc.select("div[data-test-id='ProductName']").text();
            String brand = e.select("p[data-test-id='BrandName']").text();
            Double price = Double.valueOf(e.select("span[data-test-id$='Price']").text().substring(0, 6)
                    .replaceAll("[a-zA-Z]", "").replaceAll(",", ".").trim());
            Elements colors = e.select("li[data-test-id='ColorBubble']");
            for (Element element : colors) {
                String color = element.select("li[data-test-id='ColorBubble']").attr("color");
                Item item = new Item();
                item.setProductName(productName);
                item.setBrand(brand);
                item.setColor(colorUtils
                        .getColorNameFromHex(Long.parseLong(color.substring(1), 16)));
                item.setPrice(price);
                items.add(item);
            }
        }
        for (Item item :items) {
            System.out.println(item.toString());
        }
        System.out.println("Number of Connection - " + numberOfConnection);
        System.out.println("Number of Items - " + items.size());
    }
}
