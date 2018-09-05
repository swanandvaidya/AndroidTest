package example.com.androidtest.rest;

import java.util.List;

import example.com.androidtest.pojo.NewUser;
import example.com.androidtest.pojo.ProductSearchResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


/**
 * This class used for the all get, post, put, etc methods API's
 * class handles all app API call declaration
 *
 * @author SwanandVaidya
 * @version 1.0
 * @since 29 August 2018
 */

public interface WarehouseAPI {

    @GET("newuser.json/")
    Call<NewUser> getNewUser(@Header("Ocp-Apim-Subscription-Key") String subscriptionKey);

    @GET("search.json/")
    Call<ProductSearchResults> getProducts(@Header("Ocp-Apim-Subscription-Key") String subscriptionKey,
                                           @Query("Start") String start,
                                           @Query("Limit") String limit,
                                           @Query("Search") String search,
                                           @Query("UserID") String userId);
}
