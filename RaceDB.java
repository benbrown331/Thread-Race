/*
    Benjamin Brown
    */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RaceDB {

    public static synchronized void insertRace(String name, int time) {
        Connection connection = DBUtil.getConnection();
        try {
            String str = "INSERT INTO Winners" +
                    "(Winner_Name,Winner_Speed) " +
                    "VALUES " +
                    "(?,?)";

            PreparedStatement ps = connection.prepareStatement(str);

            ps.setString(1, name);
            ps.setInt(2, time);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printRace() {
        Connection connection = DBUtil.getConnection();
        String str = "SELECT * FROM Winners ";

        try (PreparedStatement ps = connection.prepareStatement(str);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString(2);
                int time = rs.getInt(3);

                Console.println("Contestant " + name
                        + " completed the race in " + time
                        + " milliseconds!\n");
            }
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
