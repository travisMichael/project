package jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.File;

/**
 * Created by tlatz on 8/6/2018.
 */
public class CityPagesJob {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\city_pages.txt");
        Document document = Jsoup.parse(file, "UTF-8");
        Element element = (Element) document.childNodes().get(1)
                .childNodes().get(2)
                .childNodes().get(3)
                .childNodes().get(3)
                .childNodes().get(5)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(3)
                .childNodes().get(1);

        int i = 0;
        for (Node node : element.childNodes()) {
            if (node instanceof Element) {
                Element e = (Element)node;
                if (e.childNodes() != null && e.childNodes().size() >= 5) {
                    String text = e.childNodes().get(5).childNodes().get(0).toString();
                    String descLink = e.childNodes().get(7).childNodes().get(1).attributes().get("href");
                    // System.out.println(descLink);
                    System.out.println("INSERT INTO EVENT (NAME, DESCRIPTION) VALUES ('"+text+"', 'description');");
                }

            }

        }
        System.out.println();
        // getDescription();
    }

    private static String getDescription() throws Exception {
        String result = null;
        File file = new File("D:\\city_pages_description.txt");
        Document document = Jsoup.parse(file, "UTF-8");

        Element element = (Element) document.childNodes().get(1)
                .childNodes().get(2)
                .childNodes().get(3)
                .childNodes().get(3)
                .childNodes().get(5)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(3)
                .childNodes().get(1)
                .childNodes().get(5)
                .childNodes().get(3);


        return element.childNodes().get(0).toString();
    }
}
