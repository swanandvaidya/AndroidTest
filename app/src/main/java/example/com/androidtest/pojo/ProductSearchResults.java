package example.com.androidtest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductSearchResults {

    @SerializedName("HitCount")
    @Expose
    private String hitCount;
    @SerializedName("Results")
    @Expose
    private List<ProductResult> results = null;
    @SerializedName("SearchID")
    @Expose
    private String searchID;
    @SerializedName("ProdQAT")
    @Expose
    private String prodQAT;
    @SerializedName("Found")
    @Expose
    private String found;

    public String getHitCount() {
        return hitCount;
    }

    public void setHitCount(String hitCount) {
        this.hitCount = hitCount;
    }

    public List<ProductResult> getResults() {
        return results;
    }

    public void setResults(List<ProductResult> results) {
        this.results = results;
    }

    public String getSearchID() {
        return searchID;
    }

    public void setSearchID(String searchID) {
        this.searchID = searchID;
    }

    public String getProdQAT() {
        return prodQAT;
    }

    public void setProdQAT(String prodQAT) {
        this.prodQAT = prodQAT;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }
}
