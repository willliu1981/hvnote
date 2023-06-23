package idv.kuan.hvnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.BaseDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.utils.TableSchemaModifier;
import idv.kuan.libs.date.TimestampConverter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //*

        // DBFactory dbFactory = new AndroidDBFactory(this);

        BaseDBFactory dbFactory = BaseDBFactory.getFactory(new AndroidDBFactory(this));
        dbFactory.config("android1", "hv.db", "hv.db");
        //System.out.println("xxx MA:" + connection);

        //changeTable(connection);




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

        testSave();
        testQueryAll();
        //*/

    }

    private void testSave() {

        BaseDBFactory factory = DBFactoryCreator.getFactory("android1");


        String sql = "insert into statement_table (statement)" +
                "values (?)";
        try {
            PreparedStatement preparedStatement = factory.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "B3 1號溫停止" + (int) (Math.random() * 10000));
            //preparedStatement.setString(2, "房務");
            //preparedStatement.setInt(3, 0);
            //preparedStatement.setInt(4, 0);
            //preparedStatement.setString(5, "2023-06-19 22:10:02");
            //preparedStatement.setString(5, "2023-06-19 22:10:02");

            preparedStatement.execute();
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
                String statement = resultSet.getString("statement");
                String atCreated = resultSet.getString("at_created");
                String category = resultSet.getString("category");
                //int isFavorite = resultSet.getInt("is_favorite");


                System.out.println("xxx MA ------:" + idx++);
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