package com.cosmo.arquitecturamvpbase.presenter;

import android.util.Log;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.BreakFastMenu;
import com.cosmo.arquitecturamvpbase.model.Food;
import com.cosmo.arquitecturamvpbase.model.Note;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.BreakFastMenuRepository;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.NoteRepository;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IProductView;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductPresenter extends BasePresenter<IProductView> {

    private ProductRepository productRepository;
    private NoteRepository noteRepository;
    private BreakFastMenuRepository breakFastMenuRepository;

    public ProductPresenter() {
        productRepository = new ProductRepository();
        noteRepository = new NoteRepository();
        breakFastMenuRepository = new BreakFastMenuRepository();
    }

    public void getListProduct() {
        if (getValidateInternet().isConnected()) {
            createThreadProduct();
        } else {
            getView().showAlertDialogInternet(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadProduct() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getProductList();
                //getNotesList();
                getBreakFastMenu();
            }
        });
        thread.start();
    }

    private void getProductList() {

        try {
            ArrayList<Product> productArrayList = productRepository.getProductList();
            getView().showProductList(productArrayList);

        } catch (RetrofitError retrofitError) {

            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }

    private void getNotesList(){
        try {
            Note note = noteRepository.getNote();
            Log.i("<to>",note.getTo());
            Log.i("<from>", note.getFrom());
            Log.i("<heading>", note.getHeading());
            Log.i("<body>", note.getBody());

            //getView().showNoteList(note);

        } catch (RetrofitError retrofitError) {
            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/

    }

    private void getBreakFastMenu(){
        try{
            BreakFastMenu breakFastMenu = breakFastMenuRepository.getBreakFastMenu();

            Log.i("<BreakFastMenu> ", "");

            for (Food food:breakFastMenu.getLstFood()) {
                Log.i("<name> ", food.getName());
                Log.i("<price> ", food.getPrice());
                Log.i("<description> ", food.getDescription());
                Log.i("<calories> ", food.getCalories());
            }

        }catch (RetrofitError retrofitError){
            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());
        }
    }


}
