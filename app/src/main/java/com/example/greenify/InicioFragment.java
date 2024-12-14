package com.example.greenify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.VolleyError;
import org.json.JSONObject;

/**
 * InicioFragment: Fragmento que representa la pantalla de inicio de la app.
 */
public class InicioFragment extends Fragment {

    private TextView tvRecomendaciones;
    private ApiHelper apiHelper;

    public InicioFragment() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiHelper = new ApiHelper(getContext()); // Inicializamos ApiHelper para hacer solicitudes
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializamos el TextView para mostrar las recomendaciones
        tvRecomendaciones = view.findViewById(R.id.tvRecomendaciones);

        // Configuración del botón para navegar al PerfilFragment
        view.findViewById(R.id.button_to_perfil).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.perfilFragment);
        });

        // Obtener las recomendaciones desde la API
        apiHelper.getRecomendaciones(new ApiHelper.ApiCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                // Mostrar las recomendaciones obtenidas
                try {
                    // Suponiendo que la respuesta contiene un campo "recomendaciones"
                    String recomendaciones = response.getString("recomendaciones");
                    tvRecomendaciones.setText(recomendaciones);
                } catch (Exception e) {
                    tvRecomendaciones.setText("Error al procesar los datos");
                }
            }

            @Override
            public void onError(VolleyError error) {
                // Mostrar mensaje de error si la API falla
                tvRecomendaciones.setText("Error al obtener recomendaciones");
            }
        });
    }
}
