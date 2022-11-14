package INSU.creperozelot.utils;
import INSU.creperozelot.main;

import java.sql.*;

public class MYSQL {

    public static String host = main.getInstance().getConfig().getString("mysql.host");

    public static String port = main.getInstance().getConfig().getString("mysql.port");

    public static String database = main.getInstance().getConfig().getString("mysql.db");

    public static String username = main.getInstance().getConfig().getString("mysql.username");

    public static String password = main.getInstance().getConfig().getString("mysql.password");

    public static Connection con;

    public static void connect() {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("| [INSU] MYSQL Verbindung hergestellt                     |");
            } catch (SQLException e) {
                System.out.println("| [INSU] MYSQL Verbindung konnte nicht hergestellt werden.|");
            }
    }

    public static void disconnect() {
        if (isConnected())
            try {
                con.close();
                System.out.println("| [INSU] MYSQL Verbindung wurde getrennt.                 |");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static boolean isConnected() {
        if (con == null)
            return false;
        return true;
    }

    public static void update(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean PlayerExist(String playername) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT * FROM `INSU` WHERE `PLAYER`='" + playername + "';").next();
    }

    public static void setDeath(String playername, int deathid) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `INSU` SET `DEATH`='" + deathid + "' WHERE `PLAYER`='" + playername + "';");
    }

    public static void setTeam(String playername, String teamname, int teamid) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `INSU` SET `TEAM`='" + teamname + "' WHERE `PLAYER`='" + playername + "';");
        stmt.execute("UPDATE `INSU` SET `ID`='" + teamid + "' WHERE `PLAYER`='" + playername + "';");
    }

}


