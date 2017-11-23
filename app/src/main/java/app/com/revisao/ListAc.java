package app.com.revisao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by roneijose on 22/11/2017.
 */

public class ListAc extends AppCompatActivity {

    ArrayList<Produ> produtos = new ArrayList<Produ>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        if(savedInstanceState!=null)
            produtos = (ArrayList<Produ>)savedInstanceState.getSerializable("produtos");
        else
            produtos = new ArrayList<Produ>();

        final Button btnSalvar = (Button) findViewById(R.id.lista_btnSalvar);
        final CheckBox chkPerecivel = (CheckBox) findViewById(R.id.lista_chkPerecivel);

        final ListView lstProdutos = (ListView)findViewById(R.id.lista_lstProdutos);
        final ListView lista = (ListView) findViewById(R.id.lista_lstProdutos);
        final ArrayAdapter adpter = new ProduAdapter(this, R.layout.linha, produtos);
        lista.setAdapter(adpter);

        // Nome do Produto
        Bundle args = getIntent().getExtras();
        final TextView txtNome = (TextView) findViewById(R.id.lista_txtProduto);
        String nomeParam = args.getString("nome");
        txtNome.setText(nomeParam);

        // Categoria
        String[] categorias = {"Frios", "Vegetais", "Limpeza", "Higiene"};
        final Spinner spnCategorias = (Spinner)findViewById(R.id.lista_spnCategoria);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnCategorias.setAdapter(adapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checkPerecivel = chkPerecivel.isChecked();

                if(checkPerecivel)
                    alert("Produto perecivel");
                else
                    alert("Produto não perecivel");

                // Quantidade
                TextView txtQuantidade = (TextView) findViewById(R.id.lista_txtQuantidade);

                String Nome = txtNome.getText().toString();
                //int Quantidade = Integer.parseInt(txtQuantidade.getText().toString());
                String Quantidade = txtQuantidade.getText().toString();

                String Categoria = spnCategorias.getSelectedItem().toString();


                Produ p = new Produ(Nome, Quantidade, Categoria);
                produtos.add(p);
                adpter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("produtos", produtos);
        super.onSaveInstanceState(outState);
    }

    private void alert(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
