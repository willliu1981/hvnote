package idv.kuan.libs.databases;

import java.sql.Connection;
import java.util.HashMap;


public abstract class BaseDBFactory extends DBFactoryCreator {

    private HashMap<String, String[]> commands = new HashMap<>();

    protected BaseDBFactory() {
    }

    ;

    public BaseDBFactory config(String id, String... commands) {
        this.commands.put(id, commands);
        putDBFactories(id, this);

        return getAndInitializeDBFactory(id);
    }

    private void putDBFactories(String id, BaseDBFactory DBFactory) {
        if (defaultKey == null) {
            defaultKey = id;
        }
        DBFactories.put(id, DBFactory);
    }


    public Connection getConnection() {

        return getConnection(this.commands.get(currentId));
    }


    public abstract Connection getConnection(String commands[]);


}
