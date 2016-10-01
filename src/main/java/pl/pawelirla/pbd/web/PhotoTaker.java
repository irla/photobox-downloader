package pl.pawelirla.pbd.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import pl.pawelirla.pbd.web.containers.IdName;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by irla on 2016-09-23.
 */
public class PhotoTaker {

    @Inject
    WebClient client;

    @Inject @Named("url.photo")
    String photoUrl;

    @Inject
    public PhotoTaker() {}

    public Optional<HtmlImage> image(Long id) throws IOException {
        HtmlPage page = client.getPage(photoUrl + "?photo_id=" + id);
        List<?> xPath = page.getByXPath("//img");
        if (! xPath.isEmpty()) {
            return Optional.of((HtmlImage) xPath.get(0));
        }
        return Optional.empty();
    }
}
