package com.agenda.omarche.agenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.agenda.omarche.agenda.util.ContactListAdapter;
import com.agenda.omarche.agenda.util.Contacto;
import com.agenda.omarche.agenda.util.TextChangedListener;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText txtNombre;
    private EditText txtEmail;
    private EditText txtTelefono;
    private EditText txtDireccion;

    private Button btnAgregar;


    private ImageView imgViewImage;

    private ArrayAdapter<Contacto> adapter;
    private ListView contactListViiew;

    private int request_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarListView();
        inicializarTabs();
    }

    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec  spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Lista");
        tabHost.addTab(spec);

    }

    private void inicializarComponentesUI() {
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence secuencia, int start, int before, int count) {
                btnAgregar.setEnabled(!secuencia.toString().trim().isEmpty());
            }
        });

        txtEmail = (EditText) findViewById(R.id.txtCorreoElectronico);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);

        contactListViiew = (ListView) findViewById(R.id.listView);

        imgViewImage = (ImageView) findViewById(R.id.imageView);


    }

    public void onClick(View view) {

        agregarContacto(
                txtNombre.getText().toString(),
                txtEmail.getText().toString(),
                txtTelefono.getText().toString(),
                txtDireccion.getText().toString(),
                (Uri)imgViewImage.getTag()
        );
        String msg =  String.format("%s ha sido agregado a la lista",txtNombre.getText());
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private void inicializarListView() {
        adapter  = new ContactListAdapter(this,new ArrayList<Contacto>());
        contactListViiew.setAdapter(adapter);

    }

    private void agregarContacto(String nombre, String direccion, String email, String telefono, Uri imageUri) {
        Contacto contacto = new Contacto(nombre,email,telefono,direccion,imageUri);
        adapter.add(contacto);
    }


    private void limpiarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtDireccion.getText().clear();
        txtEmail.getText().clear();

        imgViewImage.setImageResource(R.drawable.usuario);

        txtNombre.requestFocus();
    }

    public void onImgClick(View view) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == request_code){
            imgViewImage.setImageURI(data.getData());
            imgViewImage.setTag(data.getData());

        }
    }
}
