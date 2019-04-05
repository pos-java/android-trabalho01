package br.edu.utfpr.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.utfpr.trabalhofinal.Model.ListaProduto;

public class ListaProdutoDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "banco";
    private static final String TABLE_NAME = "listaProduto";
    private static final int VERSION = 3;

    public ListaProdutoDAO(Context c){
        super(c, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void inserir(ListaProduto listaProduto){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("descricao", listaProduto.getDescricao() );

        db.insert(TABLE_NAME, null, registro);
    }

}
