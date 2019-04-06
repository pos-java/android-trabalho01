package br.edu.utfpr.trabalhofinal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.edu.utfpr.trabalhofinal.dao.ListaProdutoDAO;

public class ListaProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ListView listaDeCursos = findViewById(R.id.lvProdutos);

        String[] dados = new String[] { "Cupcake", "Donut", "Eclair" };

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);




        //Buscar os produtos
        ListaProdutoDAO listaProdutos = new ListaProdutoDAO( this );
        listaProdutos.listar();

        Cursor registros = listaProdutos.listar();

        String campos[] = { "descricao" };

        int camposTela[] = { android.R.id.text1 };

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, "descricao", registros, camposTela);
        SimpleCursorAdapter adpater = new SimpleCursorAdapter( this, android.R.layout.simple_list_item_1, registros, campos, camposTela );


        listaDeCursos.setAdapter(  adpater );


        /*
        Cursor registros = banco.listar();

        ListView lvCadastro = new ListView( this );

        String campos[] = { "nome" };

        int idTela = android.R.layout.simple_list_item_1;

        int camposTela[] = { android.R.id.text1 };

        SimpleCursorAdapter adpater = new SimpleCursorAdapter( this, idTela, registros, campos, camposTela );

        lvCadastro.setAdapter( adpater );
        */


    }

    public void btnMaisBaratoOnClick(View view) {



    }
}
