package idv.kuan.hvnote.database.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.Dao;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class StatementDao implements Dao<Statement> {
    private static final String tableName = "statement_table";

    @Override
    public void create(Statement entity) throws SQLException {
        upsertOrUpdateEntity(entity);
    }


    @Override
    public Statement findById(Serializable id) {
        return null;
    }

    @Override
    public void update(Statement entity) throws SQLException {
        upsertOrUpdateEntity(entity);
    }

    public void upsertOrUpdateEntity(Statement entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("entity is null");
        }

        Connection connection = DBFactoryCreator.getFactory().getConnection();

        QueryBuilder builder = new QueryBuilder();
        populateBuilderWithEntityProperties(builder, entity);

        String query = null;

        if (entity.getId() == null) {
            query = builder.buildInsertQuery(tableName);
        } else {
            String condition = "id = " + entity.getId();
            query = builder.buildUpdateQuery(tableName, condition);
        }


        PreparedStatement statment = connection.prepareStatement(query);
        builder.prepareStatement(statment);
        statment.executeUpdate();


    }

    private void populateBuilderWithEntityProperties(QueryBuilder builder, Statement entity) {
        builder.addColumnValue("statement", entity.getStatement());
        builder.addColumnValue("category", entity.getCategory());
        builder.addColumnValue("is_favorite", entity.getFavorite() == null ? null :
                entity.getFavorite() == true ? 1 : 0);
        builder.addColumnValue("is_archived", entity.getArchived() == null ? null :
                entity.getFavorite() == true ? 1 : 0);
        builder.addColumnValue("at_created", entity.getAtCreated() == null ? null :
                entity.getAtCreated().toString());
        builder.addColumnValue("at_updated", entity.getAtCreated() == null ? null :
                entity.getAtUpdated().toString());

    }


    @Override
    public void delete(Statement entity) {

    }

    @Override
    public List<Statement> findAll() {
        return null;
    }

}
