package com.example.greenify;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class ApiHelper {

    private static final String API_URL = "https://api.ejemplo.com/recomendaciones"; // Cambiar por la URL de la API real
    private RequestQueue requestQueue;

    public ApiHelper(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    // Método para hacer una solicitud GET y obtener las recomendaciones
    public void getRecomendaciones(final ApiCallback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                    }
                }
        );

        // Añadir la solicitud a la cola de peticiones
        requestQueue.add(jsonObjectRequest);
    }

    // Interface para manejar los resultados de la API
    public interface ApiCallback {
        void onSuccess(JSONObject response);
        void onError(VolleyError error);
    }
}
