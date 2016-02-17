package com.zhaoliang.androiddemo.day04.newsclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ListView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day04.newsclient.adapter.NewsAdapter;
import com.zhaoliang.androiddemo.day04.newsclient.domain.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsClientActivity extends Activity {

    private ListView new_list;

    private String path = "http://10.122.2.31:8080/xml/news.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_client);

        new_list = (ListView) findViewById(R.id.new_list);

        new DownloadDataTask().execute(path);
    }

    class DownloadDataTask extends AsyncTask<String, Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... params) {
            String newsUrl = params[0];
            HttpURLConnection connection = null;
            try {
                URL url = new URL(newsUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                if (connection.getResponseCode() == 200) {
                    return parseInputStreamToList(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 解析数据
         *
         * @param inputStream
         * @return
         * @throws XmlPullParserException
         */
        private List<News> parseInputStreamToList(InputStream inputStream) throws XmlPullParserException, IOException {
            List<News> newses = null;
            News news = null;
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(inputStream, "utf-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String name = xmlPullParser.getName();
                        if ("newslist".equals(name)) {
                            newses = new ArrayList<>();
                        } else if ("news".equals(name)) {
                            news = new News();
                        } else if ("title".equals(name)) {
                            news.title = xmlPullParser.nextText();
                        } else if ("detail".equals(name)) {
                            news.detail = xmlPullParser.nextText();
                        } else if ("comment".equals(name)) {
                            news.comment = xmlPullParser.nextText();
                        } else if ("image".equals(name)) {
                            news.imageUrl = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("news".equals(xmlPullParser.getName())) {
                            newses.add(news);
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
            return newses;
        }

        @Override
        protected void onPostExecute(List<News> newses) {
            new_list.setAdapter(new NewsAdapter(NewsClientActivity.this, newses));
        }
    }
}
