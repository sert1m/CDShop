package database;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * This class encapsulates CDDisk entity in cdshop database.
 * It contains constructors, getters and setters of it fields.
 * @author timokhin
 */
@SuppressWarnings("serial")
public class CDDisk implements Serializable{
	/*
	 * Attribute of disk entity.
	 */
	private int itemId;
	private String type;
	private String genre;
	private String name;
	
	public CDDisk() {
	}
	/**
	 * Construct CDDisk object from ResultSet
	 * @param rs result set of sql statement execution. 
	 * @throws SQLException if the columnLabel is not valid; 
	 * if a database access error occurs or this method is called on a closed result set
	 */
	public CDDisk(ResultSet rs) throws SQLException {
		setItemId(rs.getInt("id"));
		setType(rs.getString("type"));
		setGenre(rs.getString("genre"));
		setName(rs.getString("name"));
	}

	public int getItemId() {
		return itemId;
	}

	public String getType() {
		return type;
	}

	public String getGenre() {
		return genre;
	}

	public String getName() {
		return name;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CDDisk [itemId=" + itemId + ", type=" + type + ", genre="
				+ genre + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CDDisk other = (CDDisk) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (itemId != other.itemId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}	
}
