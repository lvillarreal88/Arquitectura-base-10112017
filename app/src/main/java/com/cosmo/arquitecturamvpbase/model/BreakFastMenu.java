package com.cosmo.arquitecturamvpbase.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardo on 08/11/2017.
 */

@Root(name = "breakfast_menu")
public class BreakFastMenu {

    @ElementList(entry = "food",inline = true)
    private ArrayList<Food> lstFood;

    public ArrayList<Food> getLstFood() {
        return lstFood;
    }

    public void setLstFood(ArrayList<Food> lstFood) {
        this.lstFood = lstFood;
    }
}
