package br.edu.utfpr.trabalhofinal;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.edu.utfpr.trabalhofinal.dao.ListaProdutoDAO;
import br.edu.utfpr.trabalhofinal.dao.ProdutoDAO;

public class ListaProdutoActivity extends AppCompatActivity {

    private ListaProdutoDAO listaProdutoDAO;

    ListView listaDeCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ListView listaDeCursos = findViewById(R.id.lvProdutos);

        listaProdutoDAO = new ListaProdutoDAO(this);

        //Buscar os produtos
        ListaProdutoDAO listaProdutos = new ListaProdutoDAO( this );
        listaProdutos.listar();

        Cursor registros = listaProdutos.listar();

        String campos[] = { "descricao" };

        int camposTela[] = { android.R.id.text1 };

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, "descricao", registros, camposTela);
        SimpleCursorAdapter adpater = new SimpleCursorAdapter( this, android.R.layout.simple_list_item_1, registros, campos, camposTela );


        listaDeCursos.setAdapter(  adpater );

    }

    public void btnMaisBaratoOnClick(View view) {

        ProdutoDAO produtoDao = new ProdutoDAO(this);
        Cursor registros = produtoDao.listar();

        double total = 0;
        double menor = 0;
        String produto = "";
        int qtd = 0;

        System.out.println("\n\n");
        while( registros.moveToNext() ){
            System.out.println("\n----------------");
            System.out.println( "\n"+ registros.getString( registros.getColumnIndex("nome")) );
            System.out.println( "\n"+ registros.getString( registros.getColumnIndex("qtde")) );
            System.out.println( "\n"+ registros.getString( registros.getColumnIndex("valor") ) );

            total = Double.parseDouble( registros.getString( registros.getColumnIndex("qtde")) ) *
                    Double.parseDouble( registros.getString( registros.getColumnIndex("valor")) );

            menor = qtd == 0 ? total : menor;

            if( total < menor ){
                menor = total;
                produto = registros.getString( registros.getColumnIndex("nome")).toString();
            }

            System.out.println( "\nQTD: "+ total );

            System.out.println("\n----------------");
            qtd++;
        }

        System.out.println( "===========" );
        System.out.println( "Produto: "+produto );
        System.out.println( "Menor: "+menor );

        AlertDialog.Builder alerta = new AlertDialog.Builder( this );
        alerta.setTitle(
                "Mais barato=>"+
                "Produto: "+produto+
                "\nValor: "+menor
        );
        alerta.setView( listaDeCursos );
        alerta.setCancelable( false );
        alerta.setNeutralButton( "Ok", null );
        alerta.show();






    }
}
