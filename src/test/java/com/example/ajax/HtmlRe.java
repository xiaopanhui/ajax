package com.example.ajax;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlRe {
    public static void analysisDrug(String html) {
        Document doc = Jsoup.parse(html);
        Elements s = doc.getElementsByClass("css_doc_content_title");
        Element s1 = doc.getElementsByClass("css_doc_content_doctitle").get(0);
        System.out.println(s1.text());
        for (int i = 0; i < s.size(); i++) {
            Element s2 = doc.getElementsByClass("css_doc_content_title").get(i);
            Element s3 = doc.getElementsByClass("css_doc_content_text").get(i);
            System.out.println(s2.text());
            System.out.println(s3.text());
        }
    }
public static void main(String[] args) {
//    HtmlParser htmlParser=new HtmlParser();
  String a = "<DIV class=css_top_padding_div>"
                +"<DIV class=css_doc_content_doctitle>注射用甲硫氨酸维B<SUB>1</SUB>说明书</DIV><A name=content_item_2></A>"
                +"<DIV class=css_doc_content_title>【说明书修订日期】</DIV>"
                +"<DIV class=css_doc_content_text>核准日期：2007年04月13日<BR>修改日期：2007年08月10日</DIV>";

    String url = "http://www.baidu.com";
//  String b=  HtmlParser.readHtml(url);
     analysisDrug(a);
  }

}