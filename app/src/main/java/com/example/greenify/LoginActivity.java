package com.example.greenify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Configuración del botón de login
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            // Comparar las credenciales ingresadas con las definidas en strings.xml
            String defaultUsername = getString(R.string.default_username);
            String defaultPassword = getString(R.string.default_password);

            if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
                // Login exitoso, navegar a la actividad principal
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finalizar la actividad de login para que no se quede en el historial
            } else {
                // Credenciales incorrectas
                if (!username.equals(defaultUsername)) {
                    edtUsername.setError("Usuario no encontrado");
                }
                if (!password.equals(defaultPassword)) {
                    edtPassword.setError("Contraseña incorrecta");
                }
            }
        });
    }
}
