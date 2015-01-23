package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
/**
 * Class that encapsulates processing requests to a database for getting
 * disk entities.
 * @author timokhin
 *
 */
public class DiskDAO {
	
	private Connection con;
	private int noOfRecords;
	/**
	 * Initialize connection to a database
	 * @throws SQLException if a database access error occurs or the url is null
	 */
	public DiskDAO() throws SQLException { 
		con = ConnectionFactory.getInstance().getConnection();
	}
	/**
	 * 
	 * @return a list of all latest disks by one in each genre
	 * @throws SQLException if a database access error occurs
	 */
    public List<CDDisk> getAllLatest() throws SQLException{
    	List<CDDisk> res = null;	
    	
    	Statement stmt = null;
    	ResultSet rs = null;
    	try {
    		String sql = "SELECT * from Genre";
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(sql);
    		res = new ArrayList<CDDisk>(rs.getFetchSize());
    		while(rs.next())
    			res.add(getLatest(rs.getString(1)));
    		
    	}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
    	
    	return res;
    }
    
    private CDDisk getLatest(String genre) throws SQLException{
    	CDDisk res = null;
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		String sql = "SELECT * FROM Disk WHERE genre=? ORDER BY release_date DESC LIMIT 1";
    		stmt = con.prepareStatement(sql);   
    		stmt.setString(1, genre);
    		rs = stmt.executeQuery();
    		if (rs.next())
    			res = new CDDisk(rs);
    	}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
    	
    	return res;
    }
    /**
     * Function for searching disks in database.If null parameters are passed, they will be ignored
     * @param type type of disk
     * @param genre genre of disk
     * @param name name of disk
     * @return a list of disks that are appropriate for input parameters.
     * @throws SQLException if a database access error occurs
     */
	public List<CDDisk> getDisks(String type, String genre, String name) throws SQLException {
    	List<CDDisk> res = null;
    	
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		stmt = con.prepareStatement("SELECT * FROM Disk WHERE type=IFNULL(?, 0) AND " +
    									"genre=IFNULL(?, 0) AND name LIKE IFNULL(?,'%')");
    		
    		stmt.setString(1, type);
    		stmt.setString(2, genre);
    		stmt.setString(3, name == null ? null : "%" + name + "%");
    		
    		rs = stmt.executeQuery();
    		res = new ArrayList<CDDisk>(rs.getFetchSize());
    		
    		while(rs.next()) {
    			res.add(new CDDisk(rs));
    		}            
    	}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
    	
    	return res;
    }
    /**
     * Function for searching disks in database.If null parameters are passed, they will be ignored
     * @param type type of disk
     * @param genre genre of disk
     * @param name name of disk
     * @param from index from which begin output
     * @param noOfRecords number of records in output
     * @return a list of disks that are appropriate for input parameters.
     * @throws SQLException if a database access error occurs
     */
	public List<CDDisk> getDisks(String type, String genre, String name, int from, int noOfRecords) throws SQLException {
		List<CDDisk> res = null;
		
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		stmt = con.prepareStatement("SELECT SQL_CALC_FOUND_ROWS * FROM Disk WHERE type=IFNULL(?, 0) AND " +
    									"genre=IFNULL(?, 0) AND name LIKE IFNULL(?,'%') limit ?, ?");
    		
    		stmt.setString(1, type);
    		stmt.setString(2, genre);
    		stmt.setString(3, name == null ? null : "%" + name + "%");
    		stmt.setInt(4, from);
    		stmt.setInt(5, noOfRecords);
    		
    		rs = stmt.executeQuery();
    		res = new ArrayList<CDDisk>(rs.getFetchSize());
            
    		while(rs.next()) {
    			res.add(new CDDisk(rs));
    		}
    		
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
    	}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
		return res;
	}
	/**
	 * 
	 * @param id id of disk to search
	 * @return disk with specified id from database
     * @throws SQLException if a database access error occurs
	 */
	public CDDisk getDisk (int id) throws SQLException {
		CDDisk res = null;
		
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		stmt = con.prepareStatement("SELECT * FROM Disk WHERE id=?");
    		stmt.setInt(1, id);
    		
    		rs = stmt.executeQuery();
    		if (rs.next())
    			res = new CDDisk(rs);
    	}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
    	
		return res;
	}
	/**
	 * 
	 * @return all disk types in database
	 * @throws SQLException if a database access error occurs
	 */
	public List<String> getTypes() throws SQLException{
		List<String> res = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Type");
			rs = stmt.executeQuery();
			res = new ArrayList<>(rs.getFetchSize());
			while (rs.next())
				res.add(rs.getString(1));
		}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
		
		return res;
	}
	/**
	 * 
	 * @return all disk genres in database
	 * @throws SQLException if a database access error occurs
	 */
	public List<String> getGenres() throws SQLException {
		List<String> res = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Genre");
			rs = stmt.executeQuery();
			res = new ArrayList<>(rs.getFetchSize());
			while (rs.next())
				res.add(rs.getString(1));
		}finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
		
		return res;
	}
	/**
	 * 
	 * @return a number of found records in getDisks(String, Sting, String, int, int) function
	 * @throws SQLException if a database access error occurs
	 */
	public int getNoOfRecords() {
		return noOfRecords;
	}
}
