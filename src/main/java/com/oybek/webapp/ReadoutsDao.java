package com.oybek.webapp;

import com.oybek.webapp.entities.Readout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ReadoutsDao {

    @Autowired
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Readout p){
        String sql = String.format(
                        "insert into readouts(date, type, value, username)" +
                        "values ('%s', '%s', %d, '%s')",
                        p.getDate(), p.getType(), p.getValue(), p.getUsername());
        return template.update(sql);
    }

    public List<Readout> getReadouts(String username) {
        return template.query(
                String.format("select date, type, value from readouts where username='%s'", username),
                new RowMapper<Readout>() {
                    public Readout mapRow(ResultSet rs, int row) throws SQLException {
                        Readout readout = new Readout();
                        readout.setDate(rs.getString("date"));
                        readout.setType(rs.getString("type"));
                        readout.setValue(rs.getInt("value"));
                        return readout;
                    }
                });
    }

    public List<Readout> getReadouts() {
        return template.query(
                "select * from all_readouts",
                new RowMapper<Readout>() {
                    public Readout mapRow(ResultSet rs, int row) throws SQLException {
                        Readout readout = new Readout();
                        readout.setUsername(rs.getString("username"));
                        readout.setDate(rs.getString("date"));
                        readout.setType(rs.getString("type"));
                        readout.setValue(rs.getInt("value"));
                        return readout;
                    }
                });
    }
}
