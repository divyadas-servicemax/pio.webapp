package com.ge.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

import com.ge.util.ApplicationConstants;


/**
 * Singleton class.
 * 
 * @author nisvinshaffee
 *
 */
public class RestService {

	/**
	 * Logger for the current class
	 */
	private final static Logger logger = Logger.getLogger(RestService.class);

	public HttpURLConnection invokeRestCall(String urlString, String data) throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL(urlString);

		logger.info("***  Invoking url - " + urlString);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		if (connection instanceof HttpsURLConnection) {

			HttpsURLConnection conn1 = (HttpsURLConnection) url.openConnection();

			conn1.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			connection = conn1;
		}
		connection.setDoOutput(true);
		connection.setRequestMethod(ApplicationConstants.POST);
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStream os = connection.getOutputStream();
		os.write(data.getBytes());
		os.flush();

		return connection;
	}
}
