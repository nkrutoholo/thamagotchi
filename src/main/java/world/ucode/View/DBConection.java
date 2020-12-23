package world.ucode.View;

import world.ucode.Controller.CreateController;
import world.ucode.Controller.GameController;

import java.sql.*;

public class DBConection {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public void getConection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:Tamagotchi.s3db");
        stmt = con.createStatement();
    }

    public void execTable(String name, double maxHp, String password, int pokImage) {
        CreateController pet = Main.loader1.getController();
        try {
            stmt.execute("insert into users('name', 'password', 'maxHP', 'pokImage', 'health', 'happiness', 'hunger', 'thirst', 'cleanliness') values('"+ name +"', '"+ password +"', "+ maxHp +", " + pet.index +", 0, 0, 0, 0, 0);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void closeDB() throws SQLException {
        stmt.close();
        con.close();
    }

    public void firstStart() throws SQLException, ClassNotFoundException {
        getConection();
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(name             TEXT PRIMARY KEY NOT NULL, " +
                " password         char(50)     NOT NULL, " +
                " maxHP           double       NOT NULL, " +
                " pokImage         int          NOT NULL, " +
                " health      double       NOT NULL, " +
                " happiness   double       NOT NULL, " +
                " hunger      double       NOT NULL, " +
                " thirst      double       NOT NULL, " +
                " cleanliness double       NOT NULL);";
        stmt.executeUpdate(sql);
        closeDB();
    }

    public void createPok(String name, String pass, double maxHP, int pokImage) throws SQLException, ClassNotFoundException {
        getConection();
        String sql = "INSERT INTO users(name,password,maxHP,pokImage," +
                "health,happiness,hunger,thirst,cleanliness) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, pass);
        pstmt.setDouble(3, maxHP);
        pstmt.setInt(4, pokImage);
        pstmt.setDouble(5, maxHP);
        pstmt.setDouble(6, 1);
        pstmt.setDouble(7, 1);
        pstmt.setDouble(8, 1);
        pstmt.setDouble(9, 1);
        try {
            pstmt.executeUpdate();
        } catch(Exception err) {
            if(err.getMessage().contains("(UNIQUE constraint failed: Pets.name)")) {
                System.out.println(err);
            }
            //System.out.println(err);
        }
        closeDB();
    }

    public void checkPok(String name, String pass) throws SQLException, ClassNotFoundException {
        getConection();

        System.out.println("the loadPet (Console)");

        String sql = "SELECT * FROM users WHERE name = \'" + name + "\';";

        rs = stmt.executeQuery(sql);

        String password = "";
        try {
            password = rs.getString("password");
        } catch(Exception err) {
            if(err.getMessage().contains("ResultSet closed")) {
                closeDB();
            }
            System.out.println(err);
        }

        if(password != pass) {
            System.out.println("invalid password \'" + password + "\' -- \'" + pass + "\' (Console)");
            System.out.println();
            closeDB();

        }

        closeDB();
    }

    public void loadPok(String name) throws SQLException, ClassNotFoundException {
        getConection();
        String sql = "select * from users where name = '" + name + "';";
        rs = stmt.executeQuery(sql);
        GameController.pokName = rs.getString("name");
        GameController.maxHP = rs.getDouble("maxHP");
        GameController.imgID = rs.getInt("pokImage");
        GameController.health = rs.getDouble("health");
        GameController.happiness = rs.getDouble("happiness");
        GameController.eat = rs.getDouble("hunger");
        GameController.thirst = rs.getDouble("thirst");
        GameController.cleanless = rs.getDouble("cleanliness");
        GameController game = Main.loader4.getController();
        game.setProgressBars(GameController.health, GameController.happiness, GameController.thirst, GameController.cleanless, GameController.eat, GameController.maxHP);
        game.petia.setImage(GameController.img[rs.getInt("pokImage")]);
        closeDB();
    }

    public void savePet(String name, double health, double happiness, double hunger, double thirst, double cleanliness) throws SQLException, ClassNotFoundException {
        getConection();

        String sql = "UPDATE users SET health = ?, happiness = ?, hunger = ?, thirst = ?, cleanliness = ? WHERE name = \'" + name + "\';";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setDouble(1, health);
        pstmt.setDouble(2, happiness);
        pstmt.setDouble(3, hunger);
        pstmt.setDouble(4, thirst);
        pstmt.setDouble(5, cleanliness);
        pstmt.executeUpdate();
        closeDB();
    }

    public void deadPet(String name) throws SQLException, ClassNotFoundException {
        getConection();
        String sql = "DELETE FROM users WHERE name = \'" + name + "\';";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.executeUpdate();
        closeDB();
    }
}
