package com.example.pmdm_aplicacion.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.pmdm_aplicacion.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail;
    EditText editTextPass;
    Button boton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.usuario);
        editTextPass = (EditText) findViewById(R.id.pass);

        findViewById(R.id.boton).setOnClickListener(this);

        RequestOptions opciones = new RequestOptions().fitCenter();

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias), Context.MODE_PRIVATE);
        boolean isLogin = sharedPref.getBoolean(getString(R.string.preferencias_islogin), false);

        if(isLogin) {
            Intent i = new Intent(LoginActivity.this, com.example.pmdm_aplicacion.MainActivity.class);
            startActivity(i);
        }
    }

    public void doLogin(View view) {
        String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();

        if(email.equals("jonbeloki52@gmail.com") && pass.equals("contraseña")) {

            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preferencias_email), email);
            editor.putBoolean(getString(R.string.preferencias_islogin), true);
            editor.commit();

            Intent i = new Intent(LoginActivity.this,com.example.pmdm_aplicacion.MainActivity.class);
            startActivity(i);
        }

        else {
            Toast.makeText(this, "Email o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        doLogin(v);
    }
}
