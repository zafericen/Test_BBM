package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.favourites.Favourites;
import me.nullpointerexception.backend.pojo.FavouritesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavouritesController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping(value = "/user/{id}/favourites")
    public ResponseEntity<Object> getUserFavourites(@PathVariable String id){
        String sql = " SELECT ProductID FROM Favourites WHERE UserID = " + id +";";
        try {
            List<String> products = jdbcTemplate.queryForList(sql,String.class);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/addFavourites")
    public ResponseEntity<Object> addUserFavourites(FavouritesInfo favouritesInfo){
        String sql = "INSERT INTO Favourites(UserID,ProductID) VALUES (?,?);";
        jdbcTemplate.update(sql,favouritesInfo.UserID(),favouritesInfo.ProductID());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/deleteFavourites")
    public ResponseEntity<Object> deleteUserFavourites(FavouritesInfo favouritesInfo){
        String sql = "DELETE FROM Favourites VALUES WHERE UserID = ? AND ProductID = ?;";
        jdbcTemplate.update(sql,favouritesInfo.UserID(),favouritesInfo.ProductID());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
