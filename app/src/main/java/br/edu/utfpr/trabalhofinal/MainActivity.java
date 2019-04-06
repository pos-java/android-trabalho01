package br.edu.utfpr.trabalhofinal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.utfpr.trabalhofinal.Model.ListaProduto;
import br.edu.utfpr.trabalhofinal.Model.Produto;
import br.edu.utfpr.trabalhofinal.dao.ListaProdutoDAO;
import br.edu.utfpr.trabalhofinal.dao.ProdutoDAO;

public class MainActivity extends AppCompatActivity {

    //private EditText etProduto;
    private AutoCompleteTextView etProduto;
    private EditText etQtd;
    private EditText etValor;

    private ProdutoDAO produtoDAO;
    private ListaProdutoDAO listaProdutoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProduto = findViewById( R.id.etProduto );
        etQtd = findViewById( R.id.etQtd );
        etValor = findViewById( R.id.etValor );

        produtoDAO = new ProdutoDAO( this );
        listaProdutoDAO = new ListaProdutoDAO(this);

        carregarListaProdutos();
    }

    public void btAdicionarProdutoOnClick(View view) {

        //Adicionar produto
        Produto produto = new Produto();
        produto.setNome( etProduto.getText().toString() );
        produto.setQtde( Integer.parseInt( etQtd.getText().toString() ) );
        produto.setValor( Double.parseDouble( etValor.getText().toString() ) );

        produtoDAO.incluir( produto );


        //Adicionar lista de produto
        ListaProduto listaProduto = new ListaProduto();
        listaProduto.setDescricao( produto.getNome() );

        listaProdutoDAO.inserir( listaProduto );

        Toast.makeText( this, "Salvo com sucesso", Toast.LENGTH_LONG ).show();

        etProduto.setText("");
        etQtd.setText("");
        etValor.setText("");
    }

    public void btListarProdutosOnClick(View view) {

        Intent i = new Intent( this, ListaProdutoActivity.class);

        startActivity( i );

    }

    public void btIniciarOnClick(View view) {
        produtoDAO.limpar();

        Toast.makeText( this, "Registros excluidos com sucesso", Toast.LENGTH_LONG ).show();
    }

    public void carregarListaProdutos(){
        Cursor cursor = listaProdutoDAO.listar();

        startManagingCursor(cursor);
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_dropdown_item_1line, cursor, new String[] {"descricao"}, new int[] {android.R.id.text1});
        etProduto.setThreshold(1);


        cursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
                final int colIndex = cursor.getColumnIndexOrThrow("descricao");
                return cursor.getString(colIndex);
            }
        });

        cursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {

            public Cursor runQuery(CharSequence constraint) {
                String partialItemName = null;
                if (constraint != null) {
                    partialItemName = constraint.toString();
                }
                return listaProdutoDAO.LoadDescricoes(partialItemName);
            }
        });

        etProduto.setAdapter(cursorAdapter);


    }
}
