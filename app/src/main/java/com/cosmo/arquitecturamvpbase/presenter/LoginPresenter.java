package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.User;
import com.cosmo.arquitecturamvpbase.repository.IProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.ILoginView;

/**
 * Created by leidyzulu on 13/10/17.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private IProductRepository productRepository;

    public LoginPresenter(IProductRepository productRepository) {
     this.productRepository = productRepository;
    }

    public void login(String user, String password) {
        if(getValidateInternet().isConnected()){
            createThreadLogin(user, password);
        }else{
            //TODO
        }
    }

    private void createThreadLogin(final String user, final String password) {
        getView().showProgress(R.string.loading_message);
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                loginInRepository(user, password);
            }
        });
        thread.start();
    }

    private void loginInRepository(String user, String password) {

        try {
            User userLogin = productRepository.login(user, password);
            getView().showDashBoard(userLogin);


        }catch (RepositoryError repositoryError){
            //TODO
        }finally {
            getView().hideProgress();
        }



    }

    public void autoLogin(String token) {
        if(getValidateInternet().isConnected()){
            createThreadautoLogin(token);
        }else{
            //TODO
        }
    }

    private void createThreadautoLogin(final String token) {
        getView().showProgress(R.string.loading_message);
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                autoLoginInRepository(token);
            }
        });
        thread.start();
    }

    private void autoLoginInRepository(String token) {

        try {
            productRepository.autologin(token);
            getView().showDashBoard();

        }catch (RepositoryError repositoryError){
            //TODO
        }finally {
            getView().hideProgress();
        }

    }
}
