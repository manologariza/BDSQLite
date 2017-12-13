package com.example.manolo.bdsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAlta;
    EditText etCodigo, etDescripcion, etPrecio;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        bd = admin.getWritableDatabase();

        btnAlta=(Button)findViewById(R.id.btnAlta);
        etCodigo=(EditText)findViewById(R.id.etCodigo);
        etDescripcion=(EditText)findViewById(R.id.etDescripcion);
        etPrecio=(EditText)findViewById(R.id.etPrecio);

        btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cod = etCodigo.getText().toString();
                String descri = etDescripcion.getText().toString();
                String pre = etPrecio.getText().toString();
                ContentValues registro = new ContentValues();
                registro.put("codigo", cod);
                registro.put("descripcion", descri);
                registro.put("precio", pre);
                bd.insert("articulos", null, registro);
                bd.close();
                etCodigo.setText("");
                etDescripcion.setText("");
                etPrecio.setText("");
                Toast.makeText(getApplicationContext(), "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
