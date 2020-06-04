package com.example.pmdm_aplicacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.pmdm_aplicacion.ui.login.LoginActivity;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MagicalPermissions permisos;
    private final static int tamaño = 50;
    private MagicalCamera camara;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.botonCamara).setOnClickListener(this);
        findViewById(R.id.botonGaleria).setOnClickListener(this);
        findViewById(R.id.botonCompartir).setOnClickListener(this);

        imagen = findViewById(R.id.imageView);
        
        String[] arrayPermisos = new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        permisos = new MagicalPermissions(this, arrayPermisos);
        camara = new MagicalCamera(this, tamaño, permisos);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.botonCamara) {
            camara.takePhoto();
        }
        else if (v.getId() == R.id.botonGaleria) {
            i = new Intent(this, GaleriaActivity.class);
            startActivity(i);
        }
        else if (v.getId() == R.id.botonCompartir) {
            i = new Intent(this, CompartirActivity.class);
            startActivity(i);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permis, @NonNull int[] grantResults) {
        Map<String, Boolean> map = permisos.permissionResult(requestCode, permis, grantResults);
        for (String permission : map.keySet()) {
            Log.d("PERMISSIONS", permission + " was: " + map.get(permission));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            camara.resultPhoto(requestCode, resultCode, data);
            imagen.setImageBitmap(camara.getPhoto());
            String path = camara.savePhotoInMemoryDevice(camara.getPhoto(), "myPhotoName", "myDirectoryName", MagicalCamera.JPEG, true);
            
            if (path != null) {
                Toast.makeText(MainActivity.this, "Foto guardada" + path, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Foto no guardada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
