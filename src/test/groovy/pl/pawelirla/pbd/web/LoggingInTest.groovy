package pl.pawelirla.pbd.web

import com.gargoylesoftware.htmlunit.ElementNotFoundException
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import spock.lang.Specification

/**
 * Created by irla on 2016-09-23.
 */
class LoggingInTest extends Specification {

    def loggingIn = new LoggingIn()

    def setup() {
        WebClient client = new WebClient()
        client.options.javaScriptEnabled = false

        loggingIn.client = client;
        loggingIn.loginUrl = "https://www.photobox.co.uk"

    }

    def "LogIn Test"() {
        when:
        HtmlPage page = loggingIn.logIn("valid.email", "valid.password")
        page.getAnchorByText('My Photobox')

        then:
        notThrown(ElementNotFoundException)
    }
}
