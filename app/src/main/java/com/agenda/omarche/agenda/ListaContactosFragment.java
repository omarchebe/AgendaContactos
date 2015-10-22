package com.agenda.omarche.agenda;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.agenda.omarche.agenda.util.ContactListAdapter;
import com.agenda.omarche.agenda.util.Contacto;

import java.util.ArrayList;

/**
 * Created by Omar Che on 21/10/2015.
 */
public class ListaContactosFragment extends Fragment {
    private ArrayAdapter<Contacto> adapter;

    private ListView contactsListView;
    private ContactReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_contactos,container,false);
        inicializarComponentes(rootView);
        setHasOptionsMenu(true);
        return rootView;
    }

    private void inicializarComponentes(View view) {
        contactsListView = (ListView) view.findViewById(R.id.listview);
        adapter = new ContactListAdapter(getActivity(),new ArrayList<Contacto>());
        adapter.setNotifyOnChange(true);
        contactsListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new ContactReceiver(adapter);
        getActivity().registerReceiver(receiver, new IntentFilter("listacontactos"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_eliminar_contacto: eliminarContacto(item); return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void eliminarContacto(MenuItem item) {
        SparseBooleanArray array = contactsListView.getCheckedItemPositions();
        ArrayList<Contacto> seleccion = new ArrayList<Contacto>();
        for(int i=0; i<array.size(); i++){
            int posicion = array.keyAt(i);
            if(array.valueAt(i)) seleccion.add(adapter.getItem(posicion));
            Intent intent = new Intent("listacontactos");
            intent.putExtra("operacion",ContactReceiver.CONTACTO_ELIMINADO);
            intent.putExtra("datos",seleccion);
            getActivity().sendBroadcast(intent);
            contactsListView.clearChoices();
        }
    }
}
