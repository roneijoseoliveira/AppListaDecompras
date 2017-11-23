package app.com.revisao;

import io.realm.Realm;
import io.realm.RealmObject;

public class Produto extends RealmObject{

    private int id;

    private String produto;
    private String quantidade;
    private String categoria;


    public Produto() {
        this.produto = produto;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }


    public int getId() { return id;}

    public void setId(int id) {this.id = id; }

    public String getProduto() {return produto; }

    public void setProduto(String produto) {this.produto = produto; }

    public String getQuantidade() { return quantidade;  }

    public void setQuantidade(String quantidade) { this.quantidade = quantidade;    }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) {   this.categoria = categoria;}





    public  static  int autoIncrementId(){
        Realm realm=Realm.getDefaultInstance();
        int key=1;
        try {
            key=realm.where(Produto.class).max("id").intValue()+1;

        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return key;
    }

}
