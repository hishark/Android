package com.example.liu;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GetNewsFromInternet {

	private static ArrayList<News> loaclNewsList = new ArrayList<News>();// 地方新闻集合
	private final static String absUrl = "http://www.muscles.com.cn";
	private static String picLink;
	private static String title;
	private static String tips;
	private static String time_count;
	private static String detailContentLink;
	private static StringBuilder sb=new StringBuilder();
	private static String detailContents;
	
	public static String getWebContentCode(String url) {
		System.out.print("url:"+url);
		Document doc = null;
		String htmlcode = null;
		try {
			doc = Jsoup.connect(url).get();
			htmlcode = doc.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	System.out.print("htmlcode:"+htmlcode);
		return htmlcode;
	}

	public static ArrayList<News> getLocalNewsContent(String htmlcode) {
		Document doc = Jsoup.parse(htmlcode);
		Elements newslist = doc.getElementsByClass("newslist");
		for(Element newslistDiv : newslist)
		{
			//Elements newsboxDiv= newslistDiv.getElementsByClass("box");
			
			Elements newsboxDiv= newslistDiv.getElementsByClass("img");
			
			for(Element box : newsboxDiv)
			{
				title = box.getElementsByTag("a").first().attr("title");
				//Log.i("tag", title);
				
				detailContentLink = box.getElementsByTag("a").first().attr("href");
				picLink = box.getElementsByTag("a").first().getElementsByTag("img").attr("src");
				//Log.i("tag", picLink);
				
				tips = box.getElementsByTag("dd").text();
				//Log.i("tag", tips);
				
				time_count = box.getElementsByTag("p").first().text();
				//Log.i("tag", time_count);		
				
				
				
				try {
					Document detailArc = Jsoup.connect(absUrl+detailContentLink).get();
					Elements contentBox = detailArc.getElementsByClass("contentbox");
					
					for(Element box1 : contentBox)
					{
						Elements detailcontentbox = box1.getElementsByClass("content");
						detailContents = detailcontentbox.text();
						System.out.print(detailContents);
						
						 
					}		
					News news=new News(title, tips,picLink,detailContents,time_count);
					loaclNewsList.add(news);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				
			}		
			System.out.print(absUrl+detailContentLink);
			
		
			/*try {
				Document detailArc = Jsoup.connect(absUrl+detailContentLink).get();
				Elements contentBox = detailArc.getElementsByClass("contentbox");
				
				for(Element box : contentBox)
				{
					Elements detailcontentbox = box.getElementsByClass("content");
					detailContents = detailcontentbox.text();
					System.out.print(detailContents);
					
					for(Element box1 : detailcontentbox)
					{
						String detailContent = box1.getElementsByTag("p").text();
						sb.append(detailContent);
					}
					System.out.print(sb.toString());
			        
				}		
				News news=new News(title, tips,picLink,detailContents,time_count);
				loaclNewsList.add(news);
			} catch (IOException e) {
				
				e.printStackTrace();
			}*/
			
		}	
		return loaclNewsList;
	}
}
