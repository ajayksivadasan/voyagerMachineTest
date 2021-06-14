package com.aks.voyagermachinetest.common;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.aks.otrez_machine_test.R;


public class CommonToasts {
    public static final String SOMETHING_ERROR_OCCURRED = "SOMETHING ERROR OCCURRED";
    public static final String IMAGE_NOT_AVAILABLE = "Image NOT available";
    public static final String INTERNET_NOT_AVAILABLE = "Internet Not Available";
    public static final String SUCCESSFULLY_UPDATED = "Successfully Updated";
    public static final String NOTHING_TO_UPLOAD = "Nothing To Upload";
    public static final String CANNOT_BE_EMPTY = "Cannot Be Empty";
    public static final String USER_NAME_AND_PASSWORD_CANNOT_BE_EMPTY = "User Name and Password Cannot be Empty";
    final Context context;

    public CommonToasts(Context context) {
        this.context = context;
    }

    public void setToastMessage(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.homegrey));
        view.setBackground(ContextCompat.getDrawable(context, R.drawable.background_rounded_corner_basic_clor_drawable_shape));
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(ContextCompat.getColor(context, R.color.white));
        toast.show();
    }
}
