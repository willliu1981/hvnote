package idv.kuan.hvnote.database.daoimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idv.kuan.hvnote.database.models.Statement;
import idv.kuan.libs.databases.DBFactoryCreator;
import idv.kuan.libs.databases.Dao;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class StatementDao implements Dao<Statement> {
    private static final String tableName = "statement_table";

    @Override
    public void create(Statement entity) throws SQLException {
        createOrUpdateEntity(entity);
    }


    @Override
    public Statement findById(Serializable id) throws SQLException {
        return findByIDOrAll(new Statement());
    }

    @Override
    public void update(Statement entity) throws SQLException {
        createOrUpdateEntity(entity);
    }

    @Override
    public void createOrUpdateEntity(Statement entity) throws SQLException {
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
    public void delete(Statement entity) throws SQLException {
        Connection connection = DBFactoryCreator.getFactory().getConnection();
        String sqlQuery = "delete from " + tableName + " where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        if (entity == null) {
            throw new SQLException("entity is null");
        }

        if (entity.getId() == null) {
            throw new SQLException("id is null");
        }

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.execute();
    }

    @Override
    public List<Statement> findAll() throws SQLException {
        return findByIDOrAll(new Statement());
    }

    @Override
    public <U> U findByIDOrAll(Statement entity) throws SQLException {
        if (entity == null) {
            throw new SQLException("entity is null");
        }
        Connection connection = DBFactoryCreator.getFactory().getConnection();
        String sqlQuery = "select * from " + tableName;
        PreparedStatement preparedStatement = null;
        if (entity.getId() == null) {
            preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Statement> list = new ArrayList<>();
            Statement statement = null;
            while (resultSet.next()) {
                statement = new Statement();
                populateEntityProperties(statement, resultSet);
                list.add(statement);
            }

            return (U) list;

        } else {
            preparedStatement = connection.prepareStatement(sqlQuery + " where id=?");
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Statement statement = new Statement();
            if (resultSet.next()) {
                populateEntityProperties(statement, resultSet);
            }
            return (U) statement;
        }

    }

    private void populateEntityProperties(Statement statement, ResultSet resultSet) throws SQLException {
        statement.setId(resultSet.getInt("id"));
        statement.setStatement(resultSet.getString("statement"));
        statement.setCategory(resultSet.getString("category"));
        statement.setFavorite(resultSet.getInt("is_favorite"));
        statement.setArchived(resultSet.getInt("is_archived"));
        statement.setAtCreated(resultSet.getString("at_created"));
        statement.setAtUpdated(resultSet.getString("at_updated"));
    }
}
