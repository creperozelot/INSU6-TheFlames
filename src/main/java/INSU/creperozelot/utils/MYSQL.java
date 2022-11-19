package INSU.creperozelot.utils;

import INSU.creperozelot.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getTeams() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        Statement stmt = con.createStatement();

        List<String> list = new ArrayList<>();

        if (stmt.executeQuery("SELECT * FROM `INSU`").next()) {

        ResultSet result = stmt.executeQuery("SELECT * FROM `INSU`");

        result.first();

            while (!result.isAfterLast()) {

                String team = result.getString("TEAM");

                list.add(team);

                result.next();

            }
        }


        return list;
    }


    public static List<String> getPlayerbyTeam(String teamname) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        Statement stmt = con.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM `INSU` WHERE `TEAM`='" + teamname + "';");

        result.first();

        List<String> players = new ArrayList<>();

        if (con.createStatement().executeQuery("SELECT * FROM `INSU` WHERE `TEAM`='" + teamname + "';").next()) {

            while (!result.isAfterLast()) {

                String team = result.getString("PLAYER");

                players.add(team);

                result.next();

            }
        }
        return players;
    }
    public static String getTeambyName(String playername) throws SQLException {

        if (con.createStatement().executeQuery("SELECT TEAM FROM `INSU` WHERE PLAYER='" + playername + "';").next()) {

            try {
                Statement stmt = con.createStatement();

                ResultSet result = stmt.executeQuery("SELECT TEAM FROM `INSU` WHERE PLAYER='" + playername + "';");

                result.first();

                String team = result.getString("TEAM");

                return team;

            } catch (SQLException e) {
                e.printStackTrace();
                return "**Fehler bei Abruf**";
            }
        } else {
            return "**No team**";
        }
    }

    public static String getIDbyName(String playername) {
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT ID FROM `INSU` WHERE PLAYER='" + playername + "';");
            result.first();
            int i = result.getInt("ID");
            return String.valueOf(i);
        } catch (SQLException e) {
            e.printStackTrace();
            return "ID nicht gefunden";
        }
    }

    public static String getDeathStatebyName(String playername) throws SQLException {

        String status = "n/a";

        if (con.createStatement().executeQuery("SELECT DEATH FROM `INSU` WHERE PLAYER='" + playername + "';").next()) {

            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT DEATH FROM `INSU` WHERE PLAYER='" + playername + "';");
            result.first();
            int i = result.getInt("DEATH");


            switch (i) {
                case 0:
                    status = "Lebt";
                    break;
                case 1:
                    status = "Tot (Nachricht nicht gesendet)";
                    break;
                case 2:
                    status = "Tot (Nachricht gesendet)";
                    break;
            }

        }
        return status;
    }

    public static boolean isGameMaster(String playername) throws SQLException {

        boolean i = false;

        if (con.createStatement().executeQuery("SELECT ISGAMEMASTER FROM `INSU` WHERE PLAYER='" + playername + "';").next()) {

            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT ISGAMEMASTER FROM `INSU` WHERE PLAYER='" + playername + "';");
            result.first();
            i = result.getBoolean("ISGAMEMASTER");

        }
        return i;
    }


}


