package org.prth.dao;

import org.prth.config.AppConfig;
import org.prth.model.SPAA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SPAADaoImpl implements SPAADao {

    private final Connection connection;

    public SPAADaoImpl() throws Exception {
        this.connection = AppConfig.getConnection();
    }

    @Override
    public List<SPAA> getAllSpaa() {
        String sql = "SELECT * FROM spaa";
        List<SPAA> SPAAS = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SPAAS.add(mapResultSetToSpaa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SPAAS;
    }

    @Override
    public SPAA getSpaaById(Long id) {
        String sql = "SELECT * FROM spaa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSpaa(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SPAA addSpaa(SPAA SPAA) {
        String sql = "INSERT INTO spaa (id, text, tag) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, SPAA.getId());
            stmt.setString(2, SPAA.getText());
            stmt.setString(3, SPAA.getTag());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                SPAA.setId((long) keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SPAA;
    }

    @Override
    public SPAA updateSpaa(Long id, SPAA SPAA) {
        SPAA existing = getSpaaById(id);
        if (existing == null) return null;

        String sql = "UPDATE spaa SET text = ?, tag = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, SPAA.getText() != null ? SPAA.getText() : existing.getText());
            stmt.setString(2, SPAA.getTag() != null ? SPAA.getTag() : existing.getTag());
            stmt.setLong(3, id);
            stmt.executeUpdate();

            return getSpaaById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteSpaa(Long id) {
        String sql = "DELETE FROM spaa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SPAA> getSpaaByTag(String tag){
        String sql = "SELECT * FROM spaa WHERE tag = ?";
        List<SPAA> SPAAS = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tag);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SPAAS.add(mapResultSetToSpaa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SPAAS;
    }

    private SPAA mapResultSetToSpaa(ResultSet rs) throws SQLException {
        SPAA spaa = new SPAA();
        spaa.setId(rs.getLong("id"));
        spaa.setText(rs.getString("text"));
        spaa.setTag(rs.getString("tag"));
        return spaa;
    }
}