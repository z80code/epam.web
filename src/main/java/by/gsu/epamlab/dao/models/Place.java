package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Place {
	private int id;
	private int seat;
	private int row;
	private String section;
	private int theaterId;
	private String state;
	private int x;
	private int y;
	Timestamp addTime;
	private int cost;

	public Place() {
	}

	public Place(int id, int seat, int row, String section, int theaterId, String state, int x, int y, Timestamp addTime, int cost) {
		this.id = id;
		this.seat = seat;
		this.row = row;
		this.section = section;
		this.theaterId = theaterId;
		this.state = state;
		this.x = x;
		this.y = y;
		this.addTime = addTime;
		this.cost = cost;
	}

	public Place(int seat, int row, String section, int theaterId, String state, int x, int y, Timestamp addTime, int cost) {
		this.seat = seat;
		this.row = row;
		this.section = section;
		this.theaterId = theaterId;
		this.state = state;
		this.x = x;
		this.y = y;
		this.addTime = addTime;
		this.cost = cost;
	}

	public Place(ResultSet resultSet) throws SQLException {
		this(resultSet.getInt(1),
				resultSet.getInt(2),
				resultSet.getInt(3),
				resultSet.getString(4),
				resultSet.getInt(5),
				resultSet.getString(6),
				resultSet.getInt(7),
				resultSet.getInt(8),
				resultSet.getTimestamp(9),
				resultSet.getInt(10));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Place place = (Place) o;

		if (id != place.id) return false;
		if (seat != place.seat) return false;
		if (row != place.row) return false;
		if (theaterId != place.theaterId) return false;
		if (x != place.x) return false;
		if (y != place.y) return false;
		if (cost != place.cost) return false;
		return section != null ? section.equals(place.section) : place.section == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + seat;
		result = 31 * result + row;
		result = 31 * result + (section != null ? section.hashCode() : 0);
		result = 31 * result + theaterId;
		result = 31 * result + x;
		result = 31 * result + y;
		result = 31 * result + cost;
		return result;
	}
}

