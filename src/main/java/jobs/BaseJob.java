package jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.net.URL;

public class BaseJob {
    public Document getDocumentForResource(String urlString) throws Exception {
        URL url = new URL(urlString);
        return Jsoup.parse(url, 1000);
    }

    public Element getElementOfInterest(Node node, String sequence) {
        String[] parts = sequence.split(" ");

        for (int i = 0; i < parts.length; i++) {
            node = node.childNodes().get(Integer.parseInt(parts[i]));
        }

        return (Element)node;
    }
}
