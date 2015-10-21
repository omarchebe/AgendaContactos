package com.agenda.omarche.agenda;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agenda.omarche.agenda.util.Contacto;
import com.agenda.omarche.agenda.util.TextChangedListener;

/**
 * Created by Omar Che on 21/10/2015.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener{

    private EditText txtNombre;
    private EditText txtEmail;
    private EditText txtTelefono;
    private EditText txtDireccion;

    private Button btnAgregar;

    private ImageView imgViewImage;

    private int request_code = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewroot  = inflater.inflate(R.layout.fragment_crear_contacto, container,false);
        inicializarComponentesUI(viewroot);
        return viewroot;
    }

    private void inicializarComponentesUI(View view) {
        txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence secuencia, int start, int before, int count) {
                btnAgregar.setEnabled(!secuencia.toString().trim().isEmpty());
            }
        });

        txtEmail = (EditText) view.findViewById(R.id.txtCorreoElectronico);
        txtDireccion = (EditText) view.findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) view.findViewById(R.id.txtTelefono);

        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);

        imgViewImage = (ImageView) view.findViewById(R.id.imageView);
        imgViewImage.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == btnAgregar){

            agregarContacto(
                    txtNombre.getText().toString(),
                    txtEmail.getText().toString(),
                    txtTelefono.getText().toString(),
                    txtDireccion.getText().toString(),
                    String.valueOf(imgViewImage.getTag())
            );
            String msg =  String.format("%s ha sido agregado a la lista",txtNombre.getText());
            Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }else if(view== imgViewImage){
            Intent intent = null;
            if(Build.VERSION.SDK_INT <19){
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
            }
            else {
                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
            }
            intent.setType("image/*");
            startActivityForResult(intent, request_code);
        }
    }

    private void agregarContacto(String nombre, String direccion, String email, String telefono, String imageUri) {
        Contacto contacto = new Contacto(nombre,email,telefono,direccion,imageUri);
        Intent intent = new Intent("listacontactos");
        intent.putExtra("operacion",ContactReceiver.CONTACTO_AGREGADO);
        intent.putExtra("datos",contacto);
        getActivity().sendBroadcast(intent);
    }


    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtDireccion.getText().clear();
        txtEmail.getText().clear();
        imgViewImage.setImageResource(R.drawable.usuario);
        txtNombre.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == request_code)
        {
            imgViewImage.setImageURI(data.getData());
            imgViewImage.setTag(data.getData());
        }
    }
}
