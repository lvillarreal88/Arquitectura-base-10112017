package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Note;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.ProductPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.ProductAdapter;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductActivity extends BaseActivity<ProductPresenter> implements IProductView {

    private ListView productList;
    private ProductAdapter productAdapter;
    //private ContentLoadingProgressBar progress;
    private FloatingActionButton buttonLaunchCreate;
    private SwipeRefreshLayout swipRefresh;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setPresenter(new ProductPresenter());
        getPresenter().inject(this, getValidateInternet());
        //createProgressDialog();
        productList = (ListView) findViewById(R.id.product_listView);
        swipRefresh = (SwipeRefreshLayout) findViewById(R.id.swipRefresh);
        //progress = (ContentLoadingProgressBar) findViewById(R.id.progress);
        //progress.show();
        getPresenter().getListProduct();
        loadEvents();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getPresenter().getListProduct();
    }

    private void loadEvents() {
        buttonLaunchCreate = (FloatingActionButton) findViewById(R.id.fab_launch_createproduct);
        buttonLaunchCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CreateProductActivity.class);
                startActivity(intent);
            }
        });

        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getListProduct();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipRefresh.setRefreshing(true);
    }

    @Override
    public void showProductList(final ArrayList<Product> productArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // progress.hide();
                swipRefresh.setRefreshing(false);
                callAdapter(productArrayList);
            }
        });
    }

    @Override
    public void showAlertDialogInternet(final int title, final int message) {
       showAlertDialog(title, getResources().getString(message));

    }

    private void showAlertDialog(final int title, final String message) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               swipRefresh.setRefreshing(false);
               getShowAlertDialog().showAlertDialog(title, message, false, R.string.accept, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        getPresenter().getListProduct();
                   }
               }, R.string.cancel, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finish();
                   }
               });
           }
       });
    }

    @Override
    public void showAlertError(int title, String message) {
        showAlertDialog(title, message);
    }

    @Override
    public void showNoteList(Note note) {

    }

    private void callAdapter(final ArrayList<Product> productArrayList) {
        productAdapter =  new ProductAdapter(this, R.id.product_listView, productArrayList);
        productList.setAdapter(productAdapter);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductActivity.this, DetailActivity.class);
                intent.putExtra(Constants.ITEM_PRODUCT,productArrayList.get(position));
                startActivity(intent);
            }
        });
    }


}
