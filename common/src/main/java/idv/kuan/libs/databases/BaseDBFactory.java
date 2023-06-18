package idv.kuan.libs.databases;

import java.sql.Connection;
import java.util.HashMap;


public abstract class BaseDBFactory {

    private HashMap<String, String[]> commands = new HashMap<>();


    public BaseDBFactory config(String id, String... commands) {

        this.commands.put(id, commands);
        return this;
    }

    public Connection getConnection(String id) {
        return getConnection(this.commands.get(id));
    }


    public abstract Connection getConnection(String commands[]);


}
