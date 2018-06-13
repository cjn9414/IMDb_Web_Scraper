package Utils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Scraper {
    BufferedReader reader;
    private static Document site;

    public Scraper(InputStreamReader website) {
        this.reader = new BufferedReader(website);
    }

    /*
    This function will compile all necessary data from a given webpage. This data we are collecting consists
    of any given feature we feel will be advantageous to analyze.
     */

    public void scrapeData() {
        try {

            Elements tags = site.select("tr");
            for (int index = 0; index < tags.size(); index++) {
                System.out.println(tags.get(index).text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    This function will obtain the next page we will scrape. It will do so by finding a link to
    a new page, from the page it is currently on.
     */

    public String getNextPage() {
        String newSite = "";
        try {
            site = Jsoup.connect(reader.readLine()).timeout(0).get();
            Elements newPeople = site.getElementsByClass("name actor-name");
            for (Element name : newPeople) {
                System.out.println(name.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newSite;
    }

    /*
    This function performs the login which is required upon connecting to the website.
     */

    public void executeLogin() throws IOException {

        final String user_agent = "Mozilla";
        String loginFormURL = "https://www.linkedin.com/";
        String loginActionURL = "https://www.linkedin.com/uas/login-submit";
        String username = "Enter email"; // enter your own email
        String password = "Enter password"; // enter your own password

        HashMap<String, String> cookies = new HashMap<>();
        HashMap<String, String> formData = new HashMap<>();
        Connection.Response loginForm = Jsoup.connect(loginFormURL)
                .method(Connection.Method.GET)
                .userAgent(user_agent)
                .execute();

        Document loginDoc = loginForm.parse();
        cookies.putAll(loginForm.cookies());
        String login_csrf_param = loginDoc.select("#loginCsrfParam-login")
                .first()
                .attr("value");


        formData.put("session_key", username);
        formData.put("session_password", password);
        formData.put("IsJsEnabled", "false");
        formData.put("loginCsrfParam", login_csrf_param);

        Connection.Response homePage = Jsoup.connect(loginActionURL)
                .cookies(cookies)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent(user_agent)
                .execute();

    }

}
