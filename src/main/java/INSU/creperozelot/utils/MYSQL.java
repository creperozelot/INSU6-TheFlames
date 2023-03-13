package INSU.creperozelot.utils;

import INSU.creperozelot.main;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.FileOutputStream;
import java.io.IOException;
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
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoreonnect=true", username, password);
                System.out.println("[INSU] MYSQL Verbindung hergestellt");
            } catch (SQLException e) {
                System.out.println("[INSU] MYSQL Verbindung konnte nicht hergestellt werden.");
            }
    }

    public static void disconnect() {
        if (isConnected())
            try {
                if (MYSQL.isConnected()) {
                    con.close();
                    System.out.println("| [INSU] MYSQL Verbindung wurde getrennt.");
                } else {
                    System.out.println("| [INSU] MYSQL Verbindung kann nicht getrent werden, da keine Verbindung besteht!.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void forcedisconnect() {
        if (isConnected())
            try {
                con.close();
                System.out.println("| [INSU] MYSQL Verbindung wurde getrennt. (force)");
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

    public static void delete(String playername) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("DELETE FROM INSU WHERE PLAYER='" + playername + "';");
    }

    public static boolean arePlayersinTeams(String player1, String player2) throws SQLException {
        boolean bol = false;

        List<String> teamplayers =  MYSQL.getPlayerbyTeamID(MYSQL.getTeamIDbyName(player1));

        if (teamplayers.contains(player2)) {
            bol = true;
        }

        return bol;
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

    public static boolean PlayerExist(String playername) {

        try {


            Statement stmt = con.createStatement();
            return stmt.executeQuery("SELECT * FROM `INSU` WHERE `PLAYER`='" + playername + "';").next();
        } catch (SQLException e){

            e.printStackTrace();

        }
        return false;
    }

    public static int getHighestID() {
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM INSU ORDER BY ID DESC LIMIT 1");
            result.first();
            return result.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
            return -404;
        }
    }

    public static void CreatePlayer(String playername, int DeahtID, String teamname, int ID, String isgamemaster, String started) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO `INSU` (`PLAYER`, `DEATH`, `ID`, `TEAM`, `ISGAMEMASTER`, `STARTED`) VALUES ('" + playername + "', " + DeahtID + ", " + ID + ", '" + teamname + "', '" + isgamemaster + "', '" + started + "');");
    }

    public static void setDeath(String playername, int deathid) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `INSU` SET `DEATH`='" + deathid + "' WHERE `PLAYER`='" + playername + "';");
    }

    public static int getDeath(String playername) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT DEATH FROM INSU WHERE PLAYER='" + playername + "'");
        result.first();
        return result.getInt("DEATH");
    }

    public static void setTeam(String playername, String teamname, int teamid) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `INSU` SET `TEAM`='" + teamname + "' WHERE `PLAYER`='" + playername + "';");
        stmt.execute("UPDATE `INSU` SET `ID`='" + teamid + "' WHERE `PLAYER`='" + playername + "';");
    }

    public static List<String> getTeams() throws SQLException {

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

    public static String export() throws SQLException, IOException {

        String excelFilePath = main.getInstance().getDataFolder() + "-exportdata.xlsx";

            String sql = "SELECT * FROM INSU";

            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("INSU");


            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            statement.close();


            return excelFilePath;
        }


    private static void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("PLAYER");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("DEATH");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("ID");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("TEAM");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("ISGAMEMASTER");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("STARTED");

    }


    private static void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                       XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            String player = result.getString("PLAYER");
            String death = result.getString("DEATH");
            int id = result.getInt("ID");
            String team = result.getString("TEAM");
            String isgamemaster = result.getString("ISGAMEMASTER");
            String started = result.getString("STARTED");

            Row row = sheet.createRow(rowCount++);



            int columnCount = 0;

            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(player);

            cell = row.createCell(columnCount++);
            cell.setCellValue(death);


            cell = row.createCell(columnCount++);
            cell.setCellValue(id);

            cell = row.createCell(columnCount++);
            cell.setCellValue(team);

            cell = row.createCell(columnCount++);
            cell.setCellValue(isgamemaster);

            cell = row.createCell(columnCount++);
            cell.setCellValue(started);

            CellStyle style = cell.getCellStyle();
            style.setWrapText(true);
            cell.setCellStyle(style);



        }
    }

    public static List<String> getPlayerbyTeam(String teamname) throws SQLException {
        
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

    public static List<String> getTeammateNamesbyPlayer(Player player) throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM `INSU` WHERE `ID`='" + MYSQL.getIDbyName(player.getName()) + "';");

        result.first();

        List<String> players = new ArrayList<>();

        if (con.createStatement().executeQuery("SELECT * FROM `INSU` WHERE `ID`='" + MYSQL.getIDbyName(player.getName()) + "';").next()) {

            while (!result.isAfterLast()) {

                String team = result.getString("PLAYER");

                players.add(team);

                result.next();

                    if (players.contains("NONE")) {
                        players.remove("NONE");
                    }

                    if (players.contains(player.getName())) {
                        players.remove(player.getName());
                    }


            }
        }
        return players;
    }

    public static List<String> getPlayerbyTeamID(int id) throws SQLException {

        Statement stmt = con.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM `INSU` WHERE `ID`='" + id + "';");

        result.first();

        List<String> players = new ArrayList<>();

        if (con.createStatement().executeQuery("SELECT * FROM `INSU` WHERE `ID`='" + id + "';").next()) {

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

    public static int getStrikes(String playername) {
        try {
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT STRIKES FROM `INSU` WHERE PLAYER='" + playername + "';");

            result.first();

            int strikes = result.getInt("STRIKES");


            return strikes;

        } catch (SQLException e) {
            e.printStackTrace();
            return -404;
        }
    }

    public static void addStrike(String playername) throws SQLException {
        Statement stmt = con.createStatement();
        int strikes = MYSQL.getStrikes(playername) + 1;
        stmt.execute("UPDATE `INSU` SET `STRIKES`='" + strikes + "' WHERE `PLAYER`='" + playername + "';");
    }

    public static int getTeamIDbyName(String playername) throws SQLException {

        if (con.createStatement().executeQuery("SELECT ID FROM `INSU` WHERE PLAYER='" + playername + "';").next()) {

            try {
                Statement stmt = con.createStatement();

                ResultSet result = stmt.executeQuery("SELECT ID FROM `INSU` WHERE PLAYER='" + playername + "';");

                result.first();

                int team = result.getInt("ID");

                return team;

            } catch (SQLException e) {
                e.printStackTrace();
                return -403;
            }
        } else {
            return -404;
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

    public static boolean TeamExist(String teamname) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SELECT * FROM `INSU` WHERE `TEAM`='" + teamname + "';").next();
    }


    public static boolean isStarted(String playername) throws SQLException {

        boolean i = false;

        if (con.createStatement().executeQuery("SELECT STARTED FROM `INSU` WHERE PLAYER='" + playername + "';").next()) {

            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT STARTED FROM `INSU` WHERE PLAYER='" + playername + "';");
            result.first();
            i = result.getBoolean("STARTED");

        }
        return i;
    }

    public static void setStarted(String playername, String bool) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE `INSU` SET `STARTED`='" + bool + "' WHERE `PLAYER`='" + playername + "';");
    }

    public static boolean PlayerHasTeamMate(String playername) throws SQLException {
        Statement stmt = con.createStatement();
        String id = MYSQL.getIDbyName(playername);
        boolean bo = false;
        List<String> list = new ArrayList<>();

        if (stmt.executeQuery("SELECT * FROM `INSU`").next()) {

            ResultSet result = stmt.executeQuery("SELECT ID FROM `INSU` WHERE `ID`='" + id + "';");

            result.first();

            while (!result.isAfterLast()) {


                list.add(result.toString());


                result.next();



                switch (list.size()) {
                    case 0:
                        bo = false;
                        break;
                    case 1:
                        bo = false;
                        break;
                    case 2:
                        bo = true;
                        break;
                    default:
                        bo = false;
                        break;
                }

            }
        }
        return bo;
    }


    public static void keepalive() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {


                    Statement stmt = con.createStatement();
                    stmt.executeQuery("SELECT 'KEEP_ALIVE';").next();
                } catch (SQLException e){

                    e.printStackTrace();

                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20 * 60 * 10);
    }
}


