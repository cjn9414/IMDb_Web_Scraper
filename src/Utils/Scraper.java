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
    private BufferedReader reader;
    private Document site;
    private String username, password;
    public Scraper(InputStreamReader website, String username, String password) {
        this.reader = new BufferedReader(website);
        this.username = username;
        this.password = password;
    }

    /*
    This function will compile all necessary data from a given webpage. This data we are collecting consists
    of any given feature we feel will be advantageous to analyze.
     */

//    TODO Scrape where people went to school and where they live now.
//    TODO Find correlation between degree and location

    public void scrapeData() {
        try {

            Elements tags = site.select("tr");
            for (Element e : tags) {
                System.out.println(e.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    This function will obtain the next page we will scrape. It will do so by finding a link to
    a new page, from the page it is currently on.
     */

    public String getNextPage(Document site) {
        String newURL = "";
        try {
            Elements newPeople = site.getElementsByClass("name actor-name");
            for (Element name : newPeople) {
            System.out.println(name.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newURL;
    }

    /*
    This function performs the login which is required upon connecting to the website.
     */

    public Document executeLogin() throws IOException {

        final String user_agent = "Mozilla";
        String loginFormURL = "https://www.linkedin.com";
        String loginActionURL = "https://www.linkedin.com/uas/login-submit";

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


        formData.put("session_key", this.username);
        formData.put("session_password", this.password);
        formData.put("IsJsEnabled", "false");
        formData.put("loginCsrfParam", login_csrf_param);

        Connection.Response homePage = Jsoup.connect(loginActionURL)
                .cookies(cookies)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent(user_agent)
                .execute();
        return homePage.parse();
    }

}