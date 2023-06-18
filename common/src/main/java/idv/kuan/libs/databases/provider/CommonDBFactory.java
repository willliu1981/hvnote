package idv.kuan.libs.databases.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import idv.kuan.libs.databases.BaseDBFactory;

public class CommonDBFactory extends BaseDBFactory {
    public static String DefaultUrl = "C:/java/db/sqlite/flashcard4/fc4.db";

    @Override
    public Connection getConnection(String commands[]) {
        /*
        command 是db 完整路徑,省略commands[0],將以DefaultUrl為值於command
         */

        String command = "";
        if (commands == null || commands[0] == null || commands[0].equals("")) {
            command = DefaultUrl;


        }

        String url = "jdbc:sqlite:" + command;
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
