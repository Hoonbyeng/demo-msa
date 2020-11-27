package com.hbyoon.demo.common.rest;

import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import com.hbyoon.demo.common.util.JsonUtils;
import com.hbyoon.demo.common.util.RequestUtils;

public abstract class ClientInfoHolder {
	private static final ThreadLocal<ClientInfo> HOLDER = new ThreadLocal<>();

	public static ClientInfo getClientInfo() {
		ClientInfo clientInfo = HOLDER.get();
		if ( Objects.isNull(clientInfo) ) {
			clientInfo = resolveClientInfo();
			HOLDER.set(clientInfo);
		}
		return clientInfo;
	}
	
	private static ClientInfo resolveClientInfo() {
		HttpHeaders headers = RequestUtils.requestHeaders();
		if ( Objects.nonNull(headers) ) {
			String value = headers.getFirst(ClientInfo.CLIENT_INFO_HEADER_NAME);
			if ( StringUtils.hasText(value) ) {
				return JsonUtils.object(value, ClientInfo.class);
			}
		}
		
		return ClientInfo.defaultValue();
	}
	
	public static void clear() {
		HOLDER.remove();
	}
}
