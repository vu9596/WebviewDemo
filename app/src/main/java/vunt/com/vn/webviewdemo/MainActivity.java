package vunt.com.vn.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgBack;
    private ImageView imgNext;
    private ImageView imgReload;
    private EditText edtURL;
    private Button btnTim;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        webView.setWebViewClient(new WebViewClient());//khi click trong trang web sẽ không load ra khỏi app mà vẫn ở trong app
        imgBack.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgReload.setOnClickListener(this);
        btnTim.setOnClickListener(this);

        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);//có thể thu phóng màn hình
        settings.setDisplayZoomControls(false); //xóa cái nút thu phóng
        settings.setJavaScriptEnabled(true); //dùng đc các chức năng của trang web và xem đc video trên youtube
    }

    private void initViews() {
        imgBack = (ImageView) this.findViewById(R.id.imgBack);
        imgNext = (ImageView) this.findViewById(R.id.imgNext);
        imgReload = (ImageView) this.findViewById(R.id.imgReload);
        edtURL = (EditText) this.findViewById(R.id.edtURL);
        btnTim = (Button) this.findViewById(R.id.btnTim);
        webView = (WebView) this.findViewById(R.id.webview1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTim:
                String url = edtURL.getText().toString().trim();
                webView.loadUrl("http://" + url);
                edtURL.setText(webView.getUrl());
                break;
            case R.id.imgBack:
                if(webView.canGoBack()) {
                    webView.goBack();
                    edtURL.setText(webView.getUrl());
                    Toast.makeText(this, "Trang trước", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Can not go back!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgNext:
                if(webView.canGoForward()) {
                    webView.goForward();
                    edtURL.setText(webView.getUrl());
                    Toast.makeText(this, "Trang sau", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Can not go forward!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgReload:
                webView.reload();
                edtURL.setText(webView.getUrl());
                break;
        }
    }
}
