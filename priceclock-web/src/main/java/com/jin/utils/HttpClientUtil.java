package com.jin.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
/**
 * doGet和doPost请求
 * @author jinrong.wang
 *
 */
public class HttpClientUtil {
	/**
	 * 未经过URI转码
	 * get请求,暂时项目中用到个方法主要是用于获取token
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.parseObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * URI转码的
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doGet(String url) throws Exception {
		// 创建一个httpclient链接对象
		HttpClient httpclient = new DefaultHttpClient();
		URL mURL = new URL(url);
		URI uri = new URI(mURL.getProtocol(), mURL.getHost(), mURL.getPath(), mURL.getQuery(), null);
		HttpGet httpGet = new HttpGet(uri);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		// 执行请求并获取结果
		String responseBody = httpclient.execute(httpGet, responseHandler);
		JSONObject jsonObject = JSONObject.parseObject(responseBody);
		return jsonObject;
	}
	
	/**
	 * POST请求,目前用于这个的有创建菜单
	 * @param url
	 * @param outStr
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSONObject.parseObject(result);
		return jsonObject;
	}
}
