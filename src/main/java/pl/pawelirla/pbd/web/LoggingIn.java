package pl.pawelirla.pbd.web;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sun.webkit.WebPage;
import dagger.multibindings.ElementsIntoSet;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

/**
 * Created by irla on 2016-09-23.
 */
public class LoggingIn {

    @Inject
    WebClient client;

    @Inject @Named("url.login")
    String loginUrl;

    @Inject
    public LoggingIn() {}

    public boolean logIn(String username, String password) throws IOException, InterruptedException {
        HtmlPage page = client.getPage(loginUrl);
        HtmlInput usernameInput = (HtmlInput) page.getElementById("j_username");
        usernameInput.type(username);
        HtmlInput passwordInput = (HtmlInput) page.getElementById("j_password");
        passwordInput.type(password);
        HtmlButton submit = (HtmlButton) page.getElementById("submit");
        page = submit.click();
        return !page.asText().contains("Incorrect email or password. Please try again.");
    }
}
