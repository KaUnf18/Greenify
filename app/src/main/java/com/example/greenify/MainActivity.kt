package com.example.greenify

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.HurlStack
import okhttp3.OkHttpClient
import javax.net.ssl.SSLContext
import java.security.SecureRandom
import javax.net.ssl.X509TrustManager
import java.security.cert.X509Certificate
import javax.net.ssl.SSLSocketFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración del NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // Vincula el NavController a la Toolbar (opcional)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configura el NavController con el toolbar para el manejo de la barra de navegación
        NavigationUI.setupWithNavController(toolbar, navController)

        // Opcional: Habilitar el botón de retroceso en el Toolbar si es necesario
        NavigationUI.setupActionBarWithNavController(this, navController)

        // Iniciar la función para mostrar recomendaciones predeterminadas
        mostrarRecomendacionesPredeterminadas()
    }

    // Función para mostrar recomendaciones predeterminadas
    private fun mostrarRecomendacionesPredeterminadas() {
        val textViewRecomendaciones: TextView = findViewById(R.id.textViewRecomendaciones)

        // Recomendaciones predeterminadas
        val recomendacion1 = "Recomendación 1: Apaga las luces cuando no las necesites."
        val recomendacion2 = "Recomendación 2: Utiliza transporte público o bicicleta."
        val recomendacion3 = "Recomendación 3: Planta más árboles para mejorar el aire."

        // Muestra las recomendaciones en el TextView
        textViewRecomendaciones.text = "$recomendacion1\n$recomendacion2\n$recomendacion3"
    }

    // Habilita el botón de retroceso en el ActionBar si estás usando Fragmentos
    override fun onSupportNavigateUp(): Boolean {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
