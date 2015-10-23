package com.agenda.omarche.agenda.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Omar Che on 23/10/2015.
 */
public class MenuBarActionReceiver extends BroadcastReceiver {

    public static final int ELIMINAR_CONTACTO = 1;
    public static final int SINCRONIZAR_CONTACTOS = 2;

    public static final String FILTER_NAME = "menu_bar_action";

    private final MenuBarActionListener listener;

    public MenuBarActionReceiver(MenuBarActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion", -1);
        switch (operacion) {
            case ELIMINAR_CONTACTO:
                listener.eliminarContactos();
                break;
            case SINCRONIZAR_CONTACTOS:
                listener.sincornizarContactos();
                break;
        }
    }


    public static interface MenuBarActionListener {
        public void eliminarContactos();

        public void sincornizarContactos();
    }


}
