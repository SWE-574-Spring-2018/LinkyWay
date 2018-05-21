package com.linkyway.model.domain;

import lombok.NoArgsConstructor;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;

/**
 * @author duran.serkan.kilic
 */

@NoArgsConstructor
public class AnnotationText{
	private String id;
	
	@NotNull
	private String tweetUrl;
	
	private String tweetId;
	
	@NotNull
	private String tweetText;
	
	@NotNull
	private String type;
	
	@NotNull
	private int startIndex;
	
	@NotNull
	private int endIndex;
	
	@NotNull
	private String comment;
	
	public AnnotationText(String tweetUrl, String tweetId, String tweetText, String type, int startIndex, int endIndex, String comment) {
		this.tweetId = tweetId;
		this.tweetUrl = tweetUrl;
		this.tweetText = tweetText;
		this.type = type;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.comment = comment;
	}
	
	public AnnotationText mapFrom(JSONObject annotation) {
		this.id = annotation.getString("id");
		this.comment = annotation.getJSONObject("body").getString("value");
		this.tweetUrl = annotation.getJSONObject("target").getString("source");
		this.type = "text";
		
		final JSONObject TextQuoteSelector = annotation.getJSONObject("target").getJSONObject("selector");
		final String start = TextQuoteSelector.getString("prefix");
		final String end = TextQuoteSelector.getString("exact");
		final String exact = TextQuoteSelector.getString("suffix");
		tweetText = start + exact + end;
		startIndex = start.length();
		endIndex = end.length();
		
		return this;
	}
	
	public String toJsonLD() {
		
		JSONObject annotation = new JSONObject();
		annotation.put("@context", "http://www.w3.org/ns/anno.jsonld");
		annotation.put("type", "Annotation");
		
		if (id != null && !id.equals("")) {
			annotation.put("id", id);
		}
		
		JSONObject body = new JSONObject();
		body.put("type", "TextualBody");
		body.put("value", comment);
		annotation.put("body", body);
		
		JSONObject target = new JSONObject();
		target.put("source", tweetUrl);
		
		JSONObject TextQuoteSelector = new JSONObject();
		TextQuoteSelector.put("type", "TextQuoteSelector");
		if (type.equals("text")) {
			TextQuoteSelector.put("exact", tweetText.substring(startIndex, endIndex));
			TextQuoteSelector.put("start", tweetText.substring(0, startIndex));
			TextQuoteSelector.put("end", tweetText.substring(endIndex, tweetText.length()));
		} else {
			TextQuoteSelector.put("exact", tweetText);
			TextQuoteSelector.put("prefix", "");
			TextQuoteSelector.put("suffix", "");
		}
		
		target.put("selector", TextQuoteSelector);
		annotation.put("target", target);
		
		return annotation.toString();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTweetUrl() {
		return tweetUrl;
	}
	
	public void setTweetUrl(String tweetUrl) {
		this.tweetUrl = tweetUrl;
	}
	
	public String getTweetId() {
		return tweetId;
	}
	
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
	
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}

