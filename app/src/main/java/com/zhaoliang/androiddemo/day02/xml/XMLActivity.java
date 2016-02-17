package com.zhaoliang.androiddemo.day02.xml;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day02.xml.domain.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLActivity extends Activity implements View.OnClickListener {

    private Button btn_writexml;
    private Button btn_readxml;

    private List<Person> persons;
    private String path;

    private ListView person_xml_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        path = getFilesDir() + File.separator + "persons.xml";

        findView();

        setOnclickListener();

        createData();
    }

    /**
     * 创建数据
     */
    private void createData() {
        persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            persons.add(new Person("张三" + i, 18 + i, "北京" + i));
        }
    }

    /**
     * 设置点击事件
     */
    private void setOnclickListener() {
        btn_writexml.setOnClickListener(this);
        btn_readxml.setOnClickListener(this);
    }

    /**
     * 查找控件
     */
    private void findView() {
        btn_writexml = (Button) findViewById(R.id.btn_writexml);
        btn_readxml = (Button) findViewById(R.id.btn_readxml);
        person_xml_list = (ListView) findViewById(R.id.person_xml_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_writexml:
                try {
                    writeXML();
                    Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_readxml:
                try {
                    List<Person> personList = readXML();
                    person_xml_list.setAdapter(new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, personList));
                    Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "读取失败", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    /**
     * 读XML
     */
    private List<Person> readXML() throws Exception {
        List<Person> personList = null;
        Person person = null;

        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(new FileInputStream(path), "utf-8");

        int eventType = xmlPullParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    String name = xmlPullParser.getName();

                    if ("persons".equals(name)) {
                        personList = new ArrayList<>();
                    } else if ("person".equals(name)) {
                        person = new Person();
                    } else if ("name".equals(name)) {
                        person.name = xmlPullParser.nextText();
                    } else if ("age".equals(name)) {
                        person.age = Integer.parseInt(xmlPullParser.nextText());
                    } else if ("address".equals(name)) {
                        person.address = xmlPullParser.nextText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("person".equals(xmlPullParser.getName())) {
                        personList.add(person);
                    }
                    break;
            }

            eventType = xmlPullParser.next();
        }

        return personList;
    }

    /**
     * 写XML
     */
    private void writeXML() throws Exception {
        XmlSerializer xmlSerializer = Xml.newSerializer();
        xmlSerializer.setOutput(new FileOutputStream(path), "utf-8");
        xmlSerializer.startDocument("utf-8", true);

        xmlSerializer.startTag(null, "persons");

        for (Person person : persons) {
            xmlSerializer.startTag(null, "person");

            xmlSerializer.startTag(null, "name");
            xmlSerializer.text(person.name);
            xmlSerializer.endTag(null, "name");

            xmlSerializer.startTag(null, "age");
            xmlSerializer.text(String.valueOf(person.age));
            xmlSerializer.endTag(null, "age");

            xmlSerializer.startTag(null, "address");
            xmlSerializer.text(person.address);
            xmlSerializer.endTag(null, "address");

            xmlSerializer.endTag(null, "person");
        }

        xmlSerializer.endTag(null, "persons");

        xmlSerializer.endDocument();
    }
}
