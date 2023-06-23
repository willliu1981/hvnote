package idv.kuan.hvnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import idv.kuan.androidlib.databases.provider.AndroidDBFactory;
import idv.kuan.libs.databases.BaseDBFactory;
import idv.kuan.libs.databases.DBFactoryCreator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseDBFactory dbFactory = DBFactoryCreator.getFactory(new AndroidDBFactory(this));
        System.out.println("xxx MA 1:" + dbFactory);
        dbFactory.config("android1", "hv.db", "hv.db");
        BaseDBFactory dbFactory2 = DBFactoryCreator.getFactory("android1");
        System.out.println("xxx MA 2:" + dbFactory2);

        BaseDBFactory dbFactory3 = DBFactoryCreator.getFactory(new AndroidDBFactory(this));
        System.out.println("xxx MA 3:" + dbFactory3);
        dbFactory3.config("android2", "hv.db", "hv.db");
        BaseDBFactory dbFactory4 = DBFactoryCreator.getFactory("android2");
        System.out.println("xxx MA 4:" + dbFactory4);
        /*

        // DBFactory dbFactory = new AndroidDBFactory(this);

        BaseDBFactory dbFactory = BaseDBFactory.getFactory(new AndroidDBFactory(this));
        dbFactory.config("android1", "hv.db", "hv.db");
        //System.out.println("xxx MA:" + connection);

        //changeTable(connection);

        testSave();

        testQueryAll();

         */

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
                    GMTtoLocal(timestamp);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeTable(Connection connection) {
        try {
            String sql0 = "DROP TABLE statement_table_temp";
            PreparedStatement preparedStatement = connection.prepareStatement(sql0);
            preparedStatement.execute();

            String sql1 = "CREATE TABLE \"statement_table_temp\" (" +
                    "\"id\"INTEGER NOT NULL UNIQUE," +
                    "\"statement\"TEXT NOT NULL," +
                    "\"category\"TEXT DEFAULT 'COMMON'," +
                    "\"is_favorite\"INTEGER DEFAULT 0," +
                    "\"is_archived\"INTEGER DEFAULT 0," +
                    "\"at_created\"TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "\"at_updated\"TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "PRIMARY KEY(\"id\" AUTOINCREMENT)" +
                    ")";

            preparedStatement.execute(sql1);

            String sql2 = "INSERT INTO statement_table_temp SELECT * FROM statement_table";
            preparedStatement.execute(sql2);

            String sql3 = "DROP TABLE statement_table";
            preparedStatement.execute(sql3);

            String sql4 = "ALTER TABLE statement_table_temp RENAME TO statement_table";
            preparedStatement.execute(sql4);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Timestamp GMTtoLocal(Timestamp gmt) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            final Date parse1 = formatter.parse(gmt.toString());

            formatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
            final String format2 = formatter.format(parse1);
            System.out.println("xxx MA format2:" + format2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }


}