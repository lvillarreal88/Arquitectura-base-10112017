package com.cosmo.arquitecturamvpbase.views.activities;

import com.cosmo.arquitecturamvpbase.model.User;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by leidyzulu on 13/10/17.
 */

public interface ILoginView extends IBaseView {
    void showDashBoard(User userLogin);

    void showDashBoard();
}
