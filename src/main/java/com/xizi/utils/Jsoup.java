package com.xizi.utils;

import com.xizi.pojo.Content;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class Jsoup {
    public static void main(String[] args) throws Exception {
        new Jsoup().parseJD("vue").forEach(System.out::println);

    }

    //封装
    public  List<Content> parseJD(String keywords) throws Exception {
        //https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keywords;

        Document document = org.jsoup.Jsoup.parse(new URL(url), 30000);

        Element element = document.getElementById("J_goodsList");

        Elements elements = element.getElementsByTag("li");

        List<Content> goodsList = new ArrayList<>();

        //获取元素中的内容  el就是每一个li标签
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
//            System.out.println(img);
//            System.out.println(price);
//            System.out.println(title);

            Content content = new Content();
            content.setTitle(title);
            content.setImg(img);
            content.setPrice(price);
            goodsList.add(content);
        }

        return  goodsList;
    }



}
