package idv.kuan.hvnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.hvnote.database.daos.StatementDao;
import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.libs.databases.BaseDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.Dao;
import idv.kuan.libs.databases.utils.QueryBuilder;
import idv.kuan.libs.date.TimestampConverter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // DBFactory dbFactory = new AndroidDBFactory(this);

        BaseDBFactory dbFactory = BaseDBFactory.getFactory(new AndroidDBFactory(this));
        dbFactory.config("android1", "hv.db", "hv.db");
        Connection connection = dbFactory.getConnection();
        //System.out.println("xxx MA:" + connection);

        //changeTable(connection);

        /*
        String sql = "CREATE TABLE \"statement_table\" (" +
                "\"id\"INTEGER NOT NULL UNIQUE," +
                "\"statement\"TEXT NOT NULL," +
                "\"category\"TEXT DEFAULT 'COMMON'," +
                "\"is_favorite\"INTEGER DEFAULT 0," +
                "\"is_archived\"INTEGER DEFAULT 0," +
                "\"at_created\"TEXT DEFAULT CURRENT_TIMESTAMP," +
                "\"at_updated\"TEXT DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY(\"id\" AUTOINCREMENT)" +
                ")";
        TableSchemaModifier.evolveTableStructure(dbFactory.getConnection(), "statement_table",
                "statement_table", sql);
        //*/


        //testSave();
        //testUpdate();
        testUpsert();

        testQueryAll();

    }


    private void testSave() {

        Dao dao = new StatementDao();
        Statement st = new Statement();

        st.setStatement("test st3");
        st.setCategory("COMMON3");
        st.setFavorite(false);
        st.setArchived(false);

        try {
            dao.create(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testUpdate() {

        Dao dao = new StatementDao();
        Statement st = new Statement();

        st.setId(45);
        st.setStatement("test st3-1");
        st.setCategory("COMMON3A");


        try {
            dao.update(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testUpsert() {

        StatementDao dao = new StatementDao();
        Statement st = new Statement();

        st.setId(46);
        st.setStatement("test st5");
        st.setCategory("COMMON5");
        st.setFavorite(false);
        st.setArchived(false);

        try {
            dao.upsertOrUpdateEntity(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testQueryAll() {
        Connection connection = BaseDBFactory.getFactory("android1").getConnection();

        String sql = "select * from statement_table ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            int idx = 1;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String statement = resultSet.getString("statement");
                String atCreated = resultSet.getString("at_created");
                String category = resultSet.getString("category");
                //int isFavorite = resultSet.getInt("is_favorite");


                System.out.println("xxx MA ------:" + idx++);
                System.out.println("xxx MA id:" + id);
                System.out.println("xxx MA statement:" + statement);
                System.out.println("xxx MA atCreated:" + atCreated);
                System.out.println("xxx MA category:" + category);

                if (atCreated != null) {
                    Timestamp timestamp = Timestamp.valueOf(atCreated);
                    Timestamp timestamp1 = TimestampConverter.GMTtoLocal(timestamp);
                    System.out.println("xxx MA timestamp1:" + timestamp1);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}