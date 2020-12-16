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

    public void getData(String name) throws SQLException {
        String sql = "select * from users where name = '" + name + "';";
        rs = stmt.executeQuery(sql);
        GameController.namePet = rs.getString("name");
        GameController.maxHP = rs.getDouble("max_hp");
        GameController.imageID = rs.getInt("image_id");
        GameController.health = rs.getDouble("health_stat");
        GameController.happiness = rs.getDouble("happiness_stat");
        GameController.hunger = rs.getDouble("hunger_stat");
        GameController.thirst = rs.getDouble("thirst_stat");
        GameController.cleanliness = rs.getDouble("cleanliness_stat");
        GameController game = Main.loader4.getController();
        game.setProgressBars(GameController.health, GameController.happiness, GameController.thirst, GameController.cleanless, GameController.eat, GameController.maxHP);
        game.petia.setImage(GameController.img[rs.getInt("image_id")]);
    }

}
