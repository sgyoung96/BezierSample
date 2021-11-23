package com.sgy.bezierv3.activity;

import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

import com.sgy.bezierv3.R;

public class BaseActivity extends AppCompatActivity {
    /**
     * statusbar height
     * @return
     */
    public float getStatusbarHeight() {
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return this.getResources().getDimensionPixelOffset(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * actionbar height
     * @return
     */
    public float getActionBarHeight() {
        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(R.attr.actionBarSize, tv, true);
        float actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);
        return actionBarHeight;
    }
}
