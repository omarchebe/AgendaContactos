package com.agenda.omarche.agenda.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.agenda.omarche.agenda.model.Contacto;
import com.agenda.omarche.agenda.model.ContactoDao;
import com.agenda.omarche.agenda.model.DaoMaster;
import com.agenda.omarche.agenda.model.DaoSession;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Omar Che on 21/10/2015.
 */
public class ContactReceiver extends BroadcastReceiver {


    public static final int CONTACTO_AGREGADO = 1;
    public static final int CONTACTO_ELIMINADO = 2;
    public static final int CONTACTO_ACTUALIZADO = 3;

    public static final String FILTER_NAME = "listacontactos";


    public DaoMaster daoMAster;
    public DaoSession daoSession;
    public SQLiteDatabase db;

    private ContactoDao contactoDao;

    private  AppCompatActivity activity;

    public ContactReceiver(AppCompatActivity activity) {
        this.activity = activity;
        createSessionDao();
    }


    public ContactReceiver() {
        createSessionDao();
    }

    private void createSessionDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(activity, "contacto-db", null);
        db = helper.getWritableDatabase();
        daoMAster = new DaoMaster(db);
        daoSession = daoMAster.newSession();
    }
    //private final OrmLiteBaseActivity<DatabaseHelper> activity;
/*
    public ContactReceiver(OrmLiteBaseActivity<DatabaseHelper> activity) {
        this.activity = activity;
    }
    */


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

        contactoDao = daoSession.getContactoDao();
        contactoDao.insert(contacto);
        /*
        if(activity != null) {
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Contacto,Integer> dao = helper.getContactRuntimeException();
            dao.create(contacto);
        }
        */
        //adapter.add(contacto);
    }

    private void eliminarContacto(Intent intent) {
        ArrayList<Contacto> lista = (ArrayList<Contacto>) intent.getSerializableExtra("datos");
        /*
        if(activity != null) {
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Contacto,Integer> dao = helper.getContactRuntimeException();
            dao.delete(lista);
        }
        */
        //for (Contacto c : lista) {
        //adapter.remove(c);
        //}
    }

    private void actualizarContacto(Intent intent) {
        Contacto contacto = (Contacto) intent.getSerializableExtra("datos");
        /*
        if(activity != null) {
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Contacto,Integer> dao = helper.getContactRuntimeException();
            dao.update(contacto);
        }
        */
        //int posicion = adapter.getPosition(contacto);
        //adapter.insert(contacto, posicion);

    }

    public List<Contacto> getcontactos() {
        List<Contacto> contactos = new ArrayList<Contacto>();
        List contactosResult = contactoDao.queryBuilder()
                .list();
        contactos = contactosResult;
        return contactos;

    }
}
