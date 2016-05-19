package mysharesdkdemo.weshpe.com.zoomtestcard;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebView;

public class jsBriage extends AppCompatActivity {
WebView webView ;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_briage);
        webView = (WebView) this.findViewById(R.id.wv);
        //kitkat之前 js调用js
        webView.loadUrl("javascript:scriptString");
        //kitkat之后
        webView.evaluateJavascript("", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //value执行结果
            }
        });
    }
}
