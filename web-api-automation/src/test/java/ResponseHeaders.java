import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author aruangth
 */
public class ResponseHeaders extends BaseClass {

    @Test
    public void contentTypeIsJson() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        Header contentType = response.getEntity().getContentType();
        Assert.assertEquals(contentType.getValue(), "application/json; charset=utf-8");

        ContentType ct = ContentType.getOrDefault(response.getEntity());
        Assert.assertEquals(ct.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGitHub() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = ResponseUtil.getHeader(response, "Server");
        Assert.assertEquals(headerValue, "GitHub.com");
    }

    @Test
    public void xRateLimitIsSixty() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String limitVal = ResponseUtil.getHeader(response, "X-RateLimit-Limit");
        Assert.assertEquals(limitVal, "60");
    }

    @Test
    public void eTagIsPresent() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        boolean result = ResponseUtil.headerIsPresent(response, "ETag");
        Assert.assertEquals(result, true);
    }
}
