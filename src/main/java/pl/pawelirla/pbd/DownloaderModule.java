package pl.pawelirla.pbd;

import com.gargoylesoftware.htmlunit.WebClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.io.PrintStream;

/**
 * Created by irla on 2016-09-23.
 */
@Module
public class DownloaderModule {

    @Singleton @Provides public  WebClient webClient() {
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getCookieManager().clearCookies();
        return webClient;
    }

    @Singleton @Provides
    public PrintStream printStream() {
        return System.out;
    }
}
