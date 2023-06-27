package idv.kuan.hvnote.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.daoimpl.StatementDao;
import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.Dao;

public class EntranceActivity extends AppCompatActivity {

    private Button btn_entrance;
    private TextView txtv_debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        DBFactoryCreator.getFactory(new AndroidDBFactory(this)).
                config("android1","hv.db","hv.db");

        btn_entrance = findViewById(R.id.entr_btn_manage_phrases);
        txtv_debug = findViewById(R.id.entr_txtv_debug);

        btn_entrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntranceActivity.this, ManagePhrasesActivite.class);
                intent.putExtra("msg1", "test Hello!");

                Dao<Statement> dao = new StatementDao();
                Statement st = new Statement();
                st.setId(46);
                try {
                    Statement statement = dao.findByIDOrAll(st);
                    intent.putExtra("st1", statement);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}