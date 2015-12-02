package datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Parmor;

/**
 * DAOSQLite Data Access Object for an SQLite database
 *
 * @author Jon VanZile - modified from John Phillips' code
 * @version 0.1 on 2015-12-01
 */
public class DAOSQLite {

    protected final static String DRIVER = "org.sqlite.JDBC";
    protected final static String JDBC = "jdbc:sqlite";

    /**
     * Inserts an record into the database table. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param parmor the object to insert
     * @param dbPath the path to the SQLite database
     */
    public static void createRecord(Parmor parmor, String dbPath) {
        String q = "insert into parmor (id, modelName, slot, paint, dResist, eResist, rResist, location) "
                + "values (null, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, parmor.getModelName());
            ps.setString(2, parmor.getSlot());
            ps.setString(3, parmor.getPaint());
            ps.setInt(4, parmor.getdResist());
            ps.setInt(5, parmor.geteResist());
            ps.setInt(6, parmor.getrResist());
            ps.setString(7, parmor.getLocation());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

//   /**
//     * Retrieve a record given an empId.
//     *
//     * @param empId the empId of the record to retrieve
//     * @param dbPath the path to the SQLite database
//     * @return User object
//     */
   public static Parmor retrieveRecordById(int id, String dbPath) {
        String q = "select id, modelName, slot, paint, dResist, eResist, rResist, location from parmor where id = ?";
        Parmor parmor = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            parmor = myQuery(conn, ps).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
       return parmor;
   } 
    /**
     * Retrieve all of the records in the database as a list sorted by email.
     *
     * @param dbPath the path to the SQLite database
     * @return list of objects
     */
    public static List<Parmor> retrieveAllRecordsByName(String dbPath) {
        String q = "select * from parmor order by modelName";
        List<Parmor> list = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            list = myQuery(conn, ps);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    /**
//     * Update a record from the database given an parmor object. Note the use
//     * of a parameterized query to prevent SQL Injection attacks.
//     *
//     * @param parmor the parmor record to update
//     * @param dbPath the path to the SQLite database
//     */
    public static void updateRecord(Parmor parmor, String dbPath) {
        String q = "update parmor set modelName=?, slot=?, paint=?, dResist=?, eResist=?, rResist=?, location=? where id = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, parmor.getModelName());
            ps.setString(2, parmor.getSlot());
            ps.setString(3, parmor.getPaint());
            ps.setInt(4, parmor.getdResist());
            ps.setInt(5, parmor.geteResist());
            ps.setInt(6, parmor.getrResist());
            ps.setString(7, parmor.getLocation());
            ps.setInt(8, parmor.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Delete a record from the database given its id. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param id the id of the record to delete
     * @param dbPath the path to the SQLite database
     */
    public static void deleteRecord(int id, String dbPath) {
        String q = "delete from parmor where id = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new parmor table.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void createTable(String dbPath) {
        String q = "create table parmor ("
                + "id integer not null primary key autoincrement, "
                + "modelName varchar(15) not null, "
                + "slot varchar(10) not null, "
                + "paint varchar(30) not null, "
                + "dResist integer not null, "
                + "eResist integer not null, "
                + "rResist integer not null, "
                + "location varchar(30) not null)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Drops the parmor table erasing all of the data.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void dropTable(String dbPath) {
        final String q = "drop table if exists parmor";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populates the table with sample data records.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void populateTable(String dbPath) {
        Parmor p;
        p = new Parmor(0, "T45", "chest", "atomcats", 120, 255, 76, "diamond city");
        DAOSQLite.createRecord(p, dbPath);
    }

    /**
     * A helper method that executes a prepared statement and returns the result
     * set as a list of objects.
     *
     * @param conn a connection to the database
     * @param ps a prepared statement
     * @return list of objects from the result set
     */
    protected static List<Parmor> myQuery(Connection conn, PreparedStatement ps) {
        List<Parmor> list = new ArrayList();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String modelName = rs.getString("modelName");
                String slot = rs.getString("slot");
                String paint = rs.getString("paint");
                int dResist = rs.getInt("dResist");
                int eResist = rs.getInt("eResist");
                int rResist = rs.getInt("rResist");
                String location = rs.getString("location");
                Parmor p = new Parmor(id, modelName, slot, paint, dResist, eResist, rResist, location);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Creates a connection to the SQLite database.
     *
     * @param dbPath the path to the SQLite database
     * @return connection to the database
     */
    protected static Connection getConnectionDAO(String dbPath) {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(JDBC + ":" + dbPath);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
