package com.aks.voyagermachinetest.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.aks.voyagermachinetest.R;
import com.aks.voyagermachinetest.common.CommonToasts;
import com.aks.voyagermachinetest.database.RegistrationHelper;
import com.aks.voyagermachinetest.login.LoginActivity;


public class RegistrationActivity extends AppCompatActivity {
    EditText etName;
    EditText etEmail;
    EditText etPhoneNumber;
    EditText etUserName;
    EditText etPasswordReg;
    EditText etConfirmPassword;
    Button btSubmit;
    Context context;
    ModelRegistration registration;
    CommonToasts commonToasts;
    RegistrationHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_registration);
        initIds();
        setClickListeners();
        registration = new ModelRegistration();
        commonToasts = new CommonToasts(context);
        helper = new RegistrationHelper(context);
    }

    private void initIds() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etUserName = findViewById(R.id.etUserName);
        etPasswordReg = findViewById(R.id.etPasswordReg);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btSubmit = findViewById(R.id.btSubmit);
    }

    private void setClickListeners() {
        btSubmit.setOnClickListener(v -> {
            if (etName.getText().toString().equals("")) {
                etName.setError(CommonToasts.CANNOT_BE_EMPTY);
            } else if (etEmail.getText().toString().equals("")) {
                etEmail.setError("");
            } else if (etPhoneNumber.getText().toString().equals("")) {
                etPhoneNumber.setError(CommonToasts.CANNOT_BE_EMPTY);
            } else if (etPasswordReg.getText().toString().equals("")) {
                etPasswordReg.setError(CommonToasts.CANNOT_BE_EMPTY);
            } else if (!etPasswordReg.getText().toString().equals(etConfirmPassword.getText().toString())) {
                etPasswordReg.setText("");
                etConfirmPassword.setText("");
                etPasswordReg.setError(CommonToasts.CANNOT_BE_EMPTY);
                etConfirmPassword.setError(CommonToasts.CANNOT_BE_EMPTY);
            } else {
                String name;
                String email;
                String phone;
                String uName;
                String passWord;
                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                phone = etPhoneNumber.getText().toString().trim();
                uName = etUserName.getText().toString().trim();
                passWord = etConfirmPassword.getText().toString().trim();
                registration.setEmail(email);
                registration.setName(name);
                registration.setPhone(phone);
                registration.setuName(uName);
                registration.setPassWord(passWord);
                helper.insertIntoUsers(registration);
                commonToasts.setToastMessage("Registration Successful");
                startActivity(new Intent(context, LoginActivity.class));
            }
        });
    }
}