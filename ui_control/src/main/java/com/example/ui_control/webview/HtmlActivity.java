package com.example.ui_control.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.ui_control.R;

public class HtmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        WebView show = findViewById(R.id.show);
        StringBuilder sb = new StringBuilder();
//        sb.append("<html>");
//        sb.append("<head>");
//        sb.append("<title>欢迎您</title>");
//        sb.append("</head>");
//        sb.append("<h2>欢迎您访问<a href=\"http://www.baidu.com\">" + "百度</a><h2>");
//        sb.append("</body>");
//        sb.append("</html>");

        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                " <h2>欢迎来到<a href=\"http://www.baidu.com\">百度联盟</a></h2>\n" +
                "</body>\n" +
                "</html>");
        show.loadData(sb.toString(), "text/html", "utf-8");


    }
}