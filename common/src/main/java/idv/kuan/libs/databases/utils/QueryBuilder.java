package idv.kuan.libs.databases.utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private List<String> columns;
    private List<Object> values;
    private List<String> updateColumns;

    public QueryBuilder() {
        columns = new ArrayList<>();
        values = new ArrayList<>();
        updateColumns = new ArrayList<>();
    }

    public void addColumnValue(String column, Object value) {
        if(value!=null){
            addNullableColumnValue(column,value);
        }
    }

    public void addNullableColumnValue(String column, Object value) {
        columns.add(column);
        values.add(value);
        updateColumns.add(column + " = ?");
    }

    public void prepareStatement(PreparedStatement statement) throws SQLException {
        for (int i = 0; i < values.size(); i++) {
            Object value = values.get(i);
            int index = i + 1;

            if (value == null) {
                statement.setNull(index, java.sql.Types.NULL);
            } else if (value instanceof String) {
                statement.setString(index, (String) value);
            } else if (value instanceof Integer) {
                statement.setInt(index, (Integer) value);
            } else if (value instanceof Double) {
                statement.setDouble(index, (Double) value);
            } else {
                // Add more type handling as needed
                throw new SQLException("Unsupported value type: " + value.getClass().getName());
            }
        }
    }

    public String buildInsertQuery(String tableName) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ");
        queryBuilder.append(tableName);
        queryBuilder.append(" (");

        for (int i = 0; i < columns.size(); i++) {
            queryBuilder.append(columns.get(i));

            if (i < columns.size() - 1) {
                queryBuilder.append(", ");
            }
        }

        queryBuilder.append(") VALUES (");

        for (int i = 0; i < values.size(); i++) {
            queryBuilder.append("?");

            if (i < values.size() - 1) {
                queryBuilder.append(", ");
            }
        }

        queryBuilder.append(")");

        return queryBuilder.toString();
    }

    public String buildUpdateQuery(String tableName, String condition) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE ");
        queryBuilder.append(tableName);
        queryBuilder.append(" SET ");

        for (int i = 0; i < updateColumns.size(); i++) {
            queryBuilder.append(updateColumns.get(i));

            if (i < updateColumns.size() - 1) {
                queryBuilder.append(", ");
            }
        }

        if (condition != null && !condition.isEmpty()) {
            queryBuilder.append(" WHERE ");
            queryBuilder.append(condition);
        }

        return queryBuilder.toString();
    }
}
