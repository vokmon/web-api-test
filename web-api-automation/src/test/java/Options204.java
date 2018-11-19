import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author aruangth
 */
public class Options204 extends BaseClass {

    @Test
    public void optionsReturnsCorrectMethodsList() throws ClientProtocolException, IOException {
        String header = "Access-Control-Allow-Methods";
        // The order can be different. This is just for demonstration.
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        response = client.execute(request);

        String actualValue = ResponseUtil.getHeader(response, header);
        Assert.assertEquals(actualValue, expectedReply);
    }
}
