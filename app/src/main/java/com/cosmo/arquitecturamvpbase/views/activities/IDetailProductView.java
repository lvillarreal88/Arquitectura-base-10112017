package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by leidyzulu on 23/09/17.
 */

public interface IDetailProductView extends IBaseView {

    void showToast(int message);


    void showToast(String message);
}
