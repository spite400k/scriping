package com.spite.scriping_yahoo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.springframework.expression.ParseException;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;

@MessageEndpoint
public class Scraper {

	@Splitter(inputChannel = "channel1", outputChannel = "channel3")
	public HashMap<String, Object> scrape(HashMap<String, Object> payload) {

		final List<Element> anchorList = new ArrayList<Element>();

		// 1. values()でマップの全値を取得する
		List<Object> listValues = new ArrayList<Object>(payload.values());

		// 2. keySet()でマップのキー値をすべて取得する
		List<String> listKeys = new ArrayList<String>(payload.keySet());

		// 3. それぞれのリストの内容を確認します。
		System.out.println(listValues.toString());
		System.out.println(listKeys.toString());

		//return new ArrayList(DumpEntry(timestamp, id, ref, status));
		return payload ;
	}
//
//	@Filter(inputChannel = "channel2", outputChannel = "channel3")
//	public boolean filter(Element payload) {
//		Matcher m = patter.matcher(payload.toString());
//		return m.find();
//	}
//
	@Transformer(inputChannel = "channel3", outputChannel = "channel4")
	public List<DumpEntry> convert(HashMap<String, Object> payload) throws ParseException {
		//String dateStr = payload.ownText().substring(0, 19);

		//DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//format.setTimeZone(TimeZone.getTimeZone("GMT"));

		//Date date = format.parse(dateStr);
		//Timestamp timestamp = new Timestamp(date.getTime());

		String id;
		String ref;
		id = "private data";
		ref = null;
		List<DumpEntry> entryList = new ArrayList<DumpEntry>();
		List<Map> items = (List<Map>) payload.get("Items");
		for (Map item: items) {
			String itemCode = (String) item.get("itemCode");
			String itemName = (String) item.get("itemName");
			Integer itemPrice = (Integer) item.get("itemPrice");
			String catchcopy = (String) item.get("catchcopy");
			String itemCaption = (String) item.get("itemCaption");
			String itemUrl = (String) item.get("itemUrl");
			String shopUrl = (String) item.get("shopUrl");
			String shopCode = (String) item.get("shopCode");
			String shopName = (String) item.get("shopName");
			String genreId = (String) item.get("genreId");
			// 評価は0 もしくは小数点あり
//			Integer s = (Integer) item.get("reviewAverage");
//			Double d = (Double)item.get("reviewAverage");
//			BigDecimal reviewAverage = new BigDecimal(d).setScale(
//                    2, BigDecimal.ROUND_HALF_DOWN );
			
			BigDecimal reviewAverage = new BigDecimal(0);
			//Timestamp instdt = new Timestamp(System.currentTimeMillis());
			
			DumpEntry entry = new DumpEntry(itemCode, itemName, itemPrice, catchcopy, itemCaption, itemUrl, shopUrl, shopCode, shopName, genreId, reviewAverage);
			entryList.add(entry);
		}
		
		return entryList;
	}
}