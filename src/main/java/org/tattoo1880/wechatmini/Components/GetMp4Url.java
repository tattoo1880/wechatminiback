package org.tattoo1880.wechatmini.Components;


import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.tattoo1880.wechatmini.Entity.Mp4;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class GetMp4Url {
	
	
//	@Autowired
//	private R2dbcEntityTemplate r2dbcEntityTemplate;
	
	private final R2dbcEntityTemplate r2dbcEntityTemplate;
	
	public GetMp4Url(R2dbcEntityTemplate r2dbcEntityTemplate) {
		this.r2dbcEntityTemplate = r2dbcEntityTemplate;
	}
	
	
	public Mono<Mp4> getMp4Url(String testurl) {
	
//		String testurl = "https://x.com/__Inty__/status/1857212704030667004";
		
		
		String id = testurl.split("/status/")[1];
		System.out.println(id);
		
		
		//"https://x.com/i/api/graphql/nBS-WpgA6ZG0CyNHD517JQ/TweetDetail?variables=%7B%22focalTweetId%22%3A%221857212704030667004%22%2C%22with_rux_injections%22%3Afalse%2C%22rankingMode%22%3A%22Relevance%22%2C%22includePromotedContent%22%3Atrue%2C%22withCommunity%22%3Atrue%2C%22withQuickPromoteEligibilityTweetFields%22%3Atrue%2C%22withBirdwatchNotes%22%3Atrue%2C%22withVoice%22%3Atrue%7D&features=%7B%22rweb_tipjar_consumption_enabled%22%3Atrue%2C%22responsive_web_graphql_exclude_directive_enabled%22%3Atrue%2C%22verified_phone_label_enabled%22%3Afalse%2C%22creator_subscriptions_tweet_preview_api_enabled%22%3Atrue%2C%22responsive_web_graphql_timeline_navigation_enabled%22%3Atrue%2C%22responsive_web_graphql_skip_user_profile_image_extensions_enabled%22%3Afalse%2C%22communities_web_enable_tweet_community_results_fetch%22%3Atrue%2C%22c9s_tweet_anatomy_moderator_badge_enabled%22%3Atrue%2C%22articles_preview_enabled%22%3Atrue%2C%22responsive_web_edit_tweet_api_enabled%22%3Atrue%2C%22graphql_is_translatable_rweb_tweet_is_translatable_enabled%22%3Atrue%2C%22view_counts_everywhere_api_enabled%22%3Atrue%2C%22longform_notetweets_consumption_enabled%22%3Atrue%2C%22responsive_web_twitter_article_tweet_consumption_enabled%22%3Atrue%2C%22tweet_awards_web_tipping_enabled%22%3Afalse%2C%22creator_subscriptions_quote_tweet_preview_enabled%22%3Afalse%2C%22freedom_of_speech_not_reach_fetch_enabled%22%3Atrue%2C%22standardized_nudges_misinfo%22%3Atrue%2C%22tweet_with_visibility_results_prefer_gql_limited_actions_policy_enabled%22%3Atrue%2C%22rweb_video_timestamps_enabled%22%3Atrue%2C%22longform_notetweets_rich_text_read_enabled%22%3Atrue%2C%22longform_notetweets_inline_media_enabled%22%3Atrue%2C%22responsive_web_enhance_cards_enabled%22%3Afalse%7D&fieldToggles=%7B%22withArticleRichContentState%22%3Atrue%2C%22withArticlePlainText%22%3Afalse%2C%22withGrokAnalyze%22%3Afalse%2C%22withDisallowedReplyControls%22%3Afalse%7D"
		
		String targetUrl = "https://x.com/i/api/graphql/nBS-WpgA6ZG0CyNHD517JQ/TweetDetail?variables=%7B%22focalTweetId%22%3A%22" + id + "%22%2C%22with_rux_injections%22%3Afalse%2C%22rankingMode%22%3A%22Relevance%22%2C%22includePromotedContent%22%3Atrue%2C%22withCommunity%22%3Atrue%2C%22withQuickPromoteEligibilityTweetFields%22%3Atrue%2C%22withBirdwatchNotes%22%3Atrue%2C%22withVoice%22%3Atrue%7D&features=%7B%22rweb_tipjar_consumption_enabled%22%3Atrue%2C%22responsive_web_graphql_exclude_directive_enabled%22%3Atrue%2C%22verified_phone_label_enabled%22%3Afalse%2C%22creator_subscriptions_tweet_preview_api_enabled%22%3Atrue%2C%22responsive_web_graphql_timeline_navigation_enabled%22%3Atrue%2C%22responsive_web_graphql_skip_user_profile_image_extensions_enabled%22%3Afalse%2C%22communities_web_enable_tweet_community_results_fetch%22%3Atrue%2C%22c9s_tweet_anatomy_moderator_badge_enabled%22%3Atrue%2C%22articles_preview_enabled%22%3Atrue%2C%22responsive_web_edit_tweet_api_enabled%22%3Atrue%2C%22graphql_is_translatable_rweb_tweet_is_translatable_enabled%22%3Atrue%2C%22view_counts_everywhere_api_enabled%22%3Atrue%2C%22longform_notetweets_consumption_enabled%22%3Atrue%2C%22responsive_web_twitter_article_tweet_consumption_enabled%22%3Atrue%2C%22tweet_awards_web_tipping_enabled%22%3Afalse%2C%22creator_subscriptions_quote_tweet_preview_enabled%22%3Afalse%2C%22freedom_of_speech_not_reach_fetch_enabled%22%3Atrue%2C%22standardized_nudges_misinfo%22%3Atrue%2C%22tweet_with_visibility_results_prefer_gql_limited_actions_policy_enabled%22%3Atrue%2C%22rweb_video_timestamps_enabled%22%3Atrue%2C%22longform_notetweets_rich_text_read_enabled%22%3Atrue%2C%22longform_notetweets_inline_media_enabled%22%3Atrue%2C%22responsive_web_enhance_cards_enabled%22%3Afalse%7D&fieldToggles=%7B%22withArticleRichContentState%22%3Atrue%2C%22withArticlePlainText%22%3Afalse%2C%22withGrokAnalyze%22%3Afalse%2C%22withDisallowedReplyControls%22%3Afalse%7D";
		
		
		
		
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				.url(targetUrl)
				.header("accept", "*/*")
				.header("accept-language", "zh-CN,zh;q=0.9")
				.header("authorization", "Bearer AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA")
				.header("content-type", "application/json")
				.header("cookie", "night_mode=2; g_state={\"i_p\":1727453655044,\"i_l\":1}; kdt=1zqzQTRkLbO9k2ezj42OkG4NcSCsJJjUwNsZOd3H; dnt=1; auth_multi=\"1850829389724033024:6cca0ef4a52de84b3df722825acbcb36eb6d4634\"; auth_token=95207a4ce71c889fdf2c23acab3f83c1ec626c2f; guest_id_ads=v1%3A173010723241792048; guest_id_marketing=v1%3A173010723241792048; lang=zh-cn; guest_id=v1%3A173010723241792048; twid=u%3D732225356388667392; ct0=77e2767b7dad33b9eaffea0a1b48c9a74cb768ff7ef162b7b5fad2f77c8d3df3b671d3483ddbca044162ef9952b43f534530d0573b5f625a6c8594477efc73e72b6855d1ac14d405f6b6658cba6fdabc; personalization_id=\"v1_EDLqVfqBZZSw+QQI8CPsSA==\"")
				.header("priority", "u=1, i")
				.header("referer", "https://x.com/__Inty__/status/1857212704030667004")
				.header("sec-ch-ua", "\"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"")
				.header("sec-ch-ua-mobile", "?0")
				.header("sec-ch-ua-platform", "\"macOS\"")
				.header("sec-fetch-dest", "empty")
				.header("sec-fetch-mode", "cors")
				.header("sec-fetch-site", "same-origin")
				.header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36")
				.header("x-client-transaction-id", "QzHeANzf8Atq36PztggAs8TJ2+vvP806KQT1RRGuKynU0B5ZxDpBufRsdLtgr81h0k0NpEHhCEf1TpbxSxKV58CU9v6UQA")
				.header("x-csrf-token", "77e2767b7dad33b9eaffea0a1b48c9a74cb768ff7ef162b7b5fad2f77c8d3df3b671d3483ddbca044162ef9952b43f534530d0573b5f625a6c8594477efc73e72b6855d1ac14d405f6b6658cba6fdabc")
				.header("x-twitter-active-user", "yes")
				.header("x-twitter-auth-type", "OAuth2Session")
				.header("x-twitter-client-language", "zh-cn")
				.build();
		
		
		try {
			Response response = client.newCall(request).execute();
			
			//! 使用gson 解析字符串
			
			JsonObject regularObj = JsonParser.parseString(response.body().string()).getAsJsonObject();
			System.out.println(regularObj);
			
			//! 先检测checkelement 有没有一个card 字段
			JsonElement checkElement = regularObj.getAsJsonObject("data")
					
					.getAsJsonObject("threaded_conversation_with_injections_v2")
					.getAsJsonArray("instructions")
					.get(0)
					.getAsJsonObject()
					.getAsJsonArray("entries")
					.get(0)
					.getAsJsonObject()
					.getAsJsonObject("content")
					.getAsJsonObject("itemContent")
					.getAsJsonObject("tweet_results")
					.getAsJsonObject("result");
			System.out.println(checkElement);
			if (checkElement.getAsJsonObject().has("card")) {
				
				try {
					System.out.println("含有card字段");
					String newobj = checkElement.getAsJsonObject().getAsJsonObject("card").getAsJsonObject("legacy").getAsJsonArray("binding_values").get(0).getAsJsonObject().getAsJsonObject("value").get("string_value").getAsString();
					JsonObject newobj1 = JsonParser.parseString(newobj).getAsJsonObject();
					JsonObject mediaEntities = newobj1.getAsJsonObject("media_entities");
					System.out.println(mediaEntities);
					JsonArray resultarray = new JsonArray();
					for (String key : mediaEntities.keySet()) {
						System.out.println(key);
						JsonArray jsonArray = mediaEntities.getAsJsonObject(key).getAsJsonObject("video_info").getAsJsonArray("variants");
						for (JsonElement element : jsonArray) {
							JsonObject jsonObject = element.getAsJsonObject();
							if (jsonObject.get("content_type").getAsString().equals("video/mp4")) {
								resultarray.add(jsonObject.get("url").getAsString());
							}
						}
					}
					System.out.println(resultarray);
					String title = regularObj.getAsJsonObject("data")
							.getAsJsonObject("threaded_conversation_with_injections_v2")
							.getAsJsonArray("instructions")
							.get(0)
							.getAsJsonObject()
							.getAsJsonArray("entries")
							.get(0)
							.getAsJsonObject()
							.getAsJsonObject("content")
							.getAsJsonObject("itemContent")
							.getAsJsonObject("tweet_results")
							.getAsJsonObject("result")
							.getAsJsonObject("legacy")
							.get("full_text").getAsString();
					
					Mp4 mp4 = new Mp4();
					mp4.setTitle(title);
					mp4.setUrls(resultarray.toString());
					mp4.setId(Long.parseLong(id));
					
					return r2dbcEntityTemplate.insert(Mp4.class)
							.using(mp4)
							.doOnSuccess(result -> System.out.println("Data inserted successfully"));
				} catch (JsonSyntaxException e) {
					System.out.println("JsonSyntaxException");
					return Mono.empty();
				}
			}
			
			JsonElement checkelement2 = regularObj.getAsJsonObject("data")
					.getAsJsonObject("threaded_conversation_with_injections_v2")
					.getAsJsonArray("instructions")
					.get(0)
					.getAsJsonObject()
					.getAsJsonArray("entries")
					.get(0)
					.getAsJsonObject()
					.getAsJsonObject("content")
					.getAsJsonObject("itemContent")
					.getAsJsonObject("tweet_results")
					.getAsJsonObject("result");
			
			if (checkelement2.getAsJsonObject().has("tweet")){
				try {
					System.out.println("含有tweet字段");
					JsonElement newurl = checkelement2.getAsJsonObject().getAsJsonObject("tweet")
							.getAsJsonObject("legacy")
							.getAsJsonObject("entities")
							.getAsJsonArray("media").get(0).getAsJsonObject()
							.getAsJsonObject("video_info").getAsJsonArray("variants");
//				System.out.println(newurl);
					JsonArray jparrary = new JsonArray();
					
					for (JsonElement element : newurl.getAsJsonArray()) {
						JsonObject jsonObject = element.getAsJsonObject();
						if (jsonObject.get("content_type").getAsString().equals("video/mp4")) {
							jparrary.add(jsonObject.get("url").getAsString());
						}
					}
					System.out.println(jparrary);
					String title = checkelement2.getAsJsonObject().getAsJsonObject("tweet")
							.getAsJsonObject("legacy")
							.get("full_text").getAsString();
					
					Mp4 mp4 = new Mp4();
					mp4.setTitle(title);
					mp4.setUrls(jparrary.toString());
					mp4.setId(Long.parseLong(id));
					
					
					return r2dbcEntityTemplate.insert(Mp4.class)
							.using(mp4)
							.doOnSuccess(result -> System.out.println("Data inserted successfully"));
				} catch (NumberFormatException e) {
					System.out.println("NumberFormatException");
					return Mono.empty();
				}
			}
					
			
			
			JsonElement jsonElement = regularObj.getAsJsonObject("data")
					.getAsJsonObject("threaded_conversation_with_injections_v2")
					.getAsJsonArray("instructions")
					.get(0)
					.getAsJsonObject()
					.getAsJsonArray("entries")
					.get(0)
					.getAsJsonObject()
					.getAsJsonObject("content")
					.getAsJsonObject("itemContent")
					.getAsJsonObject("tweet_results")
					.getAsJsonObject("result")
					.getAsJsonObject("legacy")
					.getAsJsonObject("entities")
					.getAsJsonArray("media").get(0)
					.getAsJsonObject()
					.getAsJsonObject("video_info")
					.getAsJsonArray("variants");
			
			
			System.out.println("=====================================");
			System.out.println(jsonElement);
			System.out.println("=====================================");
			
			
			String title = regularObj.getAsJsonObject("data")
					.getAsJsonObject("threaded_conversation_with_injections_v2")
					.getAsJsonArray("instructions")
					.get(0)
					.getAsJsonObject()
					.getAsJsonArray("entries")
					.get(0)
					.getAsJsonObject()
					.getAsJsonObject("content")
					.getAsJsonObject("itemContent")
					.getAsJsonObject("tweet_results")
					.getAsJsonObject("result")
					.getAsJsonObject("legacy")
					.get("full_text").getAsString();
			//! 过滤结果
//			List<String> jsonArray = new ArrayList<>();
			JsonArray jsonArray = new JsonArray();
			for (JsonElement element : jsonElement.getAsJsonArray()) {
				JsonObject jsonObject = element.getAsJsonObject();
				if (jsonObject.get("content_type").getAsString().equals("video/mp4")) {
					jsonArray.add(jsonObject.get("url").getAsString());
				}
			}
			System.out.println(jsonArray);
			
			Mp4 mp4 = new Mp4();
			mp4.setTitle(title);
			mp4.setUrls(jsonArray.toString());
//			mp4.setId(id);
			//todo id = > long
			mp4.setId(Long.parseLong(id));
			
			
			System.out.println(mp4);
			
			
			
			//!存入数据库
			try {
				return r2dbcEntityTemplate.insert(Mp4.class)
						.using(mp4)
						.doOnSuccess(result -> System.out.println("Data inserted successfully"));
						
			} catch (Exception e) {
				return Mono.empty();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return Mono.empty();
		}
		
		
	}
}
