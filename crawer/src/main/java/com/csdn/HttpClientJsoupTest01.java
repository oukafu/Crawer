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

	//url���� "http://www.csdn.net/" 
	public void get(String url) {
		
		CloseableHttpClient client=HttpClients.createDefault();	//����һ��Ĭ�ϵ�����ͻ���
		
		HttpGet get=new HttpGet(url);				//����һ��get����

		CloseableHttpResponse response=null;		//����һ����Ӧ
		
		try {
			response=client.execute(get);
			System.out.println(response.getStatusLine().getStatusCode());//��ӡ��Ӧ״̬�룬200��ʾ�ɹ�
			HttpEntity entity=response.getEntity();		//��ȡ��Ӧʵ��
			String html=EntityUtils.toString(entity);	//��ʵ�������ת��Ϊ�ַ���
			
			/**
			 * ������������jsoup������ǰ��ȡ�õ�html������ȡcsdn��ҳ�ļ���ͷ����Ŀ�µı���
			 */
			Document document=Jsoup.parse(html);	//����Jsoup��ľ�̬��������htmlת����һ��Document����
			Element element=document.select("div.wrap .left .hot_blog ul").first();	//����selectѡ������ȡ����Ҫ��liԪ�ؼ���
			Elements elements= element.select("a");	//ȡ��a���ӵļ���
			
			for (Element element2 : elements) {
				System.out.println("���⣺"+element2.attr("title")+"		-->>	��ַ��"+element2.attr("href"));
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
