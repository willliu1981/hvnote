package idv.kuan.hvnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.BaseDBFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseDBFactory dbFactory = new AndroidDBFactory(this);
        dbFactory.config("android1", "hv.db", "hv.db");
        Connection connection = dbFactory.getConnection("android1");
        System.out.println("xxx MA:" + connection);

    }


}