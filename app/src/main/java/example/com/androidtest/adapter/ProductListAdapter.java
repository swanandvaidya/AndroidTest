package example.com.androidtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import example.com.androidtest.R;
import example.com.androidtest.interfaces.OnItemClickListener;
import example.com.androidtest.pojo.ProductResult;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    private Context context;
    private List<ProductResult> productList;
    private  OnItemClickListener listener;

    public ProductListAdapter(Context context, List<ProductResult> list, OnItemClickListener listener){
        this.context = context;
        this.productList = list;
        this.listener = listener;
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ProductResult product = productList.get(position);
        holder.tvProductDesc.setText(product.getProducts().get(0).getDescription());
        holder.tvProductSubDesc.setText(product.getProducts().get(0).getSubDept());
        holder.tvProductBarcode.setText(product.getProducts().get(0).getBarcode());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(productList.get(holder.getLayoutPosition()).getProducts().get(0),holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvProductDesc,tvProductSubDesc,tvProductBarcode;
        private CardView cardView;
        public ViewHolder(View view) {
            super(view);
            tvProductDesc = view.findViewById(R.id.tv_product_desc);
            tvProductSubDesc = view.findViewById(R.id.tv_product_sub_dept);
            tvProductBarcode = view.findViewById(R.id.tv_product_barcode_no);
            cardView = view.findViewById(R.id.card_view);
        }
    }
}
