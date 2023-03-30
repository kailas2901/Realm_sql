package com.example.realm_sql;

import android.app.Application;

import io.realm.Realm;

public class RealmConfiguration extends Application {
    //use application because the app starts with this java class

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        io.realm.RealmConfiguration config = new io.realm.RealmConfiguration.Builder()
                .allowQueriesOnUiThread(true).allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
