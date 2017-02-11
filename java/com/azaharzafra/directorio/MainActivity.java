package com.azaharzafra.directorio;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MySQLiteHelper cdb = new MySQLiteHelper(this, "DB", null, 1);
    private EditText etNombreM;
    private Button bCerrarM, bAddM, bBuscarM;
    private ListView lvM;
    private ArrayAdapter aaM;


    private void hideSoftKeyboard ( View v) {
        InputMethodManager inputMethodManager ;
        inputMethodManager = ( InputMethodManager ) getSystemService ( INPUT_METHOD_SERVICE );
        inputMethodManager . hideSoftInputFromWindow (v. getWindowToken () , 0);
    }
    public void onClick(View view) {
        hideSoftKeyboard (view);
        switch (view.getId()) {
            case R.id.bBuscarMain: bContacto(); break;
            case R.id.bCerrarMain: cerrarApp(); break;
            case R.id.bAddMain: addContacto(); break;
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreM = (EditText) findViewById(R.id.etNombreMain);
        bAddM = (Button) findViewById(R.id.bAddMain);
        bCerrarM = (Button) findViewById(R.id.bCerrarMain);
        bBuscarM = (Button) findViewById(R.id.bBuscarMain);
        lvM = (ListView) findViewById(R.id.lvMain);
        hideSoftKeyboard (etNombreM);
    }

    public void bContacto() {

        String nombre = etNombreM.getText().toString().toLowerCase();
        List<Contacto> lista= new ArrayList<>();
        aaM = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvM.setAdapter(aaM);

        if (nombre.isEmpty()) {
            Toast.makeText(this, R.string.NombreNoValido, Toast.LENGTH_SHORT).show();
        } else {
            lista  = cdb.buscarContacto(nombre);
            int total = lista.size();
            if (total==0) {
                Toast.makeText(this, "No encotrado", Toast.LENGTH_SHORT).show();
                lista.clear();
                aaM = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
                lvM.setAdapter(aaM);
            }else{
                for (int i = 0; i < total; i++) {
                    String name = lista.get(i).getName();
                    Integer num = lista.get(i).getNumber();
                    final String n = num.toString();
                    aaM = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
                    lvM.setAdapter(aaM);

                    lvM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String numero = "tel:"+n;
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse(numero));
                            startActivity(callIntent);
                        }
                    });
                }
            }
            etNombreM.setText("");
        }
        hideSoftKeyboard (etNombreM);
    }

    private void infoDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.alerta);
        builder.setIcon(android.R.drawable.ic_dialog_info );
        builder.setPositiveButton (R.string.ok, new DialogInterface.OnClickListener () {
            public void onClick (DialogInterface dialog, int id) {
                dialog.cancel();
                System.exit(0);
            }
        });
        builder.show();
    }

    public void cerrarApp(){
        infoDialog ();
       // System.exit(0);
    }

    public void addContacto(){

        Intent intent = new Intent(this, AddEntryActivity.class);
        startActivity(intent);
    }
}

