package edu.intech.mediatech.models.interfaces;

import android.content.Context;
import android.content.SharedPreferences;

public class Tools {

    Context context;
    private SharedPreferences sharedPref;

    public Tools(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    public SharedPreferences getSharedPref() {
        sharedPref = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        return this.sharedPref;
    }
}
