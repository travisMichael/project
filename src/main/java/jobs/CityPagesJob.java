package jobs;

import app.entity.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class CityPagesJob extends BaseJob {
    private final static String sequence = "1 2 3 3 5 1 1 3 1";
    static Element eventListElement;

    CityPagesJob() throws Exception {
        File file = new File("D:\\job_data\\city_pages.txt");
        Document document = Jsoup.parse(file, "UTF-8");
        eventListElement = super.getElementOfInterest(document, sequence);
    }

    public static void main(String[] args) throws Exception {

        CityPagesJob job = new CityPagesJob();
        job.run();

    }

    private void run() {
        RestTemplate restTemplate = new RestTemplate();

        for (Node node : eventListElement.childNodes()) {
            if (node instanceof Element) {
                Element e = (Element)node;
                if (e.childNodes() != null && e.childNodes().size() >= 5) {
                    String text = e.childNodes().get(5).childNodes().get(0).toString();
                    String descLink = e.childNodes().get(7).childNodes().get(1).attributes().get("href");
                    String date = e.childNodes().get(7).childNodes().get(0).toString().replace("at","").trim();
                    // System.out.println(descLink);
                    Event event = new Event();
                    event.setName(text);
                    event.setDate(date);
                    event.setDescription(descLink);
                    HttpEntity<Event> entity = new HttpEntity(event);
                    try {
                        Object response = restTemplate.postForObject("http://localhost:8080/events", entity, Object.class);
                    } catch (Exception ex){
                        System.out.println("Failed to post event: " + event.getName());
                    }
                    // System.out.println("INSERT INTO EVENT (NAME, DESCRIPTION) VALUES ('"+text+"', 'description');");
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
