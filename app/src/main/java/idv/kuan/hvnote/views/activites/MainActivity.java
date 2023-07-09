package idv.kuan.hvnote.views.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import idv.kuan.hvnote.R;
import idv.kuan.hvnote.database.daoimpl.StatementsDao;
import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.kuanandroidlibs.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.BaseDBFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // DBFactory dbFactory = new AndroidDBFactory(this);

        BaseDBFactory dbFactory = BaseDBFactory.getFactory(new AndroidDBFactory(this));
        dbFactory.config("android1", "hv.db", "hv.db");
        Connection connection = dbFactory.getConnection();


        StatementsDao dao=new StatementsDao();
        try {
            List<Statement> all = dao.findAll();
            for(Statement st:all){
                System.out.println("xxx MA:st="+st);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


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
        //testUpsert();

        //testQueryAll();


        //testDelete();
        //testFindAll();
        //testFindById();

    }

/*
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

        Dao dao = new StatementDao();
        Statement st = new Statement();

        st.setId(46);
        st.setStatement("test st5");
        st.setCategory("COMMON5");
        st.setFavorite(false);
        st.setArchived(false);

        try {
            dao.createOrUpdateEntity(st);
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

    private void testFindAll() {
        Dao dao = new StatementDao();
        Statement st = new Statement();
        try {
            List<Statement> list = (List<Statement>) dao.findByIDOrAll(st);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                list.forEach(x -> System.out.println("xxx MA element: id=" + x.getId() +
                        "; statement=" + x.getStatement()));
            } else {
                for (Statement element : list) {
                    System.out.println("xxx MA element: id=" + element.getId() +
                            "; statement=" + element.getStatement());
                }
            }
            System.out.println("xxx MA list:" + list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testFindById() {
        Dao dao = new StatementDao();
        Statement st = new Statement();
        st.setId(45);
        try {
            Statement statement = (Statement) dao.findByIDOrAll(st);
            System.out.println("xxx MA statement: id=" + statement.getId() + "; statement=" + statement.getStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testDelete(){
        Dao dao = new StatementDao();
        Statement st = new Statement();
        st.setId(45);
        try {
            dao.delete(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //*/

}