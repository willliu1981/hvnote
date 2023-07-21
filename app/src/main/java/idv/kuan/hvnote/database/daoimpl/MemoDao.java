package idv.kuan.hvnote.database.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import idv.kuan.hvnote.database.models.Memo;
import idv.kuan.libs.databases.daos.CommonDao;
import idv.kuan.libs.databases.utils.QueryBuilder;

public class MemoDao extends CommonDao<Memo> {
    @Override
    protected Memo createNewEntity() {
        return new Memo();
    }

    @Override
    protected void populateBuilderWithEntityProperties(QueryBuilder builder, Memo entity) {
        builder.addColumnValue("title", entity.getTitle());
        builder.addColumnValue("category", entity.getCategory());
        builder.addColumnValue("content", entity.getContent());
        builder.addColumnValue("is_important", entity.getImportant());
        builder.addColumnValue("is_completed", entity.getCompleted());
        builder.addColumnValue("is_archived", entity.getArchived());
        builder.addColumnValue("at_created", entity.getAtCreated() == null ? null :
                entity.getAtCreated().toString());
        builder.addColumnValue("at_updated", entity.getAtUpdated() == null ? null :
                entity.getAtUpdated().toString());

    }

    @Override
    protected void mapResultSetToEntity(Memo entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getInt("id"));
        entity.setTitle(resultSet.getString("title"));
        entity.setCategory(resultSet.getString("category"));
        entity.setContent(resultSet.getString("content"));
        entity.setImportant(resultSet.getInt("is_important"));
        entity.setCompleted(resultSet.getInt("is_completed"));
        entity.setArchived(resultSet.getInt("is_archived"));
        entity.setAtCreated(resultSet.getString("at_created"));
        entity.setAtUpdated(resultSet.getString("at_updated"));


    }

    @Override
    protected String getTableName() {
        return "memo_table";
    }
}
