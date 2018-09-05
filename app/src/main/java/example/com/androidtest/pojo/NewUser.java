package example.com.androidtest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewUser {

    @SerializedName("ProdQAT")
    @Expose
    private String prodQAT;


    @SerializedName("UserID")
    @Expose
    private String userID;

    public String getProdQAT() {
        return prodQAT;
    }

    public void setProdQAT(String prodQAT) {
        this.prodQAT = prodQAT;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
