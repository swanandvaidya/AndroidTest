package example.com.androidtest.presenter;

import android.content.Context;

import java.util.List;

import example.com.androidtest.data.SearchViewInteractor;
import example.com.androidtest.pojo.Product;
import example.com.androidtest.pojo.ProductResult;
import example.com.androidtest.view.SearchContractView;

public class SearchViewPresenterImpl implements
        SearchViewInteractor.OnFinishedSearchResultListener {

    private Context context;
    private SearchContractView searchView;
    private SearchViewInteractor searchViewInteractor;


    public SearchViewPresenterImpl(Context context, SearchContractView view){
        this.context = context;
        this.searchView = view;
        this.searchViewInteractor = new SearchViewInteractor(context,this);
    }

    public void searchProducts(String searchQuery){
        if(searchView != null)
            searchView.showProgress();
        this.searchViewInteractor.searchProducts(searchQuery,this);
    }

    @Override
    public void onSuccessOfSearch(List<ProductResult> list) {
        if(searchView != null){
            searchView.hideProgress();
            searchView.onSuccess(list);
        }
    }

    @Override
    public void onFailedOfSearch(String message) {
        if(searchView != null){
            searchView.hideProgress();
            searchView.onError(message);
        }
    }
}
