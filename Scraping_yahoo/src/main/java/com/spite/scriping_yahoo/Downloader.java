package com.spite.scriping_yahoo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Poller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@MessageEndpoint
public class Downloader {
	
	private static Logger LOG = LoggerFactory.getLogger(CrawlerApp.class);
	
	private static String endpoint = "https://app.rakuten.co.jp/services/api/IchibaItem/Search/20170706";
	
	private static String FORMAT = "json";
	private static String FORMAT_VERSION = "2";
	private static String applicationId = "1011720797907834280";
	private static String affiliateId = "16b9aabf.bdd13f56.16b9aac0.e81b9a3f";
	private static String keyword = "";
	private static String genreId = "";
	private static String page = "1";
	
	private static String sort = "standard";//並び順
	private static String HITS = "30"; //一度に取得する件数
	private static String shopCode = "rakuten24";
	
	@Autowired
	private CrawlerConfig config;

	@Autowired
	private RestTemplate template;

	@SuppressWarnings("unchecked")
	@InboundChannelAdapter(value = "channel1", poller = @Poller("downloadTrigger"))
	public Map<String, Object> download() {

		Map<String, String> params = setParam();
		String url = setUrl(params);
		
		Map<String,Object> response = null;
		int i=0;
		while (i<5) {
		  try {
		    response = template.getForObject(url, Map.class, params);
		    break;
		  } catch (RestClientException e) {
		    LOG.error("RestClientException",e);
		    response = new HashMap<String,Object>();
		    i++;
		  }
		  try {
		    Thread.sleep(500);
		  } catch (InterruptedException e) {
		    LOG.error("InterruptedException",e);
		  }
		}
		
//		List<Map> items = (List<Map>) response.get("Items");
//		for (Map item: items) {
//		  String title = (String) item.get("itemName");
//		  String price = String.format("¥ %,3d", (Integer) item.get("itemPrice"));
//		}
		return response;
	}

	private String setUrl(Map<String, String> params) {
		StringBuffer buffer = new StringBuffer();
		for (Iterator<Entry<String,String>> it = params.entrySet().iterator(); it.hasNext();) {
		  Map.Entry<String,String> entry = it.next();
		  String key = entry.getKey();
		  String value = entry.getValue();
		  buffer.append(String.format("%s=%s", key,value));
		  if (it.hasNext()) {
		    buffer.append("&");
		  }
		}
		String url = String.format("%s?%s", endpoint, buffer.toString());
		LOG.info("url={}", url);
		return url;
	}
	
	public Map<String, String> setParam() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("format", FORMAT);
		params.put("formatVersion", FORMAT_VERSION);
		if (!HITS.isEmpty())params.put("hits", HITS);
		params.put("applicationId", applicationId);
		if (!affiliateId.isEmpty())
			params.put("affiliateId", affiliateId);
		//if (!keyword.isEmpty())params.put("keyword", keyword);
		//if (!genreId.isEmpty())params.put("genreId", genreId);
		//if (!page.isEmpty())params.put("page", page.toString());
		//params.put("sort", sort);
		params.put("shopCode", shopCode);
		
		return params;
	}
}