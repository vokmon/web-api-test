import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author aruangth
 */
public class Get401 extends BaseClass {

    @DataProvider
    private Object[][] endpoints() {
        return new Object[][] {
            {
                "/user"
            }, {
                "/user/followers"
            }, {
                "/notifications"
            }
        };
    }

    @Test(dataProvider = "endpoints")
    public void returns401(String endPoint) throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endPoint);
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatus, 401);
    }

    // @Test
    // public void userReturns401() throws ClientProtocolException, IOException {
    // HttpGet get = new HttpGet(BASE_ENDPOINT + "/user");
    // response = client.execute(get);
    // int actualStatus = response.getStatusLine().getStatusCode();
    // Assert.assertEquals(actualStatus, 401);
    // }
    //
    // @Test
    // public void userFollowerReturns401() throws ClientProtocolException, IOException {
    // HttpGet get = new HttpGet(BASE_ENDPOINT + "/user/followers");
    // response = client.execute(get);
    // int actualStatus = response.getStatusLine().getStatusCode();
    // Assert.assertEquals(actualStatus, 401);
    // }
    //
    // @Test
    // public void notificationsReturns401() throws ClientProtocolException, IOException {
    // HttpGet get = new HttpGet(BASE_ENDPOINT + "/notifications");
    // response = client.execute(get);
    // int actualStatus = response.getStatusLine().getStatusCode();
    // Assert.assertEquals(actualStatus, 401);
    // }
}
