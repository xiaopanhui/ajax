package com.example.ajax.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Jsoup解析html标签时类似于JQuery的一些符号
 *
 * @author chixh
 *
 */
@Service
public class HtmlParser {
    protected List<List<String>> data = new LinkedList<List<String>>();

    /**
     * 获取value值
     *
     * @param e
     * @return
     */
    public static String getValue(Element e) {
        return e.attr("value");
    }

    /**
     * 获取
     * <tr>
     * 和
     * </tr>
     * 之间的文本
     *
     * @param e
     * @return
     */
    public static String getText(Element e) {
        return e.text();
    }

    /**
     * 识别属性id的标签,一般一个html页面id唯一
     *
     * @param body
     * @param id
     * @return
     */
    public static Element getID(String body, String id) {
        Document doc = Jsoup.parse(body);
        // 所有#id的标签
        Elements elements = doc.select("#" + id);
        // 返回第一个
        return elements.first();
    }

    /**
     * 识别属性class的标签
     *
     * @param body
     * @return
     */
    public static Elements getClassTag(String body, String classTag) {
        Document doc = Jsoup.parse(body);
        // 所有#id的标签
        return doc.select("." + classTag);
    }

    /**
     * 获取tr标签元素组
     *
     * @param e
     * @return
     */
    public static Elements getTR(Element e) {
        return e.getElementsByTag("tr");
    }

    /**
     * 获取td标签元素组
     *
     * @param e
     * @return
     */
    public static Elements getTD(Element e) {
        return e.getElementsByTag("td");
    }
    /**
     * 获取表元组
     * @param table
     * @return
     */
    public static List<List<String>> getTables(Element table){
        List<List<String>> data = new ArrayList<>();

        for (Element etr : table.select("tr")) {
            List<String> list = new ArrayList<>();
            for (Element etd : etr.select("td")) {
                String temp = etd.text();
                //增加一行中的一列
                list.add(temp);
            }
            //增加一行
            data.add(list);
        }
        return data;
    }
    /**
     * 读html文件
     * @param fileName
     * @return
     */
    public static String readHtml(String fileName){
        FileInputStream fis = null;
        StringBuffer sb = new StringBuffer();
        try {
            fis = new FileInputStream(fileName);
            byte[] bytes = new byte[1024];
            while (-1 != fis.read(bytes)) {
                sb.append(new String(bytes));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sb.toString();
    }
    public  static String paseHtml(String str ) {
//        String  str="http://www.baidu.com";
        File file = null;

        try {
            URL url = new URL( str);
            InputStream in = url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String detail="";
            while ((detail = bufr.readLine()) != null) {
                str= sb.append(detail).toString();
            }
             file = new File("./temporary.html");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(str.getBytes("UTF-8"));

            bufr.close();
            isr.close();
            in.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(file);

    }

    public  JSONArray getJOnseParse ( String url) {
        String filename = paseHtml(url);
        Document doc2= Jsoup.parse(readHtml( filename));
//        Document doc2 = Jsoup.parse(readHtml("./rtnerror.html"));
//        Elements tables = doc2.select("table");
        Element table = doc2.select("table").get(25);
        Elements docs = doc2.select("table").get(25).select("th");
        List<List<String>> list = getTables(table);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("respCode","200");
//        jsonObject1.put("respDesc","调用成功!");
        JSONArray jsonArray = new JSONArray();
        for (List<String> list2 : list) {
            if(list2.size()>1&&!list2.get(0).equals(" ")){//判断是否有数据
                JSONObject jsonObject2 = new JSONObject();
                for (int i=0;i<docs.size();i++){
                    jsonObject2.put(docs.get(i).text(),list2.get(i));
                }

                jsonArray.add(jsonObject2);

            }

        }

       return jsonArray;

    }


}
