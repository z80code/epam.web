package by.gsu.epamlab.dao.entitys;

import java.sql.*;

abstract class AbstractRepository<T> implements IRepository<T> {

    private Connection conn;
    private Statement st;
    private PreparedStatement preSt;

    AbstractRepository(Connection conn) {
        this.conn = conn;
    }

    abstract T createByResultSet(ResultSet rs) throws SQLException;

    private void addParameters(Object... objs) throws SQLException {
        preSt.clearParameters();
        for (int i = 0; i < objs.length; i++) {
            preSt.setString(i+1, objs[i].toString());
        }
    }

    ResultSet prepareRequest(String sql, Object... params) throws SQLException {
        preSt = conn.prepareStatement(sql);
        addParameters(params);
        return preSt.executeQuery();
    }

    ResultSet request(String sql) throws SQLException {
        st = conn.createStatement();
        return st.executeQuery(sql);
    }

    int prepareUpdate(String sql, Object... params) throws SQLException {
        preSt = conn.prepareStatement(sql);
        addParameters(params);
        return preSt.executeUpdate();
    }


    @Override
    public void close() throws SQLException {
        if (st != null && !st.isClosed()) {
            st.close();
        }
        if (preSt != null && !preSt.isClosed()) {
            preSt.close();
        }
    }
}
