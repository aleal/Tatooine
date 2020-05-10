package com.ktech;

import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.filterchain.FilterChain;
import org.glassfish.grizzly.http.ContentEncoding;
import org.glassfish.grizzly.http.HttpCodecFilter;
import org.glassfish.grizzly.http.HttpContent;
import org.glassfish.grizzly.http.HttpHeader;
import org.glassfish.grizzly.http.HttpPacket;
import org.glassfish.grizzly.http.HttpProbe;
import org.glassfish.grizzly.http.TransferEncoding;
import org.glassfish.grizzly.http.server.HttpServer;

@SuppressWarnings("rawtypes")
public class Logging { 
	
	public static void setUp(HttpServer httpServer) {
		final FilterChain filterChain = httpServer.getListener("grizzly").getFilterChain();

		// Get HttpCodecFilter

		HttpCodecFilter codecFilter = (HttpCodecFilter) filterChain.get(filterChain.indexOfType(HttpCodecFilter.class));
		codecFilter.getMonitoringConfig().addProbes(new HttpProbe() {

			@Override
			public void onDataReceivedEvent(Connection connection, Buffer buffer) {
				System.out.println(buffer.toStringContent());

			}

			@Override
			public void onDataSentEvent(Connection connection, Buffer buffer) {
				System.out.println(buffer.toStringContent());

			}

			@Override
			public void onHeaderParseEvent(Connection connection, HttpHeader header, int size) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onHeaderSerializeEvent(Connection connection, HttpHeader header, Buffer buffer) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onContentChunkParseEvent(Connection connection, HttpContent content) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onContentChunkSerializeEvent(Connection connection, HttpContent content) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onContentEncodingParseEvent(Connection connection, HttpHeader header, Buffer buffer,
					ContentEncoding contentEncoding) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onContentEncodingParseResultEvent(Connection connection, HttpHeader header, Buffer result,
					ContentEncoding contentEncoding) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onContentEncodingSerializeEvent(Connection connection, HttpHeader header, Buffer buffer,
					ContentEncoding contentEncoding) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onContentEncodingSerializeResultEvent(Connection connection, HttpHeader header, Buffer result,
					ContentEncoding contentEncoding) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTransferEncodingParseEvent(Connection connection, HttpHeader header, Buffer buffer,
					TransferEncoding transferEncoding) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTransferEncodingSerializeEvent(Connection connection, HttpHeader header, Buffer buffer,
					TransferEncoding transferEncoding) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onErrorEvent(Connection connection, HttpPacket httpPacket, Throwable error) {
				error.printStackTrace();
			}
		});
	}
}
