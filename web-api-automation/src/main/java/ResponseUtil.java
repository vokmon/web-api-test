import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author aruangth
 */
public class ResponseUtil {

    public static String getHeader(CloseableHttpResponse response, String headerName) {
        // Get All headers
        // Header[] headers = response.getAllHeaders();
        // List<Header> httpHeaders = Arrays.asList(headers);
        // String returnHeader = "";
        // Loop over the headers list
        // for (Header header : httpHeaders) {
        // if (headerName.equals(header.getName())) {
        // returnHeader = header.getValue();
        // break;
        // }
        // }
        // if (returnHeader.isEmpty()) {
        // throw new RuntimeException("Didn't find the header: " + headerName);
        // }
        // return returnHeader;

        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeaders.stream().filter(
                header -> headerName.equals(header.getName())).findFirst().orElseThrow(
                        () -> new RuntimeException("Didn't find the header: " + headerName));
        return matchedHeader.getValue();
    }

    public static boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        return httpHeaders.stream().anyMatch(header -> header.getName().equals(headerName));
    }
}
