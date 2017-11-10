package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.Note;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by leidyzulu on 16/09/17.
 */

public interface IProductView extends IBaseView {


    void showProductList(ArrayList<Product> productArrayList);

    void showAlertDialogInternet(int title, int message);

    void showAlertError(int title, String message);

    void showNoteList(Note note);
}
