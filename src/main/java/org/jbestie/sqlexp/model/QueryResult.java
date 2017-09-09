package org.jbestie.sqlexp.model;

import java.util.List;

public class QueryResult {
    private List<String> columnNames;
    private List<List<String>> resultSet;
    
    public QueryResult(List<String> columnNames, List<List<String>> resultSet) {
        super();
        this.columnNames = columnNames;
        this.resultSet = resultSet;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<List<String>> getResultSet() {
        return resultSet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((columnNames == null) ? 0 : columnNames.hashCode());
        result = prime * result + ((resultSet == null) ? 0 : resultSet.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QueryResult other = (QueryResult) obj;
        if (columnNames == null) {
            if (other.columnNames != null)
                return false;
        } else if (!columnNames.equals(other.columnNames))
            return false;
        if (resultSet == null) {
            if (other.resultSet != null)
                return false;
        } else if (!resultSet.equals(other.resultSet))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "QueryResult [columnNames=" + columnNames + ", resultSet=" + resultSet + "]";
    }
}
