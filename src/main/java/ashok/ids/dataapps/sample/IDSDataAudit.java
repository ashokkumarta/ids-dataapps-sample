package ashok.ids.dataapps.sample;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ashok.ids.dataapps.common.CommonBase;

@Component
public class IDSDataAudit extends CommonBase {


	@Value("${audit.url}")
	private String auditUrl;

	@Value("${audit.auth}")
	private String authToken;

	private final String CONTENT_TYPE = "application/json";

	public IDSDataAudit() {
	}

	public void audit(String data) throws IOException {
		
		logger.info("Sending for audit: {}",data);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(auditUrl);
		post.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
		post.setHeader(HttpHeaders.AUTHORIZATION, authToken);

		StringEntity body = new StringEntity(data);

		HttpResponse response = httpclient.execute(post);
		httpclient.getConnectionManager().shutdown();
		logger.info("Audit successful for: {}",data);
	}
}
