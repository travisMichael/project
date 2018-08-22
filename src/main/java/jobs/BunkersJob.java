package jobs;

import org.jsoup.nodes.Document;


public class BunkersJob extends BaseJob {
    private final static String resourceUrl = "http://bunkersmusic.com/calendar";
    Document document;

    BunkersJob() throws Exception {
        document = super.getDocumentForResource(resourceUrl);
    }
    public static void main(String[] args) throws Exception {

    }
}
