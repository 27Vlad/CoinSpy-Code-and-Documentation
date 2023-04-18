package com.project.CryptoTracker.daos;

import org.springframework.jdbc.core.RowMapper;

import com.project.CryptoTracker.models.Asset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowMapper implements RowMapper {
    @Override
    public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
        Asset a = new Asset();
        a.setId(rs.getInt(1));
        a.setUserId(rs.getInt(2));
        a.setEmail(rs.getString(3));
        a.setName(rs.getString(4));
        a.setPrice(rs.getDouble(5));
        a.setAmount(rs.getDouble(6));
        a.setPurchaseDate(rs.getDate(7));
        a.setSellOrBuy(rs.getBoolean(8));
       // System.out.println(a.toString());
        return a;
    }

};
