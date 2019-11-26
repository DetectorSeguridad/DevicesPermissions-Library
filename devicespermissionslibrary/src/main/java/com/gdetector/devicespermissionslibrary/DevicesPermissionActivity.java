package com.gdetector.devicespermissionslibrary;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.os.Build;

import java.util.LinkedHashMap;
import java.util.List;


public class DevicesPermissionActivity {

    private static LinkedHashMap<String, List<String>> expandableListDetail;
    private static ObservableBoolean listFilled = new ObservableBoolean(false);
    private Context ctx;
    private Boolean needsCheckPermission = false;


    /**
     * Este constructor será necesario en caso de que queramos controlar si nuestro dispositivo
     * necesita alguna configuración para las notificaciones
     * @param ctx
     */
    public DevicesPermissionActivity(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Constructor con la lista de instrucciones que se van a mostrar en el expandable list
     * de la vista. Este constructor será necesario en caso de querer acceder de manera 'manual',
     * es decir, mediante un botón por ejemplo, a una instancia de la lista con las instrucciones
     * @param expandableListDetail
     */
    public DevicesPermissionActivity(LinkedHashMap<String, List<String>> expandableListDetail) {
        DevicesPermissionActivity.expandableListDetail = expandableListDetail;

        listFilled.set(!listFilled.get());
    }

    /**
     * Función que permite controlar si necesitamos alguna configuración en concreto para las notificaciones
     * @return true si necesitamos la configuración
     */
    public Boolean CheckConfiguration(){
        String currentModel = GetDeviceName();
        for (DevicesModel model : DevicesModel.values()) {
            if (currentModel.contains(model.toString())) {
                needsCheckPermission = true;
                break;
            }
        }
        return needsCheckPermission;
    }

    /**
     * Comprueba la marca y el modelo del dispositivo
     * @return string con la marca y el model del dispositivo (Ej: HUAWEI LITE)
     */
    private String GetDeviceName()
    {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.BRAND;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return Capitalize(model);
        } else {
            return Capitalize(manufacturer) + " " + model;
        }
    }

    /**
     * Conversión a mayusculas de un string
     * @param s
     * @return s en mayusculas
     */
    private String Capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        return s.toUpperCase();
    }

    /**
     * Get del HashMap que contiene las instrucciones para el terminal. Será usado para la creado de la
     * vista mediante código
     * @return LinkedHashMap<String, List<String>>
     */
    static LinkedHashMap<String, List<String>> getExpandableListDetail() {
        return expandableListDetail;
    }

    /**
     * Get del Observable que nos indica si la lista esta llena o no (si tiene algún dato). De esta forma podremos controlar
     * los tiempos de carga al obtener estos datos de un servicio en segundo plano que puede tardar un tiempo.
     * @return listFilled
     */
    static ObservableBoolean getListFilled() {
        return listFilled;
    }



}
