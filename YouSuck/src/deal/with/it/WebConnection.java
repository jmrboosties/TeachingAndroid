package deal.with.it;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebConnection {

	public WebConnection() {
		//epic
	}
	
	public String readFromServer() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://www.tagverse.us/tutorials/interact.php");
		String string = null;
		
		try {
			HttpResponse response = client.execute(get);
			ResponseHandler<String> handler = new BasicResponseHandler();
			string = handler.handleResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return string;
	}
	
}
