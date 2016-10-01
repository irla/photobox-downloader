package pl.pawelirla.pbd.web

import com.gargoylesoftware.htmlunit.WebClient
import spock.lang.Specification

/**
 * Created by irla on 2016-09-23.
 */
class AlbumsListerTest extends Specification {
    def loggingIn = new LoggingIn()
    def albumsLister = new AlbumsLister()

    def setup() {
        WebClient client = new WebClient()
        client.options.javaScriptEnabled = false

        loggingIn.client = client;
        loggingIn.loginUrl = "https://www.photobox.co.uk"

        albumsLister.client = client
        albumsLister.albumsUrl = "https://www.photobox.co.uk/my/albums"

    }

    def "List Test"() {
        given:
        loggingIn.logIn("valid.email", "valid.password")

        when:
        def list = albumsLister.list()

        then:
        list[0].name == 'kaski'
        list[0].id == 727334291
    }
}
