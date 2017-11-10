package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.helper.TypeDecryption;
import com.cosmo.arquitecturamvpbase.model.BreakFastMenu;
import com.cosmo.arquitecturamvpbase.services.IServices;

/**
 * Created by leonardo on 08/11/2017.
 */

public class BreakFastMenuRepository implements IBreakFastMenuRepository {

    private IServices iServices;

    public BreakFastMenuRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.XML);
        this.iServices = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public BreakFastMenu getBreakFastMenu() {
        return iServices.getBreakFastMenu();
    }
}
