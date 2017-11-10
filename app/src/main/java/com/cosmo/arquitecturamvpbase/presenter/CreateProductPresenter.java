package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.views.activities.ICreateProductView;

import retrofit.RetrofitError;

/**
 * Created by jersonsuaza on 9/19/17.
 */

public class CreateProductPresenter extends BasePresenter<ICreateProductView> {

    private IProductRepository productRepository;

    public CreateProductPresenter(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /*public void createNewProduct(Product product) {
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(product);
        }else{
            getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }*/

    public void createNewProduct(String name, String description, String price, String quantity) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(product);
        }else{
            getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }

    public void createThreadCreateProduct(final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createNewProductService(product);
            }
        });
        thread.start();
    }

    private void createNewProductService(Product product){
        try{
            productRepository.createProduct(product);
            getView().showResultCreateNewProduct(true);
        }catch (RetrofitError retrofitError){
            getView().showResultCreateNewProduct(false);
        }
    }

}
