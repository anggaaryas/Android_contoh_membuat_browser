package indonesia.angasalabs.browsertest;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Angarsa Labs...!
 * Created by Angga on 13/01/2019.
 */
public class WebClient extends WebViewClient {
    private Activity activity;

    public WebClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
