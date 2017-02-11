package com.azaharzafra.directorio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEntryActivity extends AppCompatActivity {

    MySQLiteHelper cdb = new MySQLiteHelper(this, "DB", null, 1);
    private EditText etNombreA, etNumeroA;
    private Button bVolverA, bAddA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        etNombreA = (EditText) findViewById(R.id.etNombreAdd);
        etNumeroA = (EditText) findViewById(R.id.etNumeroAdd);
        bAddA = (Button) findViewById(R.id.bAddAdd);
        bVolverA = (Button) findViewById(R.id.bVolverAdd);

    }
    private void hideSoftKeyboard ( View v) {
        InputMethodManager inputMethodManager ;
        inputMethodManager = ( InputMethodManager ) getSystemService ( INPUT_METHOD_SERVICE );
        inputMethodManager . hideSoftInputFromWindow (v. getWindowToken () , 0);
    }

    public void onClickAdd(View view) {
        hideSoftKeyboard (view);
        switch (view.getId()) {
            case R.id.bVolverAdd: volver(); break;
            case R.id.bAddAdd: add(); break;
        }
    }

    private void volver(){

        this.finish();

    }
    private void add(){

        String nombre = etNombreA.getText().toString().toLowerCase();
        Integer numero =  Integer.parseInt(etNumeroA.getText().toString().toLowerCase());

        cdb.insertarContacto(nombre, numero);
        Toast.makeText(this, R.string.NombreNuevo, Toast.LENGTH_SHORT).show();
    }
}
