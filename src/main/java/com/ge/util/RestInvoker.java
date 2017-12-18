package com.ge.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ge.service.RestService;

/**
 * Class to make REST call to push data webapp test endpoint and insert data
 * into DB
 * 
 * @author Nisvin Shaffee
 *
 */
public class RestInvoker {
	final static Logger logger = Logger.getLogger(RestInvoker.class);

	private RestService service = new RestService();

	public String getJson(int run, String currentDate, long currentTime, String transactionID) {

		JSONObject woJson = new JSONObject();
		woJson.put("transaction_id", transactionID == null ? "" : transactionID.toString());
		woJson.put("run", run + "");
		woJson.put("created_at", currentDate);
		woJson.put("test_started_at", currentTime + "");
		return woJson.toString();
	}

	/**
	 * @param data
	 * @param urlString
	 * @throws CassandraQueryException
	 */
	public String postData(String studentJsonData) {
		String result = "";

		// Make REST call to push data to test end point in webapp
		System.out.println("***********************");

		try {

			HttpURLConnection conn = service.invokeRestCall(ApplicationConstants.PREDICTIONIO_URL, studentJsonData);

			int responseCode = conn.getResponseCode();
			// Log respective information into logger file
			if (responseCode == 200) {
				logger.info("Successfully called REST endpoint");
				logger.debug("Simulator POST endpoint response code = " + responseCode + " , response data = "
						+ conn.getResponseMessage());
				result = ApplicationConstants.PASS;
				
				
				
			} else {
				logger.error("Error in inserting data to DB");
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

}