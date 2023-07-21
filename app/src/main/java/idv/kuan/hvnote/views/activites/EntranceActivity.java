package idv.kuan.hvnote.views.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.daoimpl.MemoDao;
import idv.kuan.hvnote.database.models.Memo;
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


        //*dbg...

        MemoDao dao = new MemoDao();
        Memo entity = new Memo();
        try {
            entity.setContent("Memo" + ((int) (Math.random() * 1000)));
            dao.create(entity);

            List<Memo> all = dao.findAll();
            for (Memo m : all) {
                System.out.println("xxx EA:entity=" + m.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // */
    }

    private void init() {
        initDB();
    }

    private void initDB() {
        Connection connection = DBFactoryCreator.getFactory(new AndroidDBFactory(this)).
                config("android1", "hv.db", "hv.db").getConnection();

        checkAndUpdateDB(connection);

    }

    private void checkAndUpdateDB(Connection connection) {
        String memoTableName = "memo_table";
        String memoTableCreateSql = "CREATE TABLE \"memo_table\" ( " +
                " \"id\" INTEGER NOT NULL UNIQUE, " +
                " \"title\" TEXT, " +
                " \"category\" TEXT DEFAULT 'COMMON', " +
                " \"content\" TEXT, " +
                " \"is_important\" INTEGER DEFAULT 0, " +
                " \"is_completed\" INTEGER DEFAULT 0, " +
                " \"is_archived\" INTEGER DEFAULT 0, " +
                " \"at_created\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                " \"at_updated\" TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                " PRIMARY KEY(\"id\" AUTOINCREMENT) " +
                ")";

        String statementTableName = "statement_table";
        String statementTableCreateSql = "CREATE TABLE \"statement_table\" ( " +
                " \"id\" INTEGER NOT NULL UNIQUE, " +
                " \"statement\" TEXT NOT NULL, " +
                " \"category\" TEXT DEFAULT 'COMMON', " +
                " \"is_favorite\" INTEGER DEFAULT 0, " +
                " \"is_archived\" INTEGER DEFAULT 0, " +
                " \"at_created\" TEXT DEFAULT CURRENT_TIMESTAMP, " +
                " \"at_updated\" TEXT DEFAULT CURRENT_TIMESTAMP, " +
                " PRIMARY KEY(\"id\" AUTOINCREMENT) " +
                ")";

        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            int appVersionCode = packageInfo.versionCode;

            TableSchemaModifier.createNewOrEvolveTableStructure(connection, appVersionCode, memoTableName, memoTableCreateSql);
            TableSchemaModifier.createNewOrEvolveTableStructure(connection, appVersionCode, statementTableName, statementTableCreateSql);

            TableSchemaModifier.updateDBVersion(connection, appVersionCode);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}