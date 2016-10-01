package pl.pawelirla.pbd;

import com.gargoylesoftware.htmlunit.WebClient;
import dagger.Module;
import dagger.Provides;
import lombok.Setter;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by irla on 2016-09-23.
 */
@Module
public class Settings {

    @Setter private String urlLogin;
    @Setter private String urlAlbums;
    @Setter private String urlAlbum;
    @Setter private String urlPhoto;

    @Provides @Named("url.login") String getUrlLogin() {
       return urlLogin;
    }
    @Provides @Named("url.albums") String getUrlAlbums() {
        return urlAlbums;
    }
    @Provides @Named("url.album") String getUrlAlbum() {
        return urlAlbum;
    }
    @Provides @Named("url.photo") String getUrlPhoto() {
        return urlPhoto;
    }
}
