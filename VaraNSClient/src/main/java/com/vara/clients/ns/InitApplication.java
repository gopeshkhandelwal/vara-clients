package com.vara.clients.ns;

import static com.vara.clients.ns.Messages.ERROR_OCCURRED;
import static com.vara.clients.ns.Messages.INVALID_WS_URL;
import static com.vara.clients.ns.Messages.WRONG_PROPERTIES_FILE;

import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netsuite.suitetalk.client.v2020_1.WsClient;

public class InitApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(InitApplication.class);
	
	public static void main(String[] args) {
		WsClient client = null;
		try {
			client = WsClientFactory.getWsClient(new Properties(), null);
		} catch (MalformedURLException e) {
			LOGGER.error(INVALID_WS_URL, e);
			System.exit(2);
		} catch (IOException e) {
			LOGGER.error(WRONG_PROPERTIES_FILE, e);
			System.exit(1);
		} catch (Exception e) {
			LOGGER.error(ERROR_OCCURRED, e);
			System.exit(1);
		}
		new InitOperations(client).run();
	}
}
