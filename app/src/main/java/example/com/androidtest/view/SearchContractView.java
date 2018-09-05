package example.com.androidtest.view;

import java.util.List;
import example.com.androidtest.pojo.ProductResult;

public interface SearchContractView {

     void onSuccess(List<ProductResult> list);
     void onError(String message);

     void showProgress();
     void hideProgress();

}
