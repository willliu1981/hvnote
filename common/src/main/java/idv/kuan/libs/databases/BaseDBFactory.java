package idv.kuan.libs.databases;

import java.sql.Connection;
import java.util.HashMap;


public abstract class BaseDBFactory extends DBFactoryCreator {

    private HashMap<String, String[]> commands = new HashMap<>();

    protected BaseDBFactory(){};

    public BaseDBFactory config(String id, String... commands) {
        this.commands.put(id, commands);
        dbFactories.put(id, this);
        getID = id;
        return this;
    }

    public Connection getConnection() {

        return getConnection(this.commands.get(getID));
    }


    public abstract Connection getConnection(String commands[]);


}
