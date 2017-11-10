package com.cosmo.arquitecturamvpbase.views.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Note;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.presenter.ProductPresenter;
import com.cosmo.arquitecturamvpbase.views.activities.CreateProductActivity;
import com.cosmo.arquitecturamvpbase.views.activities.DetailActivity;
import com.cosmo.arquitecturamvpbase.views.activities.IProductView;
import com.cosmo.arquitecturamvpbase.views.activities.ProductActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.ProductAdapter;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 14/10/17.
 */

public class ProductFragment extends BaseFragment<ProductPresenter> implements IProductView{

    private ListView productList;
    private ProductAdapter productAdapter;
    //private ContentLoadingProgressBar progress;
    private FloatingActionButton buttonLaunchCreate;
    private SwipeRefreshLayout swipRefresh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product, container, false);
        setPresenter(new ProductPresenter());
        getPresenter().inject(this, getValidateInternet());
        productList = (ListView) view.findViewById(R.id.product_listView);
        productList.setNestedScrollingEnabled(true);
        swipRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipRefresh);
        loadEvents(view);



        return view;
    }

    @Override
    public void showProductList(final ArrayList<Product> productArrayList) {
        getActivity().runOnUiThread(new Runnable() {
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipRefresh.setRefreshing(false);
                getBaseActivity().getShowAlertDialog().showAlertDialog(title, message, false, R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().getListProduct();
                    }
                }, R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
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
        productAdapter =  new ProductAdapter(getActivity(), R.id.product_listView, productArrayList);
        productList.setAdapter(productAdapter);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Constants.ITEM_PRODUCT,productArrayList.get(position));
                startActivity(intent);
            }
        });
    }


    private void loadEvents(View view) {
        buttonLaunchCreate = (FloatingActionButton) view.findViewById(R.id.fab_launch_createproduct);
        buttonLaunchCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateProductActivity.class);
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
    public void onResume() {
        super.onResume();
       swipRefresh.setRefreshing(true);
        getPresenter().getListProduct();
    }
}
