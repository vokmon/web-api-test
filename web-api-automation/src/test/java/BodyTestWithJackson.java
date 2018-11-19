import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import entity.NotFound;
import entity.RateLimit;
import entity.User;

/**
 * @author aruangth
 */
public class BodyTestWithJackson extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/vokmon");
        response = client.execute(get);

        User user = ResponseUtil.unmarshall(response, User.class);
        Assert.assertEquals(user.getLogin(), "vokmon");
    }

    @Test
    public void returnsCorrecId() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/vokmon");
        response = client.execute(get);

        User user = ResponseUtil.unmarshall(response, User.class);
        Assert.assertEquals(user.getId(), 4063827);
    }

    @Test
    public void notFoundMessage() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");
        response = client.execute(get);

        NotFound notFound = ResponseUtil.unmarshall(response, NotFound.class);
        Assert.assertEquals(notFound.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitsAreSet() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);

        RateLimit rateLimits = ResponseUtil.unmarshall(response, RateLimit.class);
        Assert.assertEquals(rateLimits.getCoreLimit(), 60);
        Assert.assertEquals(rateLimits.getSearchLimit(), "10");
    }
}
