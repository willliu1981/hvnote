package idv.kuan.libs.databases;

import java.util.HashMap;

public class DBFactoryCreator {
    static protected HashMap<String, BaseDBFactory> dbFactories = new HashMap<>();
    protected String getID;

    protected DBFactoryCreator() {

    }

    public static BaseDBFactory getFactory(BaseDBFactory factory) {

        for (BaseDBFactory dbFactory : dbFactories.values()) {
            if (factory.getClass().getName().equals(dbFactory.getClass().getName())) {

                return dbFactory;
            }
        }

        return factory;
    }

    public static BaseDBFactory getFactory(String id) {
        BaseDBFactory dbFactory = dbFactories.get(id);
        dbFactory.getID = id;
        return dbFactory;
    }
}
