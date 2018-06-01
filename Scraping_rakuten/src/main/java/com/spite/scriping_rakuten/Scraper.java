package com.spite.scriping_rakuten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;

@MessageEndpoint
public class Scraper {

	@Splitter(inputChannel = "channel1", outputChannel = "channel2")
	public List<Element> scrape(HashMap<String, Object> payload) {

		final List<Element> anchorList = new ArrayList<Element>();

		// 1. values()でマップの全値を取得する
		List<Object> listValues = new ArrayList<Object>(payload.values());

		// 2. keySet()でマップのキー値をすべて取得する
		List<String> listKeys = new ArrayList<String>(payload.keySet());

		// 3. それぞれのリストの内容を確認します。
		System.out.println(listValues.toString());
		System.out.println(listKeys.toString());

		//return new ArrayList(DumpEntry(timestamp, id, ref, status));
		return anchorList ;
	}
//
//	@Filter(inputChannel = "channel2", outputChannel = "channel3")
//	public boolean filter(Element payload) {
//		Matcher m = patter.matcher(payload.toString());
//		return m.find();
//	}
//
//	@Transformer(inputChannel = "channel3", outputChannel = "channel4")
//	public DumpEntry convert(Element payload) throws ParseException {
//		String dateStr = payload.ownText().substring(0, 19);
//
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		format.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//		Date date = format.parse(dateStr);
//		Timestamp timestamp = new Timestamp(date.getTime());
//
//		Elements list = payload.select("a");
//		String id;
//		String ref;
//		if (list.size() > 0) {
//			Element a = list.get(0);
//			id = a.ownText();
//			ref = a.attr("href");
//		} else {
//			id = "private data";
//			ref = null;
//		}
//
//		Element span = payload.select("span").get(0);
//		String status = span.ownText();
//
//		return new DumpEntry(timestamp, id, ref, status);
//	}
}