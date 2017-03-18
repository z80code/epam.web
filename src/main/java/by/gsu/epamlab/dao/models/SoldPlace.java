package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldPlace {
    private int id;
    private int userId;
    private int placeId;
    private int sessionId;

    public SoldPlace() {
    }

    public SoldPlace(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4)
                );
    }

    public SoldPlace(int userId, int placeId, int sessionId) {
        this(0, userId, placeId, sessionId);
    }

    public SoldPlace(int id, int userId, int placeId, int sessionId) {
        this.id = id;
        this.userId = userId;
        this.placeId = placeId;
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "SoldPlace{" +
                "id=" + id +
                ", userId=" + userId +
                ", placeId=" + placeId +
                ", sessionId=" + sessionId +
                '}';
    }
}
