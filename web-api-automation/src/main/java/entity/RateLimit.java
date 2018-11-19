/**
 *
 */
package entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aruangth
 */
public class RateLimit {

    /**
     * { "resources": { "core": { "limit": 60, "remaining": 49, "reset": 1542605871 }, "search": {
     * "limit": 10, "remaining": 10, "reset": 1542603124 }, "graphql": { "limit": 0, "remaining": 0,
     * "reset": 1542606664 } }, "rate": { "limit": 60, "remaining": 49, "reset": 1542605871 } }
     */
    private int coreLimit;

    private String searchLimit;

    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resources) {
        Map<String, Integer> core = (Map<String, Integer>) resources.get("core");
        coreLimit = core.get("limit");

        Map<String, String> search = (Map<String, String>) resources.get("search");
        searchLimit = String.valueOf(search.get("limit"));
    }

    public int getCoreLimit() {
        return coreLimit;
    }

    public void setCoreLimit(int coreLimit) {
        this.coreLimit = coreLimit;
    }

    public String getSearchLimit() {
        return searchLimit;
    }

    public void setSearchLimit(String searchLimit) {
        this.searchLimit = searchLimit;
    }

}
