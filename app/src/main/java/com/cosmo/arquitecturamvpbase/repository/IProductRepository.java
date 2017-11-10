package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.User;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 23/09/17.
 */

public interface IProductRepository {


    ArrayList<Product> getProductList();

    Product createProduct(Product product);


    DeleteResponse deleteProduct(String id) throws RepositoryError;

    User login(String user, String password) throws  RepositoryError;

    User autologin(String token) throws  RepositoryError;
}
