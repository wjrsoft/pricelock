package com.jin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin.utils.HttpClientUtil;

/**
 * 获取外汇价格
 */
@Controller
public class GetCNYPrice {
	private final static Logger logger = LoggerFactory.getLogger(GetCNYPrice.class);
	
	
	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping("/getCNYPrice.do")
	@ResponseBody
	public String getCNYPrice(HttpServletRequest req, HttpServletResponse resp) {
		//允许跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		System.out.println("进入getCNYPrice.do");
		try {
			JSONObject jsonObject = HttpClientUtil.doGet(
					"http://api.k780.com/?app=finance.shgold&goldid=1052&version=3&appkey=51412&sign=99a291c772ca77fd6aee3906f0fadc4e&format=json");
			json=jsonObject.toJSONString();
			logger.info("价格[{}]",json);
		} catch (Exception e) {
			logger.error("异常[{}]",e);
		}
		return json;
	}
	public String getPrice(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Object obj = jsonObject.get("records");
		logger.info(JSON.toJSONString(obj));
		return JSON.toJSONString(obj);
	}

	
}
