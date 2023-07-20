package idv.kuan.hvnote.views.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import idv.kuan.hvnote.R;
import idv.kuan.kuanandroidlibs.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.utils.TableSchemaModifier;

public class EntranceActivity extends AppCompatActivity {

    private Button btn_entrance;
    private TextView txtv_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        init();

        btn_entrance = findViewById(R.id.entr_btn_manage_phrases);
        txtv_debug = findViewById(R.id.entr_txtv_debug);


        btn_entrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntranceActivity.this, ManagePhrasesActivite.class);

                startActivity(intent);
            }
        });

    }

    private void init() {
        initDB();
    }

    private void initDB() {
        Connection conn = DBFactoryCreator.getFactory(new AndroidDBFactory(this)).
                config("android1", "hv.db", "hv.db").getConnection();


        Boolean isTableExist = TableSchemaModifier.isTableExist(conn, "memo_table");
        System.out.println("dbg EA: table is exist? " + isTableExist);

        //*
        if (isTableExist != null) {
            if (!isTableExist) {

                String sql = "CREATE TABLE \"memo_table\" ( " +
                        " \"id\" INTEGER NOT NULL UNIQUE, " +
                        " \"title\" TEXT, " +
                        " \"category\" TEXT DEFAULT 'COMMON', " +
                        " \"content\" TEXT, " +
                        " \"is_important\" INTEGER DEFAULT 0, " +
                        " \"is_completed\" INTEGER DEFAULT 0, " +
                        " \"at_created\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                        " \"at_updated\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                        " PRIMARY KEY(\"id\" AUTOINCREMENT) " +
                        ")";

                TableSchemaModifier.createNew(conn, sql);
            } else {
                String sql = "CREATE TABLE \"memo_table\" ( " +
                        " \"id\" INTEGER NOT NULL UNIQUE, " +
                        " \"title\" TEXT, " +
                        " \"category\" TEXT DEFAULT 'COMMON2', " +
                        " \"content\" TEXT, " +
                        " \"is_important\" INTEGER DEFAULT 0, " +
                        " \"is_completed\" INTEGER DEFAULT 0, " +
                        " \"at_created\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                        " \"at_updated\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                        " PRIMARY KEY(\"id\" AUTOINCREMENT) " +
                        ")";
                TableSchemaModifier.evolveTableStructure(conn, "memo_table", "memo_table", sql);


            }
        }

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("PRAGMA table_info(memo_table)");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String dflt_value = resultSet.getString("dflt_value");

                System.out.println("xxx EA:name= " + name + " , type=" + type + " , dflt_value=" + dflt_value);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        // */
    }
}