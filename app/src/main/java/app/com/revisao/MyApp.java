package app.com.revisao;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by roneijose on 19/11/2017.
 */

public class MyApp extends Application{


    public void OnCreate(){
        super.onCreate();


        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME).schemaVersion(4).deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
