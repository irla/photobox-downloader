package pl.pawelirla.pbd;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import pl.pawelirla.pbd.web.AlbumsLister;
import pl.pawelirla.pbd.web.PhotoTaker;
import pl.pawelirla.pbd.web.PhotosLister;
import pl.pawelirla.pbd.web.containers.IdName;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by irla on 2016-09-23.
 */
public class PicturesSaver {

    @Inject
    AlbumsLister albumsLister;

    @Inject
    PhotosLister photosLister;

    @Inject
    PhotoTaker photoTaker;

    @Inject
    PrintStream printer;

    @Inject
    public PicturesSaver() {}

    public void downloadAndSaveAll(File directory) throws IOException {
        List<IdName> list = albumsLister.list();
        directory = new File(directory.getAbsolutePath() + "/albums");
        directory.mkdir();
        for (IdName idName : list) {
            createAlbumAndSaveFiles(idName, directory);
        }
    }

    private void createAlbumAndSaveFiles(IdName idName, File directory) throws IOException {
        printer.println(MessageFormat.format("Creating a directory {0}", idName.getName()));
        Set<Long> list = photosLister.list(idName.getId());
        if (! list.isEmpty()) {
            File albumDir = new File(directory.getAbsolutePath() + "/" + idName.getName());
            albumDir.mkdir();
            int i = 1;
            for (Long photoId : list) {
                Optional<HtmlImage> image = photoTaker.image(photoId);
                if (image.isPresent()) {
                    printer.println(MessageFormat.format("Saving image {0,number,#}, {1} out of {2}", photoId, i++, list.size()));
                    File imageFile = new File(albumDir.getAbsolutePath() + "/" + photoId + ".jpg");
                    image.get().saveAs(imageFile);
                }
            }
        }


    }
}
