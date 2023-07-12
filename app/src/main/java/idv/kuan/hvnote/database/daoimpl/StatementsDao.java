package idv.kuan.hvnote.database.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.daos.CommonDao;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class StatementsDao extends CommonDao<Statement> {


    @Override
    protected Statement createNewEntity() {
        return new Statement();
    }

    @Override
    protected void populateBuilderWithEntityProperties(QueryBuilder builder, Statement entity) {
        builder.addColumnValue("statement", entity.getStatement());
        builder.addColumnValue("category", entity.getCategory());
        builder.addColumnValue("is_favorite", entity.getFavorite() == null ? null :
                entity.getFavorite() == true ? 1 : 0);
        builder.addColumnValue("is_archived", entity.getArchived() == null ? null :
                entity.getFavorite() == true ? 1 : 0);
        builder.addColumnValue("at_created", entity.getAtCreated() == null ? null :
                entity.getAtCreated().toString());
        builder.addColumnValue("at_updated", entity.getAtUpdated() == null ? null :
                entity.getAtUpdated().toString());
    }

    @Override
    protected String getTableName() {
        return "statement_table";
    }


    protected void mapResultSetToEntity(Statement entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
        entity.setStatement(resultSet.getString("statement"));
        entity.setCategory(resultSet.getString("category"));
        entity.setFavorite(resultSet.getInt("is_favorite"));
        entity.setArchived(resultSet.getInt("is_archived"));
        entity.setAtCreated(resultSet.getString("at_created"));
        entity.setAtUpdated(resultSet.getString("at_updated"));
    }
}
