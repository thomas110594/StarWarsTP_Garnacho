package com.example.thomasgarnacho.tpfinal_garnacho.data.models;

import android.text.TextUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomasgarnacho on 11/01/2018.
 */

public class SWModelList implements Serializable {

    public int count;
    public String next;
    public String previous;
    public List<SWPeople> results;

    public boolean hasMore() {
        return !TextUtils.isEmpty(next);
    }
}
