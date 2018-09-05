package example.com.androidtest.data;

import android.content.Context;

import java.util.List;
import example.com.androidtest.pojo.ProductResult;
import example.com.androidtest.pojo.ProductSearchResults;
import example.com.androidtest.rest.WarehouseAPI;
import example.com.androidtest.rest.WarehouseService;
import example.com.androidtest.utils.AppConstants;
import example.com.androidtest.utils.AppPreferences;
import example.com.androidtest.utils.AppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author SwanandVaidya
 * @version 1.0.0
 * @since 5 Sept 2018
 *
 * Will act as a model class which getting data from API
 * */
public class SearchViewInteractor {

    private WarehouseAPI warehouseAPI;
    private OnFinishedSearchResultListener listener;
    private Context context;

    // CTOR
    public SearchViewInteractor(Context context, OnFinishedSearchResultListener listener){
        this.context = context;
        this.listener = listener;
        warehouseAPI = WarehouseService.getClient().create(WarehouseAPI.class);
    }

    // search product by query
    // currently using limited params
    public void searchProducts(String searchQuery, final OnFinishedSearchResultListener listener) {
        Call<ProductSearchResults> call =  warehouseAPI.getProducts(AppUtils.getSubscriptionKey(),
                "0",
                "10",
                searchQuery,
                AppPreferences.getInstance(context).getPrefString(AppConstants.PREF_USER_ID));
        call.enqueue(new Callback<ProductSearchResults>() {
            @Override
            public void onResponse(Call<ProductSearchResults> call, Response<ProductSearchResults> response) {
                ProductSearchResults productSearchResults = response.body();
                listener.onSuccessOfSearch(productSearchResults.getResults());
            }

            @Override
            public void onFailure(Call<ProductSearchResults> call, Throwable t) {
                listener.onFailedOfSearch(t.getMessage());
            }
        });
    }

    public interface OnFinishedSearchResultListener{
        void onSuccessOfSearch(List<ProductResult> list);
        void onFailedOfSearch(String error);
    }
}
