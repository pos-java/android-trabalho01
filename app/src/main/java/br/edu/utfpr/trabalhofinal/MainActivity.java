package br.edu.utfpr.trabalhofinal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.utfpr.trabalhofinal.Model.ListaProduto;
import br.edu.utfpr.trabalhofinal.Model.Produto;
import br.edu.utfpr.trabalhofinal.dao.ListaProdutoDAO;
import br.edu.utfpr.trabalhofinal.dao.ProdutoDAO;

public class MainActivity extends AppCompatActivity {

    private EditText etProduto;
    private EditText etQtd;
    private EditText etValor;

    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProduto = findViewById( R.id.etProduto );
        etQtd = findViewById( R.id.etQtd );
        etValor = findViewById( R.id.etValor );

        produtoDAO = new ProdutoDAO( this );

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

        ListaProdutoDAO listaProdutoDAO = new ListaProdutoDAO(this);
        listaProdutoDAO.inserir( listaProduto );



        Toast.makeText( this, "Salvo com sucesso", Toast.LENGTH_LONG ).show();
    }

    public void btListarProdutosOnClick(View view) {

        Intent i = new Intent( this, ListaProdutoActivity.class);

        startActivity( i );

    }

    public void btIniciarOnClick(View view) {

        produtoDAO.limpar();

        Toast.makeText( this, "Registros excluidos com sucesso", Toast.LENGTH_LONG ).show();
    }
}
