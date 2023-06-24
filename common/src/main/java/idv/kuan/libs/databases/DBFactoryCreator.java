package idv.kuan.libs.databases;

import java.util.HashMap;

public class DBFactoryCreator {
    static protected HashMap<String, BaseDBFactory> DBFactories = new HashMap<>();
    protected static String defaultKey;
    protected String currentId;

    protected DBFactoryCreator() {

    }

    public static BaseDBFactory getFactory(BaseDBFactory factory) {

        for (BaseDBFactory dbFactory : DBFactories.values()) {
            if (factory.getClass().getName().equals(dbFactory.getClass().getName())) {

                return dbFactory;
            }
        }

        return factory;
    }

    public static BaseDBFactory getFactory(String id) {
        return getAndInitializeDBFactory(id);
    }

    public static BaseDBFactory getFactory() {
        if (defaultKey == null) {
            return null;
        }


        return getAndInitializeDBFactory(defaultKey);
    }

    protected static BaseDBFactory getAndInitializeDBFactory(String id) {
        BaseDBFactory dbFactory = DBFactories.get(id);
        dbFactory.currentId = id;
        return dbFactory;
    }
}
