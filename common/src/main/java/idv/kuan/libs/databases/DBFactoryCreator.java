package idv.kuan.libs.databases;

import java.util.HashMap;

public class DBFactoryCreator {
    static protected HashMap<String, BaseDBFactory> dbFactories = new HashMap<>();
    protected String getID;

    protected DBFactoryCreator() {

    }

    public static BaseDBFactory getFactory(BaseDBFactory factory) {
        return factory;
    }

    public static BaseDBFactory getFactory(String id) {
        BaseDBFactory dbFactory = dbFactories.get(id);
        dbFactory.getID = id;
        return dbFactory;
    }
}
