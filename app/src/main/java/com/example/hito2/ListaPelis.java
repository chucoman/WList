package com.example.hito2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.hito2.entidades.ConexionSqliteHelper;
import com.example.hito2.entidades.Pelicula;
import com.example.hito2.utilidades.utilidades;

import java.util.ArrayList;

public class ListaPelis extends AppCompatActivity {
ArrayList<Pelicula> listPelicula;
RecyclerView recyclerViewPeliculas;

ConexionSqliteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pelis);

        conn = new ConexionSqliteHelper(getApplicationContext(),"bd_pelicula", null,1);

        listPelicula=new ArrayList<>();
        recyclerViewPeliculas= (RecyclerView) findViewById(R.id.my_recycler_pelis);
        recyclerViewPeliculas.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPeliculas();
        ListaPeliculasadapter adapter=new ListaPeliculasadapter(listPelicula);
        recyclerViewPeliculas.setAdapter(adapter);



    }
    private void consultarListaPeliculas(){
        SQLiteDatabase db=conn.getReadableDatabase();

        Pelicula pelicula = null;

        Cursor cursor= db.rawQuery("SELECT * FROM " + utilidades.TABLA_PELICULA, null);

        while(cursor.moveToNext()){

            pelicula=new Pelicula();

            pelicula.setId(cursor.getInt(0));
            pelicula.setNombre(cursor.getString(1));
            pelicula.setGenero(cursor.getString(2));
            pelicula.setYear(cursor.getInt(3));

            listPelicula.add(pelicula);
        }
    }
}