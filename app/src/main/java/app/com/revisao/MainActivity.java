package app.com.revisao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnView, btnUpdate, btnDelete;
    EditText txtId, txtProduto, txtquantidade, txtPerecivel;
    TextView txt;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.main_btnAdd);
        btnView = (Button) findViewById(R.id.main_btnView);
        btnUpdate = (Button) findViewById(R.id.main_btnUpdate);
        btnDelete = (Button) findViewById(R.id.main_btnDelete);


        txtProduto = (EditText) findViewById(R.id.main_txtNome);


        txt = (TextView) findViewById(R.id.main_txtNome);

        final Button btnCalcular = (Button)findViewById(R.id.main_btnOk);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txtNome = (TextView) findViewById(R.id.main_txtNome);
                String Nome = txtNome.getText().toString();

                Intent intent = new Intent(getContext(), ListAc.class);
                Bundle params = new Bundle();

                params.putString("nome", Nome);

                intent.putExtras(params);
                startActivity(intent);

            }
        });

        realm = Realm.getDefaultInstance();
    }

    public void clickAction(View view) {
        switch (view.getId()) {
            case R.id.main_btnAdd:
                addRecord();
                break;
            case R.id.main_btnView:
                viewRecord();
                break;
            case R.id.main_btnUpdate:
                updateRecord();
                break;
            case R.id.main_btnDelete:
                deleteRecord();
                break;
            case R.id.main_btnOk:
                Intent itLista = new Intent(this, ListaActivity.class);
                startActivity(itLista);
                break;

        }
    }


    public void addRecord() {
        Produto mer = new Produto();
        mer.setId(mer.autoIncrementId());
        mer.setProduto(txtProduto.getText().toString());

        mer.setQuantidade(txtquantidade.getText().toString());

       // mer.setPerecivel(txtPerecivel.getText());


        realm.beginTransaction();
        realm.copyToRealm(mer);
        realm.commitTransaction();
    }

    public void viewRecord() {
        int idProduto = Integer.parseInt(txtId.getText().toString());
        Produto produtoView = realm.where(Produto.class)
                .equalTo("id", idProduto).findFirst();
        txt.setText(produtoView + produtoView.getProduto().toString());


    }

    public void updateRecord() {
        int idProduto = Integer.parseInt(txtId.getText().toString());
        Produto produtos = realm.where(Produto.class)
                .equalTo("id", idProduto).findFirst();
        realm.beginTransaction();
        produtos.setProduto(txtProduto.getText().toString());
        realm.commitTransaction();
    }

    public void deleteRecord() {
        int idUsuario = Integer.parseInt(txtId.getText().toString());
        Produto usuario = realm.where(Produto.class)
                .equalTo("id", idUsuario).findFirst();

        realm.beginTransaction();
          usuario.deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    private Context getContext(){
        return this;
    }
}




