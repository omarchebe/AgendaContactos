package com.agenda.omarche.agenda.util;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agenda.omarche.agenda.R;
import com.agenda.omarche.agenda.entity.Contacto;

import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contacto> {

    private Activity ctx;

    public ContactListAdapter(Activity context, List<Contacto> contactos){
        super(context, R.layout.listview_item,contactos);
        this.ctx =context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null)
            view = ctx.getLayoutInflater().inflate(R.layout.listview_item,parent,false);
        Contacto actual = this.getItem(position);
        inicializarCampos(view, actual);
        return view;
    }

    private void inicializarCampos(View view, Contacto actual) {
        TextView textView = (TextView) view.findViewById(R.id.lblNombre);
        textView.setText(actual.getNombre());

        textView = (TextView) view.findViewById(R.id.lblTelefono);
        textView.setText(actual.getTelefono());

        textView = (TextView) view.findViewById(R.id.lblEmail);
        textView.setText(actual.getCorreoElectronico());

        textView = (TextView) view.findViewById(R.id.lblDireccion);
        textView.setText(actual.getDireccion());

        ImageView imageView = (ImageView) view.findViewById(R.id.ivContactImage);
        imageView.setImageURI(Uri.parse(actual.getImagenUri()));

    }


}
