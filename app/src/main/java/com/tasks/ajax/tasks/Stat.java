package com.tasks.ajax.tasks;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Stat extends AppCompatActivity {

    List<String> groups;
    List<String> datas;
    int groupsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        final WebView webView = (WebView)findViewById(R.id.hello);
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        groups = new ArrayList<>();
        datas = new ArrayList<>();
        prepareData();
        String names = "[";
        String darsad = "[";
        for(int i = 0;i< groupsCount ; i++){
            if(i != 0) {
                names += ',';
                darsad += ',';
            }
            darsad += datas.get(i);
            names += "'";
            names += groups.get(i);
            names += "'";
        }
        names += "]";
        darsad += "]";
        String html = "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                " <head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <title>Solid Gauge</title>\n" +
                "  <link href=\"https://playground.anychart.com/gallery/Circular_Gauges/Solid_Gauge/iframe\" rel=\"canonical\">\n" +
                "  <meta content=\"Acme Corp,Circular Gauge,Gauges\" name=\"keywords\">\n" +
                "  <meta content=\"AnyChart - JavaScript Charts designed to be embedded and integrated\" name=\"description\">\n" +
                "  <!--[if lt IE 9]>\n" +
                "<script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" +
                "<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" +
                "<![endif]-->\n" +
                "  <link href=\"file:///android_asset/anychart-ui.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <link href=\"file:///android_asset/anychart-font.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <style>html, body, #container {\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "}</style>\n" +
                " </head>\n" +
                " <body>\n" +
                "  <div id=\"container\"></div>\n" +
                "  <script src=\"file:///android_asset/anychart-base.min.js\"></script>\n" +
                "  <script src=\"file:///android_asset/anychart-ui.min.js\"></script>\n" +
                "  <script src=\"file:///android_asset/anychart-exports.min.js\"></script>\n" +
                "  <script src=\"file:///android_asset/anychart-circular-gauge.min.js\"></script>\n" +
                "  <script type=\"text/javascript\">var names = " + names + ";\n" +
                "var data = " + darsad + ";\n" +
                "var dataSet = anychart.data.set(data);\n" +
                "var palette = anychart.palettes.distinctColors().items(['#00bfa5','#64b5f6', '#1976d2', '#ef6c00', " +
                "'#ffd54f', '#455a64', '#96a6a6', '#dd2c00', '#00838f', '#ffa000' ,'#fbcaaa' , '#001abc' ,'#aabc10'" +
                ", '#125ab4', '#ab0eee']);\n" +
                "\n" +
                "var makeBarWithBar = function (gauge, radius, i, width, without_stroke) {\n" +
                "    var stroke = '1 #e5e4e4';\n" +
                "    var texo = names[i] + ' , ' + data[i] + ' % ';\n"+
                "    if (without_stroke) {\n" +
                "        stroke = null;\n" +
                "        gauge.label(i)\n" +
                "                .text(texo)// color: #7c868e\n" +
                "                .useHtml(true);\n" +
                "        gauge.label(i)\n" +
                "                .hAlign('center')\n" +
                "                .vAlign('middle')\n" +
                "                .anchor('right-center')\n" +
                "                .padding(0, 10)\n" +
                "                .height(width / 2 + '%')\n" +
                "                .offsetY(radius + '%')\n" +
                "                .offsetX(0);\n" +
                "    }\n" +
                "\n" +
                "    gauge.bar(i).dataIndex(i)\n" +
                "            .radius(radius)\n" +
                "            .width(width)\n" +
                "            .fill(palette.itemAt(i))\n" +
                "            .stroke(null)\n" +
                "            .zIndex(5);\n" +
                "    gauge.bar(i + 100).dataIndex(6)\n" +
                "            .radius(radius)\n" +
                "            .width(width)\n" +
                "            .fill('#F5F4F4')\n" +
                "            .stroke(stroke)\n" +
                "            .zIndex(4);\n" +
                "\n" +
                "    return gauge.bar(i)\n" +
                "};\n" +
                "\n" +
                "anychart.onDocumentReady(function () {\n" +
                "    var gauge = anychart.gauges.circular();\n" +
                "    gauge.data(dataSet);\n" +
                "    gauge.fill('#fff')\n" +
                "            .stroke(null)\n" +
                "            .padding(0)\n" +
                "            .margin(100)\n" +
                "            .startAngle(0)\n" +
                "            .sweepAngle(270);\n" +
                "\n" +
                "    var axis = gauge.axis().radius(100).width(1).fill(null);\n" +
                "    axis.scale()\n" +
                "            .minimum(0)\n" +
                "            .maximum(100)\n" +
                "            .ticks({interval: 1})\n" +
                "            .minorTicks({interval: 1});\n" +
                "    axis.labels().enabled(false);\n" +
                "    axis.ticks().enabled(false);\n" +
                "    axis.minorTicks().enabled(false);\n";
        String make = "";
        int start = 100;
        int index = start;
        int width = 10;
     //   width = (100 - groupsCount) / groupsCount -;
        width = 15;
        for(int i = 0;i<groupsCount;i++){
            make += "makeBarWithBar(gauge," + index + "," + i + "," + width + ",true);\n";
            index -= 1;
            index -= width;
        }
     /*           "    makeBarWithBar(gauge, 100, 0, 17, true);\n" +
                "    makeBarWithBar(gauge, 82, 1, 17, true);\n";*/
        String html2 = "\n" +
                "  gauge.margin(50);\n" +
                "    gauge.title().text('<br/><span style=\"color:#929292; font-size: 20px; font-family:Arial;\">Percentage of task done by each group</span>').useHtml(true);\n" +
                "    gauge.title()\n" +
                "            .enabled(true)\n" +
                "            .hAlign('center')\n" +
                "            .padding(0)\n" +
                "            .margin([0, 0, 20, 0]);\n" +
                "\n" +
                "    gauge.container('container');\n" +
                "    gauge.draw();\n" +
                "});</script>\n" +
                " </body>\n" +
                "</html>";
        String html3 = html + make + html2;
        webView.loadDataWithBaseURL("",html3,"text/html","UTF-8",null);

    }

    private void prepareData() {
        Db_helper dbhelper = new Db_helper(this);
        Cursor c1 = dbhelper.selectGroup();
        if(c1.moveToFirst()){
            do{
                groups.add(c1.getString(0));
                groupsCount++;

                Cursor c = dbhelper.selectTaskByGroup(c1.getString(0));
                float ratekol = 0;
                float donerate = 0;
                if(c.moveToFirst()){
                    do{
                        if(c.getString(9).equals("yes")){
                            donerate += Float.parseFloat(c.getString(10));
                        }
                        ratekol += Float.parseFloat(c.getString(10));
                    }while(c.moveToNext());
                }

                float darsad = 0;
                darsad = (donerate / ratekol) * 100;
                darsad =(float) Math.floor(darsad);
                if(ratekol == 0){
                    darsad = 0;
                }
                datas.add(String.valueOf(darsad));
            }while(c1.moveToNext());
        }
    }
}
/*
        String html = "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                " <head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <title>Solid Gauge</title>\n" +
                "  <link href=\"https://playground.anychart.com/gallery/Circular_Gauges/Solid_Gauge/iframe\" rel=\"canonical\">\n" +
                "  <meta content=\"Acme Corp,Circular Gauge,Gauges\" name=\"keywords\">\n" +
                "  <meta content=\"AnyChart - JavaScript Charts designed to be embedded and integrated\" name=\"description\">\n" +
                "  <!--[if lt IE 9]>\n" +
                "<script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" +
                "<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" +
                "<![endif]-->\n" +
                "  <link href=\"file:///android_asset/anychart-ui.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <link href=\"file:///android_asset/anychart-font.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <style>html, body, #container {\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "}</style>\n" +
                " </head>\n" +
                " <body>\n" +
                "  <div id=\"container\"></div>\n" +
                "  <script src=\"file:///android_asset/anychart-base.min.js\"></script>\n" +
                "  <script src=\"file:///android_asset/anychart-ui.min.js\"></script>\n" +
                "  <script src=\"file:///android_asset/anychart-exports.min.js\"></script>\n" +
                "  <script src=\"file:///android_asset/anychart-circular-gauge.min.js\"></script>\n" +
                "  <script type=\"text/javascript\">var names = ['Temazepam', 'Guaifenesin', 'Salicylic Acid', 'Fluoride', 'Zinc Oxide', 'Acetaminophen','Potasium'];\n" +
                "var data = [23, 34, 67, 93, 56, 49, 15, 100];\n" +
                "var dataSet = anychart.data.set(data);\n" +
                "var palette = anychart.palettes.distinctColors().items(['#64b5f6', '#1976d2', '#ef6c00', '#ffd54f', '#455a64', '#96a6a6', '#dd2c00', '#00838f', '#00bfa5', '#ffa000']);\n" +
                "\n" +
                "var makeBarWithBar = function (gauge, radius, i, width, without_stroke) {\n" +
                "    var stroke = '1 #e5e4e4';\n" +
                "    if (without_stroke) {\n" +
                "        stroke = null;\n" +
                "        gauge.label(i)\n" +
                "                .text(names[i] + ', <span style=\"\">' + data[i] + '%</span>')// color: #7c868e\n" +
                "                .useHtml(true);\n" +
                "        gauge.label(i)\n" +
                "                .hAlign('center')\n" +
                "                .vAlign('middle')\n" +
                "                .anchor('right-center')\n" +
                "                .padding(0, 10)\n" +
                "                .height(width / 2 + '%')\n" +
                "                .offsetY(radius + '%')\n" +
                "                .offsetX(0);\n" +
                "    }\n" +
                "\n" +
                "    gauge.bar(i).dataIndex(i)\n" +
                "            .radius(radius)\n" +
                "            .width(width)\n" +
                "            .fill(palette.itemAt(i))\n" +
                "            .stroke(null)\n" +
                "            .zIndex(5);\n" +
                "    gauge.bar(i + 100).dataIndex(6)\n" +
                "            .radius(radius)\n" +
                "            .width(width)\n" +
                "            .fill('#F5F4F4')\n" +
                "            .stroke(stroke)\n" +
                "            .zIndex(4);\n" +
                "\n" +
                "    return gauge.bar(i)\n" +
                "};\n" +
                "\n" +
                "anychart.onDocumentReady(function () {\n" +
                "    var gauge = anychart.gauges.circular();\n" +
                "    gauge.data(dataSet);\n" +
                "    gauge.fill('#fff')\n" +
                "            .stroke(null)\n" +
                "            .padding(0)\n" +
                "            .margin(100)\n" +
                "            .startAngle(0)\n" +
                "            .sweepAngle(270);\n" +
                "\n" +
                "    var axis = gauge.axis().radius(100).width(1).fill(null);\n" +
                "    axis.scale()\n" +
                "            .minimum(0)\n" +
                "            .maximum(100)\n" +
                "            .ticks({interval: 1})\n" +
                "            .minorTicks({interval: 1});\n" +
                "    axis.labels().enabled(false);\n" +
                "    axis.ticks().enabled(false);\n" +
                "    axis.minorTicks().enabled(false);\n" +
                "    makeBarWithBar(gauge, 100, 0, 17, true);\n" +
                "    makeBarWithBar(gauge, 82, 1, 17, true);\n" +
                "    makeBarWithBar(gauge, 64, 2, 17, true);\n" +
                "    makeBarWithBar(gauge, 46, 3, 17, true);\n" +
                "    makeBarWithBar(gauge, 28, 4, 17, true);\n" +
                "\n" +
                "  makeBarWithBar(gauge,10,5,17,true);  makeBarWithBar(gauge,10,6,17,true);  gauge.margin(50);\n" +
                "    gauge.title().text('درصد انجام کار در هر دسته بندی' +\n" +
                "            ).useHtml(true);\n" +
                "    gauge.title()\n" +
                "            .enabled(true)\n" +
                "            .hAlign('center')\n" +
                "            .padding(0)\n" +
                "            .margin([0, 0, 20, 0]);\n" +
                "\n" +
                "    gauge.container('container');\n" +
                "    gauge.draw();\n" +
*/