package com.csdn;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientJsoupTest01 {

	//url传入 "http://www.csdn.net/" 
	public void get(String url) {
		
		CloseableHttpClient client=HttpClients.createDefault();	//定义一个默认的请求客户端
		
		HttpGet get=new HttpGet(url);				//定义一个get请求

		CloseableHttpResponse response=null;		//定义一个响应
		
		try {
			response=client.execute(get);
			System.out.println(response.getStatusLine().getStatusCode());//打印响应状态码，200表示成功
			HttpEntity entity=response.getEntity();		//获取响应实体
			String html=EntityUtils.toString(entity);	//将实体的内容转换为字符串
			
			/**
			 * 接下来就利用jsoup来解析前面取得的html，并获取csdn首页的极客头条栏目下的标题
			 */
			Document document=Jsoup.parse(html);	//利用Jsoup类的静态方法，将html转换成一个Document对象
			Element element=document.select("div.wrap .left .hot_blog ul").first();	//利用select选择器，取得需要的li元素集合
			Elements elements= element.select("a");	//取得a链接的集合
			
			for (Element element2 : elements) {
				System.out.println("标题："+element2.attr("title")+"		-->>	地址："+element2.attr("href"));
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				response.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
