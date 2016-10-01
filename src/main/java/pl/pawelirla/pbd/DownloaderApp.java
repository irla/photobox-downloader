package pl.pawelirla.pbd;

import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by irla on 2016-09-23.
 */
public class DownloaderApp {

    public static void main(String ... arguments) throws IOException, InterruptedException {
        Settings settings = new Settings();
        settings.setUrlLogin("https://www.photobox.co.uk");
        settings.setUrlAlbums("https://www.photobox.co.uk/my/albums");
        settings.setUrlAlbum("https://www.photobox.co.uk/my/album");
        settings.setUrlPhoto("https://www.photobox.co.uk/my/photo/full");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide Photobox email (and press enter):");
        String email = scanner.nextLine();

        System.out.println("Please provide Photobox email (and press enter):");
        String pass = scanner.nextLine();

        Downloader downloader = DaggerDownloader.builder()
                .settings(settings)
                .downloaderModule(new DownloaderModule())
                .build();
        boolean loggedInd = downloader.loggingIn().logIn(email, pass);
        if (loggedInd) {
            downloader.picturesSaver().downloadAndSaveAll(new File(System.getProperty("user.dir")));
        } else {
            System.err.println("Incorrect email or password");
        }
    }


}
