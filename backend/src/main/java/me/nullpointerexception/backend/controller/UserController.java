package me.nullpointerexception.backend.controller;

import me.nullpointerexception.backend.model.user.User;
import me.nullpointerexception.backend.model.user.UserStatus;
import me.nullpointerexception.backend.model.user.UserType;
import me.nullpointerexception.backend.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired JdbcTemplate jdbcTemplate;

    RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            UUID.fromString(rs.getString("UserID")),
            rs.getString("Name"),
            rs.getString("Email"),
            rs.getBytes("Password"),
            System.currentTimeMillis(),
            rs.getLong("RegisterDate"),
            UserStatus.valueOf(rs.getString("Status")),
            rs.getBytes("ProfilePicture"),
            UserType.valueOf(rs.getString("UserType"))
    );

    @PostMapping(value = "/register")
    public ResponseEntity<Object> createUser(@RequestBody RegisterInfo registerInfo) {
        String password = registerInfo.password();
        String userName = registerInfo.username();
        String email = registerInfo.email();
        byte[] hash = password.getBytes();
        UserType userType = UserType.valueOf("USER");

        String sqlSelect = "SELECT * FROM user WHERE Name=\"" + userName + "\" OR Email=\"" + email + "\";";
        try {
            boolean[] hasNext = new boolean[1];
            jdbcTemplate.query(sqlSelect, (rs) -> {
                if (rs.next()) hasNext[0] = true;
            });

            if (hasNext[0]) {
                return new ResponseEntity<>("Username or email already in use.", HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error during user creation.", HttpStatus.BAD_REQUEST);
        }

        User user = new User(userName,email,hash, userType);
        String sql = "INSERT INTO User(UserID,Name,Email,Password,LastOnline,RegisterDate,Status,ProfilePicture,UserType) VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            jdbcTemplate.update(sql, user.getUserID().toString(), user.getUserName(), user.getEmail(), user.getUserPassword(), user.getRegisterDate(),
                    user.getLastOnline(), user.getUserStatus().toString(), user.getProfilePicture(), user.getUserType().toString());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error during user creation.", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginInfo loginInfo) {
        String userName = loginInfo.username();
        String password = loginInfo.password();
        String sql = "SELECT * FROM User WHERE Name = ? AND Password = ?;";
        try{
            User user = jdbcTemplate.queryForObject(sql,userRowMapper,userName,password);
            if (user == null || user.getUserStatus() != UserStatus.ACTIVE)
                return new ResponseEntity<>("Error.", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error during login.", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/setPassword")
    public ResponseEntity<Object> setPassword(@RequestBody SetPasswordInfo setPasswordInfo) {
        String userName = setPasswordInfo.username();
        byte[] password = setPasswordInfo.password();
        String sql = "UPDATE User SET Password = ? WHERE Name = ? AND Password = ?";
        try {
            jdbcTemplate.update(sql, password, userName);
            return new ResponseEntity<>("Password successfully updated.", HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("User name or password is incorrect.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable String id){
        String sql = "SELECT UserID,Name,Email,RegisterDate,LastOnline,Status,ProfilePicture,UserType FROM user WHERE UserID = ?";
        User user = jdbcTemplate.queryForObject(sql,userRowMapper,id);
        return user;
    }

    @PostMapping(value = "/user/update")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateInformation updateInformation){
        String userID = updateInformation.userID();
        String username = updateInformation.username();
        String email = updateInformation.email();
        byte[] picture = updateInformation.profilePicture();
        String sqlName = "UPDATE User SET Name = ? WHERE UserID = ?";
        String sqlEmail = "UPDATE User SET Email = ? WHERE UserID = ?";
        String sqlPicture = "UPDATE User SET ProfilePicture = ? WHERE UserID = ?";
        try {
            jdbcTemplate.update(sqlName,username,userID);
            jdbcTemplate.update(sqlEmail,email,userID);
            jdbcTemplate.update(sqlPicture,picture,userID);
            return new ResponseEntity<>("Update completed",HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity<>("Update failed",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user/setStatus")
    public ResponseEntity<Object> setUserStatus(@RequestBody StatusInfo statusInfo){
        String sql = "UPDATE User SET Status = ? WHERE UserID = ?";
        try {
            jdbcTemplate.update(sql, statusInfo.userStatus(), statusInfo.userID());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/user/setType")
    public ResponseEntity<Object> setUserType(@RequestBody UserTypeInfo userTypeInfo ){
        String sql = "UPDATE User SET UserType = ? WHERE UserID = ?";
        try {
            jdbcTemplate.update(sql,userTypeInfo.userType(),userTypeInfo.userID());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
