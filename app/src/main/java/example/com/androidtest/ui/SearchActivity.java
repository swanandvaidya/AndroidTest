package example.com.androidtest.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.List;
import example.com.androidtest.R;
import example.com.androidtest.adapter.ProductListAdapter;
import example.com.androidtest.interfaces.OnItemClickListener;
import example.com.androidtest.pojo.Product;
import example.com.androidtest.pojo.ProductResult;
import example.com.androidtest.presenter.SearchViewPresenterImpl;
import example.com.androidtest.view.SearchContractView;

/**
 * @author SwanandVaidya
 * @version 1.0.0
 * @since 5 Sept 2018
 *
 * {@link SearchActivity} used to search the product and display the results
 *
 * */

public class SearchActivity extends AppCompatActivity implements
        SearchContractView,
        OnItemClickListener{

    private SearchView searchView;
    private SearchViewPresenterImpl searchViewPresenterImpl;
    private RecyclerView recycleView;
    private ProductListAdapter productListAdapter;
    private ProgressBar progressBar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // init all views with their ids
        initElementsWithIds();

        // initialize the progress bar
        initProgressBar();

        // initialize the recycler view
        initRecyclerView();

        // handle all click events
        initElementsWithListeners();

        // init the search view presenter
        initSearchViewPresenter();

        // customize the search view
        //customizedSearchView();
    }

    private void initProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    /* customize the search view like text color, hint text color, search icon, etc. */
    private void customizedSearchView() {
        // changes the text color of the search view
        int id = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.WHITE);
        EditText et = searchView.findViewById(id);
        et.setHintTextColor(Color.WHITE);

        // used to change the search icon of default one
        // can also use the custom icon
        // provide the drawable to use custom icon
        int icon = searchView.getContext().getResources()
                .getIdentifier("android:id/search_mag_icon",null,null);
        ImageView iv = searchView.findViewById(icon);
        iv.setColorFilter(Color.WHITE);

    }

    private void initElementsWithIds() {
        searchView = findViewById(R.id.search_view);
        recycleView = findViewById(R.id.recycle_view);
    }

    /** initializes the recycler view */
    private void initRecyclerView() {
        recycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleView.setLayoutManager(layoutManager);
    }

    private void initElementsWithListeners() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchViewPresenterImpl.searchProducts(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initSearchViewPresenter() {
        searchViewPresenterImpl = new SearchViewPresenterImpl(SearchActivity.this,this);
    }


    @Override
    public void onSuccess(List<ProductResult> list) {
        productListAdapter = new ProductListAdapter(SearchActivity.this,list,this);
        recycleView.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(SearchActivity.this,""+message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    /** {@link OnItemClickListener} used to handle the on click event
     *  used when user click on the list  */
    @Override
    public void onItemClick(Product product, int position) {
        // currently showing using dialog
        // we can use separate screen for the details, but currently just showing into dialog
        showProductSelectedDialog(product);
    }

    /* shows the product data, description, barcode, etc. */
    private void showProductSelectedDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        ImageView ivProduct = view.findViewById(R.id.iv_product);
        Picasso.get().load(product.getImageURL()).error(R.drawable.ic_image_loading).into(ivProduct);
        TextView tvProductDesc = view.findViewById(R.id.tv_product_desc);
        tvProductDesc.setText("Description: "+product.getDescription());
        TextView tvProductClass = view.findViewById(R.id.tv_product_class);
        tvProductClass.setText("Class: "+product.get_class());
        TextView tvProductSubDesc = view.findViewById(R.id.tv_product_sub_dept);
        tvProductSubDesc.setText("Sub Dept: "+product.getSubDept());
        TextView tvProductBarcode = view.findViewById(R.id.tv_product_barcode_no);
        tvProductBarcode.setText("Barcode: "+product.getBarcode());
        builder.setView(view);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
