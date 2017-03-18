package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Place;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceRepository extends AbstractRepository<Place> {

    private final static String SELECT_ALL = "select * from places";
    private final static String SELECT_BY_ID = "select * from places where places.id=?";
    private final static String SELECT_BY_THEATER_ID = "select * from places where places.theaterId=?";
    private final static String SELECT_BY_SEAT_ROW_SECTION_THEATER = "select * from places where places.seat=? and places.row=? and  places.section=? and places.theaterId=?";
    private final static String DELETE_BY_ID = "deletePlace from places where places.id=?";
    private final static String ADD_PLACE = "insert into places(seat, row, section,  theaterId, state, x, y, cost) values(?,?,?,?,?,?,?,?)";

    public PlaceRepository(Connection conn) {
        super(conn);
    }

    @Override
    Place createByResultSet(ResultSet rs) throws SQLException {
        return new Place(rs);
    }

    @Override
    public Place getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Place(rs) : null;
    }

    public List<Place> getByIds(Integer... ids) throws SQLException {
        List<Place> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public List<Place> getByTheaterId(int id) throws SQLException {
        List<Place> places = new ArrayList<>();
        ResultSet rs = prepareRequest(SELECT_BY_THEATER_ID, id);
        while (rs.next()) {
            places.add(new Place(rs));
        }
        return places;
    }

    private Place getByUniFields(int seat, int row, String section, int theater) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_SEAT_ROW_SECTION_THEATER, row, section, seat, theater);
        return rs.next() ? new Place(rs) : null;
    }

    @Override
    public Place add(Place place) throws SQLException {
        Place placeExist = getByUniFields( place.getSeat(), place.getRow(), place.getSection(),place.getTheaterId());
        if (placeExist != null) return null;

        prepareUpdate(ADD_PLACE,
                place.getRow(),
                place.getSection(),
                place.getSeat(),
                place.getTheaterId(),
                place.getCost());

        return getByUniFields(place.getSeat(), place.getRow(), place.getSection(),place.getTheaterId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Place> getAll() throws SQLException {
        List<Place> places = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            places.add(new Place(rs));
        }
        return places;
    }
}
