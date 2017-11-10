package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.helper.TypeDecryption;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.User;
import com.cosmo.arquitecturamvpbase.services.IServices;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductRepository implements IProductRepository {

    private IServices services;


    public ProductRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.JSON);
        services = (IServices) servicesFactory.getInstance(IServices.class);
    }
    @Override
    public ArrayList<Product> getProductList() throws RetrofitError{
        ArrayList<Product>  products = services.getProductList();
        return products;
    }
    @Override
    public Product createProduct(Product product) throws RetrofitError{
        Product productCreated = services.createProduct(product);
        return productCreated;
    }

    @Override
    public DeleteResponse deleteProduct(String id)  throws RepositoryError {
        try {

           return services.deleteProduct(id);

        }catch (RetrofitError retrofitError){

            throw  MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }

    }


    @Override
    public User login(String user, String password) throws RepositoryError {

        try {

            User userLogin =  services.login(user, password);
            return userLogin;

        }catch (RetrofitError retrofitError){
            throw  MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }

    @Override
    public User autologin(String token) throws RepositoryError {

        try {

            String bearerToken = "Bearer: " +  token;

            User user =  services.autoLogin(bearerToken);
            return user;

        }catch (RetrofitError retrofitError){
            throw  MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
        }
    }
}
