package com.project.CryptoTracker.daos;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.project.CryptoTracker.models.Asset;

@Repository
public class AssetsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AssetsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Asset> displayList(Principal principal) throws SQLException {

        String pStmt = "SELECT * FROM assets where email = ?";

        List <Asset> assets = jdbcTemplate.query(pStmt, new GameRowMapper(),principal.getName());
        for (Asset assest : assets) {
            System.out.printf("Game %s: answer: %s number of guesses %s\n",
                assest.getId(),
                assest.getEmail(),
                assest.getName());
                
        }
        System.out.println("");
        return assets;
    }

    public void addAssert(Asset asset) throws SQLException {

        String pStmt = "INSERT INTO assets (userid, email, name, price, amount, purchaseDate, sellOrBuy) VALUES  (?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(pStmt,
                null,
                asset.getEmail(),
                asset.getName(),
                asset.getPrice(),
                asset.getAmount(),
                null,
                null);    
    }  
}
