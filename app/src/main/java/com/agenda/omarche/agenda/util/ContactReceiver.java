package com.agenda.omarche.agenda.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.agenda.omarche.agenda.entity.Contacto;

import java.util.ArrayList;

/**
 * Created by Omar Che on 21/10/2015.
 */
public class ContactReceiver extends BroadcastReceiver {

    public static final int CONTACTO_AGREGADO = 1;
    public static final int CONTACTO_ELIMINADO = 2;
    public static final int CONTACTO_ACTUALIZADO = 3;

    public static final String FILTER_NAME = "listacontactos";


    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion", -1);
        switch (operacion) {
            case CONTACTO_AGREGADO:
                agregarContacto(intent);
                break;
            case CONTACTO_ELIMINADO:
                eliminarContacto(intent);
                break;
            case CONTACTO_ACTUALIZADO:
                actualizarContacto(intent);
                break;

        }
    }

    private void agregarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        //adapter.add(contacto);
    }

    private void eliminarContacto(Intent intent) {
        ArrayList<Contacto> lista = (ArrayList<Contacto>) intent.getSerializableExtra("datos");
       //for (Contacto c : lista) {
            //adapter.remove(c);
        //}
    }

    private void actualizarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        //int posicion = adapter.getPosition(contacto);
        //adapter.insert(contacto, posicion);

    }


}
