package com.aks.voyagermachinetest.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aks.otrez_machine_test.R;
import com.aks.otrez_machine_test.common.CommonToasts;
import com.aks.otrez_machine_test.database.RegistrationHelper;
import com.aks.otrez_machine_test.home.HomeListingActivity;
import com.aks.otrez_machine_test.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsrName;
    EditText etPassword;
    Button btLogin;
    Context context;
    CommonToasts commonToasts;
    TextView tvNewUser;
    RegistrationHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_login);
        commonToasts = new CommonToasts(context);
        helper = new RegistrationHelper(context);
        initIds();
        setClickListeners();
        if (getSharedPreferences("otrez", MODE_PRIVATE).getBoolean("login", false)) {
            startActivity(new Intent(context, HomeListingActivity.class));
        }
    }

    private void setClickListeners() {
        btLogin.setOnClickListener(v -> {
            if (etUsrName.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals("")) {
                commonToasts.setToastMessage(CommonToasts.USER_NAME_AND_PASSWORD_CANNOT_BE_EMPTY);
            } else {
                if (helper.ifUserIsInDb(etUsrName.getText().toString().trim(), etPassword.getText().toString().trim())) {
                    getSharedPreferences("otrez", MODE_PRIVATE).edit().putBoolean("login", true).apply();
                    startActivity(new Intent(context, HomeListingActivity.class));
                }
            }
        });
        tvNewUser.setOnClickListener(v -> startActivity(new Intent(context, RegistrationActivity.class)));
    }

    private void initIds() {
        etUsrName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvNewUser = findViewById(R.id.tvNewUser);
    }
}