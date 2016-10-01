package pl.pawelirla.pbd.web.containers;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.StringTokenizer;

/**
 * Created by irla on 2016-09-23.
 */
@Getter
public class IdName {

    private long id;
    private String name;

    public IdName(HtmlAnchor anchor) {
        String title = anchor.getAttribute("title");
        if (title == null) throw new NullPointerException("Title of anchor can not be null");
        this.name = escape(title);

        String hrefAttribute = anchor.getHrefAttribute();
        if (hrefAttribute == null) throw new NullPointerException("Href of anchor can not be null");
        int index = hrefAttribute.lastIndexOf('=');
        if (index < 0) throw new IllegalArgumentException("Href of anchor should contain '=' char (format '/my/album?album_id=1234567')");
        String idstr = hrefAttribute.substring(index + 1, hrefAttribute.length());
        this.id = new Long(idstr);
    }

    private String escape(String name) {
        return name.replaceAll("\\s|\\&amp;", "_");
    }
}
