package com.example.gamesummary.util;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class HtmlParseUtil {

    private int TIMEOUT = 5000;
    private Document document = null;
    private String url;

    /**
     * urlのhtmlを習得
     *
     * @param  url String
     * @throws IOException
     * */
    public void connectUrll(String url) throws IOException {
        this.url = url;
        this.document = Jsoup.connect(url)
                               .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.63 Safari/537.36")
                               .timeout(TIMEOUT)
                               .get();
    }

    /**
     * urlのhtmlを習得
     * ※オーバーライド
     *
     * @param url String
     * @param timeout int
     * @throws IOException
     * */
    public void connectUrl(String url, int timeout) throws IOException {
        this.url = url;
        this.document = Jsoup.connect(url).timeout(timeout).get();
    }

    /**
     * ユニゾンリーグアンテナ
     * http://unisonleague.warotamaker.com/
     * */
    public ArrayList<Map<String, String>> getNewsParseData() {

        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();
        StringBuilder builder = new StringBuilder();
        Calendar calendar     = Calendar.getInstance();

        Elements targetElements = (Elements)this.document.select("ul.panel-body li.list-group-item");
        ListIterator<Element> targetElementsList = targetElements.listIterator();
        while(targetElementsList.hasNext()) {
            Map<String, String> parseData = new HashMap<String, String>();

            Element targetElement       = targetElementsList.next();
            String times                = targetElement.child(0).text();
            Elements targetLinkElements = targetElement.child(1).getElementsByTag("a");
            String url                  = targetLinkElements.get(0).attr("href");
            String heading              = targetLinkElements.get(0).text();
            String channel              = targetElement.child(2).text();

            builder.append(calendar.get(Calendar.YEAR))
                   .append("年")
                   .append(times)
                   .append(" ")
                   .append(channel);

            parseData.put("url", this.url + url.replace("feed", "feed-click"));
            parseData.put("site", channel);
            parseData.put("heding", heading);
            parseData.put("channel", builder.toString());
            parseDatas.add(parseData);
            builder.delete(0, builder.length());
        }

        return parseDatas;
    }

    /**
     * ユニゾンリーグ攻略まとめ速報
     * http://xn--qck1a0bxgwa7c8cu704dco4a.com/
     * */
    public ArrayList<Map<String, String>> getUnizonMatomeKouryakuParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();
        StringBuilder builder = new StringBuilder();

        Elements headerElem = (Elements)document.select("div.t_h");
        Elements naiyouElem = (Elements)document.select("div.t_b");
        ListIterator<Element> remarkUserlists    = headerElem.listIterator();
        ListIterator<Element> remarkContentlists = naiyouElem.listIterator();
        while(remarkUserlists.hasNext()) {
            Map<String, String> parseData = new HashMap<String, String>();
            Element remarkUserElement = remarkUserlists.next();
            String remarkTitle = getText(remarkUserElement, "span");

            Element remarkContentElements = remarkContentlists.next();
            String remarkContents = getText(remarkContentElements, "");

            parseData.put("remarkHeader", remarkTitle);
            parseData.put("remarkContents", remarkContents);

            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * ユニゾンリーグ攻略まとめNews
     * http://unisonleaguematomenews.blog.jp/
     * */
    public  ArrayList<Map<String, String>> getUnKouryakuMatomeSokuhouParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("div.t_b");
        ListIterator<Element> targetElementsList = targetElements.listIterator();
        while(targetElementsList.hasNext()) {
            Elements childrenList = targetElementsList.next().children();

            Map<String, String> parseData = new HashMap<String, String>();
            String remarkTitle     = getText(childrenList.get(0), "");
            String remarkContents = getText(childrenList.get(2), "");

            parseData.put("remarkHeader", remarkTitle);
            parseData.put("remarkContents", remarkContents);
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * ユニゾンリーグ速報
     * http://unisonleaguematome.blog.jp/
     * */
    public  ArrayList<Map<String, String>> getUnSokuhouParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("div.t_b");
        ListIterator<Element> targetElementsList = targetElements.listIterator();
        while(targetElementsList.hasNext()) {
            Elements childrenList = targetElementsList.next().children();

            Map<String, String> parseData = new HashMap<String, String>();
            String remarkTitle     = getText(childrenList.get(0), "");
            String remarkContents = getText(childrenList.get(2), "");

            parseData.put("remarkHeader", remarkTitle);
            parseData.put("remarkContents", remarkContents);
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * ユニゾンリーグ攻略サイト
     * http://unisonleague-site.blog.jp/
     * */
    public  ArrayList<Map<String, String>> getUnKouryakuParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("div.t_b");
        ListIterator<Element> targetElementsList = targetElements.listIterator();
        while(targetElementsList.hasNext()) {
            Elements childrenList = targetElementsList.next().children();

            Map<String, String> parseData = new HashMap<String, String>();
            String remarkTitle     = getText(childrenList.get(0), "");
            String remarkContents = getText(childrenList.get(1), "");

            parseData.put("remarkHeader", remarkTitle);
            parseData.put("remarkContents", remarkContents);
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * ユニゾンリーグ最速攻略まとめ
     * http://unisonleague.kouryakublog.biz/
     * */
    public  ArrayList<Map<String, String>> getUnSaisokuKouryakuParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements headerElem = (Elements)document.select("div.matome-headline");
        Elements naiyouElem = (Elements)document.select("div.matome-body");
        ListIterator<Element> remarkUserlists    = headerElem.listIterator();
        ListIterator<Element> remarkContentlists = naiyouElem.listIterator();
        while(remarkUserlists.hasNext()) {
            Map<String, String> parseData = new HashMap<String, String>();
            Element remarkUserElement = remarkUserlists.next();
            String remarkTitle = getText(remarkUserElement, "span");
            Element remarkContentElements = remarkContentlists.next();
            String remarkContents = getText(remarkContentElements, "");
            parseData.put("remarkHeader", remarkTitle);
            parseData.put("remarkContents", remarkContents);
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * 公式お知らせ
     * http://app.ja.unisonleague.com/app_jp/information.php?action_information_past=true&lang=jp
     * */
    public ArrayList<Map<String, String>> getInformationParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("div.item");
        ListIterator<Element> targetElementLists = targetElements.listIterator();
        while(targetElementLists.hasNext()) {
            Element targetElement = targetElementLists.next();
            Map<String, String> parseData = new HashMap<String, String>();

            parseData.put("url",        targetElement.getElementsByTag("a").attr("href"));
            parseData.put("image_url", targetElement.getElementsByTag("img").attr("src"));
            parseData.put("category",  targetElement.getElementsByClass("category").text());
            parseData.put("time",       targetElement.getElementsByClass("tv").text());
            parseData.put("title_word", targetElement.getElementsByClass("title_word").text());
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * 図鑑
     * https://gamy.jp/unisonleague/dictionary/equipments?order=latest
     * */
    public ArrayList<Map<String, String>> getDictionaryParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("li.fl");
        ListIterator<Element> targetElementLists = targetElements.listIterator();
        while(targetElementLists.hasNext()) {
            Element targetElement = targetElementLists.next();
            Map<String, String> parseData = new HashMap<String, String>();

            parseData.put("url",        "https://gamy.jp"+ targetElement.getElementsByTag("a").attr("href"));
            parseData.put("image_url", targetElement.getElementsByTag("img").attr("src"));
            parseData.put("char_no",    targetElement.getElementsByClass("dictionaryList__item__uid").text());
            parseData.put("char_name", targetElement.getElementsByTag("p").text());
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * 雑談掲示板
     * https://gamy.jp/unisonleague/dictionary/equipments?order=latest
     *
     * 質問掲示板
     * https://gamy.jp/unisonleague/bbs/qa
     *
     * ギルド募集
     * https://gamy.jp/unisonleague/bbs/other
     *
     * 招待ID
     * https://gamy.jp/unisonleague/bbs/invites
     * */
    public ArrayList<Map<String, String>> getBbsParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("li.cf");
        ListIterator<Element> targetElementLists = targetElements.listIterator();
        while(targetElementLists.hasNext()) {
            Element targetElement = targetElementLists.next();
            Map<String, String> parseData = new HashMap<String, String>();

            parseData.put("url",        targetElement.getElementsByTag("a").attr("href"));
            parseData.put("bbs_user_image",targetElement.getElementsByTag("img").attr("src"));

            Elements tmpTargetElement = targetElement.getElementsByClass("c-comment__meta");
            parseData.put("bbs_user_name", tmpTargetElement.get(0).getElementsByClass("fl").text());
            parseData.put("bbs_post_time", tmpTargetElement.get(0).getElementsByClass("fr").text());
            parseData.put("comment_title",targetElement.getElementsByClass("c-comment__title__text").text());
            parseData.put("comment_num",  targetElement.getElementsByClass("ml4").text());
            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    /**
     * 公式お知らせ詳細
     * http://app.ja.unisonleague.com/app_jp/information.php?action_information_detail=true&information_id=1434&callback=information_past&row=1&lang=jp
     * */
    public String getInformationDetailOuterHtml() {
        document.select("div.detail_back").remove();
        document.getElementsByTag("body").get(0).getElementsByTag("script").remove();
        return document.outerHtml();
    }

    /**
     * 図鑑詳細
     * https://gamy.jp/unisonleague/dictionary/equipments/589
     * */
    public ArrayList<Map<String, String>> getDictionaryDetailParseData() {
        ArrayList<Map<String, String>> parseDatas = new ArrayList<Map<String, String>>();

        Elements targetElements = (Elements)document.select("div.l-threeColumn__main__content");
        ListIterator<Element> targetElementLists = targetElements.listIterator();
        while(targetElementLists.hasNext()) {
            Element targetElement = targetElementLists.next();
            Map<String, String> parseData = new HashMap<String, String>();

            parseData.put("item_title",     targetElement.getElementsByClass("s-mainTitle__content__title").text());
            parseData.put("item_image_url", targetElement.getElementsByClass("p-dictionaryItem__image").get(0).getElementsByTag("img").attr("src"));

            Elements items = (Elements)targetElement.getElementsByTag("section");
            ListIterator<Element> itemLists = items.listIterator();
            Elements tmpItemLists = null;

            Element section1 = itemLists.next();
            tmpItemLists = section1.getElementsByTag("dd");
            parseData.put("item_name", tmpItemLists.get(0).text());
            parseData.put("item_rare", tmpItemLists.get(1).text());
            parseData.put("item_type", tmpItemLists.get(2).text());
            parseData.put("item_kind", tmpItemLists.get(3).text());
            parseData.put("item_attribute", tmpItemLists.get(4).text());
            parseData.put("item_cost", tmpItemLists.get(5).text());
            parseData.put("item_evolution_num", tmpItemLists.get(6).text());
            parseData.put("item_max_level", tmpItemLists.get(7).text());

            Element section2 = itemLists.next();
            tmpItemLists = section2.getElementsByTag("tr");
            Elements tmp2 = tmpItemLists.get(1).getElementsByTag("td");
            parseData.put("item_attack", tmp2.get(0).text());
            parseData.put("item_max_attack", tmp2.get(1).text());

            tmp2 = tmpItemLists.get(2).getElementsByTag("td");
            parseData.put("item_m_attack", tmp2.get(0).text());
            parseData.put("item_m_max_attack", tmp2.get(1).text());

            tmp2 = tmpItemLists.get(3).getElementsByTag("td");
            parseData.put("item_defence", tmp2.get(0).text());
            parseData.put("item_max_defence", tmp2.get(1).text());

            tmp2 = tmpItemLists.get(4).getElementsByTag("td");
            parseData.put("item_m_defence", tmp2.get(0).text());
            parseData.put("item_m_max_defence", tmp2.get(1).text());

            Element section3 = itemLists.next();
            tmpItemLists = section3.getElementsByTag("dd");
            parseData.put("item_skill_name", tmpItemLists.get(0).text());
            parseData.put("item_skill_effect", tmpItemLists.get(1).text());

            Element section4 = itemLists.next();
            tmpItemLists = section4.getElementsByTag("dd");
            parseData.put("item_before_evolution_name", tmpItemLists.get(0).text());
            parseData.put("item_before_evolution_url", tmpItemLists.get(0).getElementsByTag("a").get(0).attr("href"));
            parseData.put("item_before_evolution_image",  tmpItemLists.get(0).getElementsByTag("img").attr("src"));
            parseData.put("item_after_evolution_name", tmpItemLists.get(1).text());
            parseData.put("item_after_evolution_material", tmpItemLists.get(2).text());

            Element section5 = itemLists.next();
            tmpItemLists = section5.getElementsByTag("p");
            parseData.put("item_comment", tmpItemLists.get(0).text());

            parseDatas.add(parseData);
        }

        return parseDatas;
    }

    private String getText(Node node, String... tagNames) {
        String text = "";
        List<Node> nodes = node.childNodes();
        for(Node tmpNode : nodes) {
            if (tmpNode.childNodes().size() > 0) {
                text += getText(tmpNode, tagNames);
            } else {
                if (tmpNode instanceof TextNode) {
                    text += ((TextNode)tmpNode).text();
                } else {
                    for(int i = 0;i < tagNames.length;i++) {
                        if (tmpNode.nodeName().equals(tagNames[i])) {
                            text += ((Element)tmpNode).text();
                        }
                    }
                }
            }
        }

        return text;
    }
}
