package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.helper.TypeDecryption;
import com.cosmo.arquitecturamvpbase.model.Note;
import com.cosmo.arquitecturamvpbase.services.IServices;

/**
 * Created by leonardo on 07/11/2017.
 */

public class NoteRepository implements INoteRepository {

    private IServices services;

    public NoteRepository() {
        ServicesFactory servicesFactory = new ServicesFactory(TypeDecryption.XML);
        this.services = (IServices) servicesFactory.getInstance(IServices.class);
    }

    @Override
    public Note getNote() {
        Note note = services.getNote();
        return note;
    }
}
