package com.example.fauricio.proyecto_1_moviles.Controlador;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class gestor {
    private static final gestor ourInstance = new gestor();
    private JSONObject lista_empresa;

    public static gestor getInstance() {
        return ourInstance;
    }

    private gestor() {
        try {
            String request_empresa = new DAO_api_empresa().execute("get").get();
            lista_empresa = new JSONObject(request_empresa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String registrar(String email,String password){
        try {
            String result = new DAO_api().execute("post",email,password).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String registrar_empresa(String nombre,String descripcion){
        try {
            String result = new DAO_api_empresa().execute("post",nombre,descripcion).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int login(String email,String password){
        try {
            String result = new DAO_api_empresa().execute("login",email,password).get();
            return 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JSONObject getLista_empresa() {
        return lista_empresa;
    }

    public void setLista_empresa(JSONObject lista_empresa) {
        this.lista_empresa = lista_empresa;
    }
}
