package pl.pawelirla.pbd.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import pl.pawelirla.pbd.web.containers.IdName;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by irla on 2016-09-23.
 */
public class AlbumsLister {

    @Inject
    WebClient client;

    @Inject @Named("url.albums")
    String albumsUrl;

    @Inject
    public AlbumsLister() {}

    public List<IdName> list() throws IOException {
        HtmlPage page = client.getPage(albumsUrl);
        System.out.println(page.asText());
        List<HtmlAnchor> byXPath = (List<HtmlAnchor>) page.getByXPath("//a[@class = 'pbx_object_title'][starts-with(@href, '/my/album?album_id')]");
        return byXPath.stream().map(a -> new IdName(a)).collect(Collectors.toList());
    }
}
