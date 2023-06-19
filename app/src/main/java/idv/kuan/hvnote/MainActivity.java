package idv.kuan.hvnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        //System.out.println("xxx MA:" + connection);

        testSave(connection);

        testQuery(connection,2);

    }

    private void testSave(Connection connection) {
        String sql = "insert into statement_table (statement,category,is_favorite,is_archived,at_created,at_updated)" +
                "values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "B3 1號溫停止" + (int) (Math.random() * 10000));
            preparedStatement.setString(2, "房務");
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, "2023-06-19 22:10:02");
            preparedStatement.setString(5, "2023-06-19 22:10:02");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testQuery(Connection connection, int id) {
        String sql = "select * from statement_table where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String statement = resultSet.getString("statement");


                System.out.println("xxx MA :"+statement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}