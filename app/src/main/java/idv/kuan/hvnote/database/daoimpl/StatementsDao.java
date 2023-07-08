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
    protected void populateBuilderWithEntityProperties(QueryBuilder builder, Statement entity) {
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
    protected String getTableName() {
        return "statement_table";
    }

    @Override
    public Statement findById(Statement entity) throws SQLException {
        return findByIDOrAll(entity);
    }

    @Override
    public void delete(Statement entity) throws SQLException {

    }

    @Override
    public List<Statement> findAll() throws SQLException {
        return findByIDOrAll(new Statement());
    }

    @Override
    protected Statement createNewEntity() {
        return new Statement();
    }

    @Override
    public <U> U findByIDOrAll(Statement entity) throws SQLException {

        if (entity == null) {
            throw new SQLException("entity is null");
        }
        Connection connection = DBFactoryCreator.getFactory().getConnection();
        String sqlQuery = "select * from " + getTableName();
        PreparedStatement preparedStatement = null;
        if (entity.getId() == null) {
            preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Statement> list = new ArrayList<>();
            Statement statement = null;
            while (resultSet.next()) {
                statement = new Statement();
                mapResultSetToEntity(statement, resultSet);
                list.add(statement);
            }

            return (U) list;

        } else {
            preparedStatement = connection.prepareStatement(sqlQuery + " where id=?");
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Statement statement = new Statement();
            if (resultSet.next()) {
                mapResultSetToEntity(statement, resultSet);
            }
            return (U) statement;
        }
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
