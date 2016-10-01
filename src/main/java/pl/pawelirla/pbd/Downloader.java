package pl.pawelirla.pbd;

import dagger.Component;
import pl.pawelirla.pbd.web.AlbumsLister;
import pl.pawelirla.pbd.web.LoggingIn;
import pl.pawelirla.pbd.web.PhotoTaker;
import pl.pawelirla.pbd.web.PhotosLister;

import javax.inject.Singleton;

/**
 * Created by irla on 2016-09-23.
 */
@Singleton @Component(modules = {Settings.class, DownloaderModule.class})
public interface Downloader {

    LoggingIn loggingIn();
    PicturesSaver picturesSaver();
}
