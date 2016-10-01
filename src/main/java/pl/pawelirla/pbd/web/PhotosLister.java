package pl.pawelirla.pbd.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import pl.pawelirla.pbd.web.containers.IdName;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by irla on 2016-09-23.
 */
public class PhotosLister {

    @Inject
    WebClient client;

    @Inject @Named("url.album")
    String albumUrl;

    @Inject
    public PhotosLister() {}

    public Set<Long> list(long albumId) throws IOException {
        Set<Long> ids = new HashSet<>();
        int size = 0;
        int i = 1;
        ids.addAll(listFromPage(albumId, i++));
        while(ids.size() > size) { //still adding new ids
            size = ids.size();
            ids.addAll(listFromPage(albumId, i++));
        }
        return ids;
    }

    private List<Long> listFromPage(long albumId, int albumPage) throws IOException {
        HtmlPage page = client.getPage(MessageFormat.format("{0}?album_id={1,number,#}&page={2}", albumUrl, albumId, albumPage));
        List<HtmlAnchor> byXPath = (List<HtmlAnchor>) page.getByXPath("//a[starts-with(@href, '/my/photo?album_id=" + albumId + "') and text() = 'View']");
        List<Long> ids = new ArrayList<>(byXPath.size());
        for (HtmlAnchor htmlAnchor : byXPath) {
            String hrefAttribute = htmlAnchor.getHrefAttribute();
            if (hrefAttribute.contains("photo_id=")) {
                ids.add(new Long(hrefAttribute.split("photo_id=")[1]));
            }
        }
        return ids;
    }
}