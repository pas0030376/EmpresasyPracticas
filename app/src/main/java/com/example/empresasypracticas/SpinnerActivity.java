package com.example.empresasypracticas;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Vicky on 17/04/2018.
 */

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}