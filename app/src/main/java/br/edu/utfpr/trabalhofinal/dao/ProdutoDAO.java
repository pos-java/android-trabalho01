package br.edu.utfpr.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.utfpr.trabalhofinal.Model.Produto;

public class ProdutoDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "banco";
    private static final String TABLE_NAME = "produto";
    private static final int VERSION = 4;

    public ProdutoDAO(Context c){
        super(c, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, qtde INTEGER, valor REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void incluir(Produto produto){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nome", produto.getNome() );
        registro.put("qtde", produto.getQtde() );
        registro.put("valor", produto.getValor() );
//        registro.put("valor", 10 );

        db.insert(TABLE_NAME, null, registro);
    }

    public Cursor listar(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.query(TABLE_NAME, null, null, null, null, null, null);

        return registros;
    }

    public void limpar(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+ TABLE_NAME);
//        db.delete( TABLE_NAME, "_id > ?", new String[] { "0" } );
    }

}
