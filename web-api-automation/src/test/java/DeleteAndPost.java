import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author aruangth
 */
public class DeleteAndPost extends BaseClass {

    @Test
    public void deleteIsSuccessful() throws IOException {

        HttpDelete request = new HttpDelete("/url-to-delete");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token the_actual_token");

        response = client.execute(request);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 204);
    }

    @Test
    public void createRepoReturns201() throws IOException {
        // Create an HTTPPost with a valid Endpoint
        HttpPost request = new HttpPost("url");

        // Set the Basic Auth Header
        String auth = "";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        // Define Json to Post and set as Entity
        String json = "{\"name\": \"deleteme\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        // Send
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 201);
    }
}
