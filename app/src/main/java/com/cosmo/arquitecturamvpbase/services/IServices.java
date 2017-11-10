package com.cosmo.arquitecturamvpbase.services;

import com.cosmo.arquitecturamvpbase.model.BreakFastMenu;
import com.cosmo.arquitecturamvpbase.model.DeleteResponse;
import com.cosmo.arquitecturamvpbase.model.Note;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.model.User;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IServices {

    @GET("/products")
    ArrayList<Product> getProductList();

    @POST("/products")
    Product createProduct(@Body Product product);

    @DELETE("/products/{id}")
    DeleteResponse deleteProduct(@Path("id")String id);

    @GET("/note.xml")
    Note getNote();

    @GET("/simple.xml")
    BreakFastMenu getBreakFastMenu();




    @GET("/user/auth")
    User login(@Query("email") String user, @Query("password") String password);


    @GET("/user")
    User autoLogin(@Header("Authorization") String token);

}
