package idv.kuan.libs.databases.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableSchemaModifier {

    /**
     * 一般修改結構,不改欄位名,不減少欄位
     *
     * @param connection
     * @param existingTableName
     * @param updatedTableName
     * @param sql
     */
    public static void evolveTableStructure(Connection connection, String existingTableName, String updatedTableName, String sql) {
        try {

            //將原有的table 改名為tableName+"__temp"
            String sqlTemp = "ALTER TABLE " + existingTableName + " RENAME TO " + existingTableName + "__temp";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlTemp);
            preparedStatement.execute();


            //執行使用者需求的sql 語句
            preparedStatement.execute(sql);


            //將temp的table 資料賦給updated的table
            sqlTemp = "INSERT INTO " + updatedTableName + " SELECT * FROM " + existingTableName + "__temp";
            preparedStatement.execute(sqlTemp);

            //移除temp的table
            sqlTemp = "DROP TABLE " + existingTableName + "__temp";
            preparedStatement.execute(sqlTemp);

            /*
            String sql0 = "DROP TABLE statement_table_temp";
            PreparedStatement preparedStatement = connection.prepareStatement(sql0);
            preparedStatement.execute();

            preparedStatement.execute(sql);

            String sql2 = "INSERT INTO statement_table_temp SELECT * FROM statement_table";
            preparedStatement.execute(sql2);

            String sql3 = "DROP TABLE statement_table";
            preparedStatement.execute(sql3);

            String sql4 = "ALTER TABLE statement_table_temp RENAME TO statement_table";
            preparedStatement.execute(sql4);

             */


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
