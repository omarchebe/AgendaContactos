package com.agenda.omarche.agenda;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.agenda.omarche.agenda.util.ContactReceiver;

//public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    private ImageButton btnCrearcontacto;
    private ImageButton btnListacontactos;
    private ImageButton btnEliminarContacto;
    private ImageButton btnSincronizar;

    private CrearContactoFragment fragmentoCrear;
    private ListaContactosFragment fragmentoLista;


    private ActionBar actionBar;

    private ContactReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarActionBar();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        btnCrearcontacto = (ImageButton) findViewById(R.id.btnCrearContacto);
        btnCrearcontacto.setOnTouchListener(this);

        btnEliminarContacto = (ImageButton) findViewById(R.id.btnEliminarContacto);
        btnEliminarContacto.setOnTouchListener(this);

        btnListacontactos = (ImageButton) findViewById(R.id.btnListaContactos);
        btnListacontactos.setOnTouchListener(this);

        btnSincronizar = (ImageButton) findViewById(R.id.btnSincronizar);
        btnSincronizar.setOnTouchListener(this);

        cargarFragmento(getFragmentoLista());

    }

    private void cargarFragmento(Fragment fragmento) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor, fragmento);
        transaction.commit();
    }


    public CrearContactoFragment getFragmentoCrear() {
        if (fragmentoCrear == null) fragmentoCrear = new CrearContactoFragment();
        return fragmentoCrear;
    }

    public ListaContactosFragment getFragmentoLista() {
        if (fragmentoLista == null) fragmentoLista = new ListaContactosFragment();

        return fragmentoLista;
    }

    private void inicializarActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        ImageButton btn = (ImageButton) view;
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                btn.setColorFilter(R.color.entintado_oscuro);
                btn.invalidate();
                ;
                cambiarFragment(btn);
                break;

            case MotionEvent.ACTION_UP:
                btn.clearColorFilter();
                btn.invalidate();
                break;
        }
        return true;
    }

    private void cambiarFragment(View view) {
        switch (view.getId()) {
            case R.id.btnCrearContacto:
                cargarFragmento(getFragmentoCrear());
                break;

            case R.id.btnListaContactos:
                cargarFragmento((getFragmentoLista()));
                break;

            case R.id.btnEliminarContacto:

                break;
            case R.id.btnSincronizar:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new ContactReceiver();
        registerReceiver(receiver, new IntentFilter(ContactReceiver.FILTER_NAME));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}









