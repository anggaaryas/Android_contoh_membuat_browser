package indonesia.angasalabs.browsertest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText url;
    private Button go;
    private WebView webView;

    private static final int INTERNET_REQUEST_CODE = 669;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.wv_main);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebClient(this));

        url = findViewById(R.id.et_url);

        go = findViewById(R.id.btn_go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aksesURL();
            }
        });
    }

    private void aksesURL(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED) {
            webView.loadUrl("http://" + url.getText().toString());
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, INTERNET_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == INTERNET_REQUEST_CODE){
            aksesURL();
        } else {
            Toast.makeText(getApplicationContext(), "Aplikasi tidak diizinkan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
