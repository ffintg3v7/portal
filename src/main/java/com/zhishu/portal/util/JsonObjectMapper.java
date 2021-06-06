package com.zhishu.portal.util;

public class JsonObjectMapper extends org.codehaus.jackson.map.ObjectMapper {

	public JsonObjectMapper() {
		super();

		this.getSerializerProvider().setNullValueSerializer(new NullJsonSerializer());
	}
}
