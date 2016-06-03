package testRedirectRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class testRedirectRule1 {
	@Test
	public void case1() {
		// read input
		int i = 0;
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = null;
		HttpResponse resp = null;
		StringBuffer sb = null;
		URLCodec urlCodec = new URLCodec();
		try {
			for (String line : Files.readAllLines(Paths
					.get("c:\\temp\\file.txt"))) {
				i++;

				sb = new StringBuffer();
				// System.out.println("line "+i+ " is : "+line);

				if (line.indexOf("]") > 0 || line.indexOf("}") > 0
						|| line.indexOf(",") > 0 || line.indexOf(" ") > 0
						|| line.indexOf("=") > 0 || line.indexOf("&") > 0
						|| line.indexOf(";") > 0 || line.indexOf("|") > 0) {
					System.out.println("with sp chars: " + line);
				}

				else {
					sb.append("http://bugfix.totalwine.com/").append(line);
					//System.out.println("sb " + sb);
					// Getting the status code.

					// httpGet = new HttpGet(urlCodec.encode(sb.toString(),
					// CharEncoding.UTF_8).toString());
					httpGet = new HttpGet(sb.toString());
					try {
						resp = client.execute(httpGet);
					} catch (Exception e) {
						e.printStackTrace();

					}
					int statusCode = resp.getStatusLine().getStatusCode();

					if (statusCode != 200) {
						System.out.println("line " + i + " is : " + line);
						System.out.println("response code is : " + statusCode);
					}

					if (resp.getEntity() != null) {
						EntityUtils.consume(resp.getEntity());
					}

				}

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		/*
		 * // Getting the response body.
		 * 
		 * HttpClient client1 = new DefaultHttpClient(); HttpGet httpGet1 = new
		 * HttpGet("http://whatever.blah.com"); ResponseHandler<String> handler
		 * = new BasicResponseHandler();
		 * 
		 * String body = client1.execute(httpGet1, handler);
		 */
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
