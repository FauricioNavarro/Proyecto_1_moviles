package com.example.fauricio.proyecto_1_moviles.Vista.admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fauricio.proyecto_1_moviles.Controlador.Controlador;
import com.example.fauricio.proyecto_1_moviles.Controlador.listEmpresaAdapter;
import com.example.fauricio.proyecto_1_moviles.Modelo.Empresa;
import com.example.fauricio.proyecto_1_moviles.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EmpresaFragment extends Fragment {
    private View rootView;
    private FloatingActionButton nueva_empresa;
    private ListView empresas;
    private listEmpresaAdapter adapter;
    private ArrayList<Empresa> ArrayItem = null;
    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_empresa,container,false);
        empresas = rootView.findViewById(R.id.LV_empresas);
        nueva_empresa = rootView.findViewById(R.id.FB_agregar);
        ArrayItem = new ArrayList<>();
        nueva_empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),agregar_empresa.class);
                startActivity(intent);
            }
        });

        empresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Empresa temp = ArrayItem.get(i);
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_empresa",temp.getID_empresa());
                editor.commit();
                //Toast.makeText(getContext(),temp.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(),detalle_empresa.class);
                startActivity(intent);
            }
        });
        cargarLista(rootView.getContext());
        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cargarLista(Context context){
        try {
            JSONObject obj = Controlador.getInstance().getLista_empresa();
            JSONArray json_empresas = obj.getJSONArray("objects");
            for(int i=0;i<json_empresas.length();i++){
                JSONObject object = (JSONObject) json_empresas.getJSONObject(i);
                ArrayItem.add(new Empresa(object.getInt("id"),object.getString("nombre"),object.getString("descripcion")));
                adapter = new listEmpresaAdapter(ArrayItem, context);
                empresas.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
