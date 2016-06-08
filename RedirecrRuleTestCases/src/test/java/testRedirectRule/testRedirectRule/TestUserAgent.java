package testRedirectRule.testRedirectRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.RedirectLocations;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.sun.net.httpserver.Headers;

public class TestUserAgent {
	 @Test
	  public void getFinalRedirect() {
		 CloseableHttpClient httpclient  = HttpClients.custom().setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12F70 Safari/600.1.4 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").build();
		 //CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpClientContext context = HttpClientContext.create();
		 HttpGet httpget = new HttpGet("http://www.totalwine.com/wine/cShop");
		 CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget, context);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
		     HttpHost target = context.getTargetHost();
		     List<URI> redirectLocations = context.getRedirectLocations();
		     URI location = null;
			
				location = URIUtils.resolve(httpget.getURI(), target, redirectLocations);
			
		     System.out.println("Final HTTP location: " + location.toASCIIString());
		     // Expected to be an absolute URI
		 
		 } catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		     try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 
	 }
	
	
  @Test(enabled = false)
  public void testCustomUserAgent() {
	  
	  //HttpClient client = new DefaultHttpClient();
	  //client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12F70 Safari/600.1.4 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
	  HttpClient client  = HttpClients.custom().setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8.0 Mobile/12F70 Safari/600.1.4 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").build();
	  HttpContext context = new BasicHttpContext();
	  
		try {
			HttpResponse resp= client.execute(new HttpGet("http://totalwine.com/special-event/celebrate-with-sparkling/mclean/virginia/e/ec73536"),context);
			Header[] headers= resp.getAllHeaders();
			// iterate via "while loop"
			System.out.println("\n==> While Loop Example....");
			int i = 0;
			while (i < headers.length) {
				System.out.println("1: "+headers[i].getName()+":"+headers[i].getValue());
				System.out.println("1: "+headers[i].toString());
				i++;
			}
	 
			//System.out.println("1: "+resp.getStatusLine().getStatusCode());
			// System.out.println("2: "+finalUrl.toString());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  
  @Test(enabled = false)
  public void testContentHandling() {
	  CloseableHttpClient httpclient = HttpClients.createDefault();
      try {
          HttpGet httpget = new HttpGet("http://totalwine.com/special-event/celebrate-with-sparkling/mclean/virginia/e/ec73536");

          System.out.println("Executing request " + httpget.getRequestLine());

          // Create a custom response handler
          ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

              public String handleResponse(
                      final HttpResponse response) throws ClientProtocolException, IOException {
                  int status = response.getStatusLine().getStatusCode();
                
                  
                  
                  if (status >= 200 && status < 300) {
                      HttpEntity entity = response.getEntity();
                      return entity != null ? EntityUtils.toString(entity) : null;
                  } else {
                     // throw new ClientProtocolException("Unexpected response status: " + status);
                	Assert.fail("Unexpected response status: "+ response.getStatusLine().getStatusCode());  
                  }
				return null;
                  
              }

          };
          try {
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
      } finally {
          try {
			httpclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
	  
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
