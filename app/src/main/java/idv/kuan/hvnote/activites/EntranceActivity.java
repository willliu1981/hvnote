package idv.kuan.hvnote.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.daoimpl.StatementsDao;
import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.kuanandroidlibs.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.daos.Dao;

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

/* test
        StatementsDao dao=new StatementsDao();
        try {
            List<Statement> all = dao.findAll();
            for(Statement st:all){
                System.out.println("xxx MA:st="+st);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

 //*/

        btn_entrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntranceActivity.this, ManagePhrasesActivite.class);
                intent.putExtra("msg1", "test Hello!");

                Dao<Statement> dao = new StatementsDao();
                Statement st = new Statement();
                st.setId(47);
                try {
                    Statement statement = dao.findByIDOrAll(st);
                    intent.putExtra("st1", (Serializable) statement);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}