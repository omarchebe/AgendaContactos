package com.agenda.omarche.agenda;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
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

import com.agenda.omarche.agenda.model.Contacto;
import com.agenda.omarche.agenda.util.ContactReceiver;
import com.agenda.omarche.agenda.util.TextChangedListener;

/**
 * Created by Omar Che on 21/10/2015.
 */
public class CrearContactoFragment extends Fragment implements View.OnClickListener {

    private EditText txtNombre;
    private EditText txtEmail;
    private EditText txtTelefono;
    private EditText txtDireccion;

    private Button btnGuardar;
    private Button btnCancelar;

    private ImageView imgViewImage;

    private int request_code = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewroot = inflater.inflate(R.layout.fragment_crear_contacto, container, false);
        inicializarComponentesUI(viewroot);
        return viewroot;
    }

    private void inicializarComponentesUI(View view) {
        txtNombre = (EditText) view.findViewById(R.id.txtNombre);
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence secuencia, int start, int before, int count) {
                btnGuardar.setEnabled(!secuencia.toString().trim().isEmpty());
            }
        });

        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtDireccion = (EditText) view.findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) view.findViewById(R.id.txtTelefono);

        btnGuardar = (Button) view.findViewById(R.id.btnGuardar);

        imgViewImage = (ImageView) view.findViewById(R.id.imgContacto);
        imgViewImage.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGuardar:
                guardarContacto(view);
                break;
            case R.id.btnCancelar:
                limpiarCampos();
                break;
            case R.id.imgContacto:
                cargarImagen();
                break;
        }
    }

    private void cargarImagen() {
        Intent intent = null;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(intent, request_code);
    }

    private void guardarContacto(View view) {
        agregarContacto(
                txtNombre.getText().toString(),
                txtEmail.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                String.valueOf(imgViewImage.getTag())
        );
        String msg = String.format("%s ha sido agregado a la lista", txtNombre.getText());
        Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void agregarContacto(String nombre, String direccion, String email, String telefono, String imageUri) {
        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        contacto.setDireccion(direccion);
        contacto.setCorreoElectronico(email);
        contacto.setTelefono(telefono);
        contacto.setImagenUri(imageUri);


        Intent intent = new Intent(ContactReceiver.FILTER_NAME);
        intent.putExtra("operacion", ContactReceiver.CONTACTO_AGREGADO);
        intent.putExtra("datos", contacto);
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
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == request_code) {
            Uri uri = data.getData();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                int takeFlags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                ContentResolver resolver = getActivity().getContentResolver();
                resolver.takePersistableUriPermission(uri, takeFlags);
            }
            imgViewImage.setImageURI(uri);
            imgViewImage.setTag(uri);
        }
    }
}
