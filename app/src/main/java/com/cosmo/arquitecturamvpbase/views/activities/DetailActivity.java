package com.cosmo.arquitecturamvpbase.views.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.DetailProductPresenter;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class DetailActivity extends BaseActivity<DetailProductPresenter> implements IDetailProductView {

    private TextView nameValue;
    private TextView descriptionValue;
    private TextView quantityValue;
    private TextView priceValue;
    private Product product;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.activity_product_detail);
        setPresenter(new DetailProductPresenter(new ProductRepository()));
        getPresenter().inject(this, getValidateInternet());
        createProgressDialog();
        loadView();
        product = (Product) getIntent().getSerializableExtra(Constants.ITEM_PRODUCT);
        setDataItem();

    }

    private void setDataItem() {
        nameValue.setText(product.getName());
        descriptionValue.setText(product.getDescription());
        quantityValue.setText(product.getQuantity());
        priceValue.setText(product.getPrice());
    }

    private void loadView() {

        nameValue = (TextView) findViewById(R.id.product_detail_name_value);
        descriptionValue = (TextView) findViewById(R.id.product_detail_description_value);
        quantityValue = (TextView) findViewById(R.id.product_detail_quantity_value);
        priceValue = (TextView) findViewById(R.id.product_detail_price_value);


    }


    @Override
    public void showToast(final int message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    public void deleteProduct(View view){
        getPresenter().deleteProduct(product.getId());
    }
}
