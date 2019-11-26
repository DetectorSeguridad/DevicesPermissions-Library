package com.gdetector.devicespermissionslibrary;

import android.content.Context;
import android.databinding.Observable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Creación de la vista de la lista
 *
 * expandableListView       : Instancia de la clase de la lista donde se van a representar los datos
 * expandableListAdapter    : Instancia del adapter (CustomExpandableListAdapter)
 * expandableListTitle      : Titulo de cada grupo de la lista
 * expandableListDetail     : Datos correspondientes a cada grupo
 * listFilledCallback       : Callback que permite controlar el estado de la lista en la actividad DevicesPermissionActivity
 */
public class PermissionsList extends RelativeLayout {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;

    Observable.OnPropertyChangedCallback listFilledCallback;

    public PermissionsList(Context context) {
        super(context);
    }

    public PermissionsList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PermissionsList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * Inicialización de la vista. Generamos el callback para rellenar el expandablelistview en caso
     * de que recibamos datos de la actividad.
     * @param context
     * @param attrs
     */
    private void init(final Context context, AttributeSet attrs) {
        final View rootView = View.inflate(context, R.layout.permissions_list, this);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);

        listFilledCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                updateList(context);
                RemoveSessionCallbacks();
            }
        };

        DevicesPermissionActivity.getListFilled().addOnPropertyChangedCallback(listFilledCallback);
    }

    /**
     * Actualizamos la lista para mostrar los datos
     * @param ctx
     */
    private void updateList(Context ctx) {
        expandableListDetail = DevicesPermissionActivity.getExpandableListDetail();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(ctx, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        RemoveSessionCallbacks();
    }

    /**
     * Eliminamos el listener del callback
     */
    public void RemoveSessionCallbacks()
    {

        if(listFilledCallback != null)
        {
            DevicesPermissionActivity.getListFilled().removeOnPropertyChangedCallback(listFilledCallback);
            listFilledCallback = null;
        }

    }
}