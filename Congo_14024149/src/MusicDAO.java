/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	16/12/2015
 *
 *  Servlet designed and used to access the database
 *  and performance of the required SQL queries.
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MusicDAO {
//	Declaration of requird variables
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet rs1 = null;

	public MusicDAO() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DBConnection.getInstance().getConnection();
		return conn;
	}
//	Method used for listing all albums with a greater stock level than 0
	public ArrayList<AlbumBean> findAllAlbums() {
		ArrayList<AlbumBean> Albums = null;
		try {
			String queryString = "SELECT * FROM music_recordings order by title";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Albums = new ArrayList<AlbumBean>();
			while (rs1.next()) {
				AlbumBean temp = new AlbumBean();
				int stk_cnt = rs1.getInt("stock_count");
				if (stk_cnt < 1)
				{
					continue;
				}else{
				temp.setRecordID(rs1.getInt("recording_id"));
				temp.setArtistName(rs1.getString("artist_name"));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setCategory(rs1.getString("category"));
				temp.setImage_name(rs1.getString("image_name"));
				temp.setNum_tracks(rs1.getInt("num_tracks"));
				temp.setPrice(rs1.getFloat("price"));
				temp.setStock_count(rs1.getInt("stock_count"));
				Albums.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Albums;
	}
//	Method used for listing all the albums with a specific category
	public ArrayList<String> findAllCategories() {
		ArrayList<String> Categories = null;
		try {
			String queryString = "SELECT * FROM music_categories";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Categories = new ArrayList<String>();
			while (rs1.next()) {
				String temp = null;
				temp = rs1.getString("name");
				Categories.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Categories;
	}
//	Method user for populating the drop down menu on the categories servlet
	public ArrayList<AlbumBean> getCategories(String category) {
		ArrayList<AlbumBean> Albums = null;
		try {

			String queryString = "SELECT * FROM music_recordings where category like '"
					+ category + "'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Albums = new ArrayList<AlbumBean>();
			while (rs1.next()) {
				AlbumBean temp = new AlbumBean();
				int stk_cnt = rs1.getInt("stock_count");
				if (stk_cnt < 1)
				{
					continue;
				}else{
				temp.setRecordID(rs1.getInt("recording_id"));
				temp.setArtistName(rs1.getString("artist_name"));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setCategory(rs1.getString("category"));
				temp.setImage_name(rs1.getString("image_name"));
				temp.setNum_tracks(rs1.getInt("num_tracks"));
				temp.setPrice(rs1.getFloat("price"));
				temp.setStock_count(rs1.getInt("stock_count"));
				Albums.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Albums;
	}
//	Method user for retrieving the tracks of a particular ablum
	public ArrayList<AlbumInfoBean> getAlbumInfo(int rec_id) {
		ArrayList<AlbumInfoBean> AlbumInfo = null;
		try {

			String queryString = "SELECT * FROM music_tracks WHERE recording_id ="
					+ rec_id;
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			AlbumInfo = new ArrayList<AlbumInfoBean>();
			while (rs1.next()) {
				AlbumInfoBean temp = new AlbumInfoBean();
				temp.setID(rs1.getInt("id"));
				temp.setRecordID(Integer.parseInt(rs1.getString("recording_id")));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setDuration(rs1.getInt("duration"));
				AlbumInfo.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return AlbumInfo;
	}
//	Method used for listing all albums within a particular price range
	public ArrayList<AlbumBean> getPrice(String price) {
		ArrayList<AlbumBean> Albums = null;
		try {

			String queryString = "SELECT * FROM music_recordings where price "
					+ price;
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Albums = new ArrayList<AlbumBean>();
			while (rs1.next()) {
				AlbumBean temp = new AlbumBean();
				int stk_cnt = rs1.getInt("stock_count");
				if (stk_cnt < 1)
				{
					continue;
				}else{
				temp.setRecordID(rs1.getInt("recording_id"));
				temp.setArtistName(rs1.getString("artist_name"));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setCategory(rs1.getString("category"));
				temp.setImage_name(rs1.getString("image_name"));
				temp.setNum_tracks(rs1.getInt("num_tracks"));
				temp.setPrice(rs1.getFloat("price"));
				temp.setStock_count(rs1.getInt("stock_count"));
				Albums.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Albums;
	}
//	Method used for retrieval of ablums from a specific artist
	public ArrayList<AlbumBean> getArtist(String artist) {
		ArrayList<AlbumBean> Albums = null;
		try {
			String queryString = "SELECT * FROM music_recordings where artist_name like '%"
					+ artist + "%'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Albums = new ArrayList<AlbumBean>();
			while (rs1.next()) {
				AlbumBean temp = new AlbumBean();
				int stk_cnt = rs1.getInt("stock_count");
				if (stk_cnt < 1)
				{
					continue;
				}else{
				temp.setRecordID(rs1.getInt("recording_id"));
				temp.setArtistName(rs1.getString("artist_name"));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setCategory(rs1.getString("category"));
				temp.setImage_name(rs1.getString("image_name"));
				temp.setNum_tracks(rs1.getInt("num_tracks"));
				temp.setPrice(rs1.getFloat("price"));
				temp.setStock_count(rs1.getInt("stock_count"));
				Albums.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Albums;
	}
//	Method used for the recovery of a recording ID of a particular album.
	@SuppressWarnings("finally")
	public int getRec_ID(String title, String artistName) {
		int rec_id = 0;
		try {
			String queryString = "SELECT recording_id FROM music_recordings where title = '"
					+ title + "' AND artist_name = '" + artistName + "'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();
			while (rs1.next()) {
				rec_id = rs1.getInt("recording_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rec_id;
		}
	}
//	Method used for the creation of an arrayList of albums within an order.
	public ArrayList<AlbumBean> CurrentOrder(ArrayList<String> albumArray) {
		ArrayList<AlbumBean> Albums = null;
		if ((albumArray != null) && (!albumArray.isEmpty())) {
			try {
				String selectSQL = "select * from music_recordings where ";
				for (int i = 0; i < albumArray.size(); i++) {
					if (i != 0) {
						selectSQL += " OR ";
					}
					selectSQL += "recording_id = '" + albumArray.get(i) + "'";
				}
				connection = getConnection();
				ptmt = connection.prepareStatement(selectSQL);
				rs1 = ptmt.executeQuery();

				Albums = new ArrayList<AlbumBean>();

				while (rs1.next()) {
					AlbumBean temp = new AlbumBean();
					temp.setRecordID(rs1.getInt("recording_id"));
					temp.setArtistName(rs1.getString("artist_name"));
					temp.setAlbumTitle(rs1.getString("title"));
					temp.setCategory(rs1.getString("category"));
					temp.setImage_name(rs1.getString("image_name"));
					temp.setNum_tracks(rs1.getInt("num_tracks"));
					temp.setPrice(rs1.getFloat("price"));
					temp.setStock_count(rs1.getInt("stock_count"));
					Albums.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs1 != null)
						rs1.close();
					if (ptmt != null)
						ptmt.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return Albums;
		} else {
			return Albums;
		}
	}
//	Method used for the removal of an album from an order
	public ArrayList<AlbumBean> RemoveFromOrder(int rec_id) {
		ArrayList<AlbumBean> Albums = null;
		try {
			String queryString = "SELECT * FROM music_recordings where recording_id = "
					+ rec_id;
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Albums = new ArrayList<AlbumBean>();
			while (rs1.next()) {
				AlbumBean temp = new AlbumBean();
				temp.setRecordID(rs1.getInt("recording_id"));
				temp.setArtistName(rs1.getString("artist_name"));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setCategory(rs1.getString("category"));
				temp.setImage_name(rs1.getString("image_name"));
				temp.setNum_tracks(rs1.getInt("num_tracks"));
				temp.setPrice(rs1.getFloat("price"));
				temp.setStock_count(rs1.getInt("stock_count"));
				Albums.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Albums;
	}
//	Method used for adding an album to the running order before checkout.
	public ArrayList<AlbumBean> AddToOrder(int rec_id) {
		ArrayList<AlbumBean> Albums = null;
		try {
			String queryString = "SELECT * FROM music_recordings where recording_id = "
					+ rec_id;
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			rs1 = ptmt.executeQuery();

			Albums = new ArrayList<AlbumBean>();
			while (rs1.next()) {
				AlbumBean temp = new AlbumBean();
				temp.setRecordID(rs1.getInt("recording_id"));
				temp.setArtistName(rs1.getString("artist_name"));
				temp.setAlbumTitle(rs1.getString("title"));
				temp.setCategory(rs1.getString("category"));
				temp.setImage_name(rs1.getString("image_name"));
				temp.setNum_tracks(rs1.getInt("num_tracks"));
				temp.setPrice(rs1.getFloat("price"));
				temp.setStock_count(rs1.getInt("stock_count"));
				Albums.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return Albums;
	}
//	Method used for adding a user to the database
	public int addUser(CustomerBean newUser) {
		int customerId = 0;
		try {
			String queryString = "INSERT INTO users(prefix, firstname, surname, housenumber, address1, address2, address3, town, county, postcode) VALUES(?,?,?,?,?,?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newUser.getPrefix());
			ptmt.setString(2, newUser.getFirst_Name());
			ptmt.setString(3, newUser.getLast_Name());
			ptmt.setInt(4, newUser.getHouse_number());
			ptmt.setString(5, newUser.getAddress1());
			ptmt.setString(6, newUser.getAddress2());
			ptmt.setString(7, newUser.getAddress3());
			ptmt.setString(8, newUser.getTown());
			ptmt.setString(9, newUser.getCounty());
			ptmt.setString(10, newUser.getPostCode());
			ptmt.executeUpdate();
			String query = "select userID from users where firstname = '"
					+ newUser.getFirst_Name() + "'";
			PreparedStatement ps = connection.prepareStatement(query);
			rs1 = ps.executeQuery();
			while (rs1.next()) {
				customerId = rs1.getInt("userID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return customerId;
	}
//	Method used for adding an order to the database
	public ArrayList<AlbumBean> addOrder(ArrayList<String> albumArray,
			int customer_id, int orderNumber, float orderTotal) {
		ArrayList<AlbumBean> Albums = null;

		if ((albumArray != null) && (!albumArray.isEmpty())) {
			try {
				String selectSQL = "select * from music_recordings where ";
				for (int i = 0; i < albumArray.size(); i++) {
					if (i != 0) {
						selectSQL += " OR ";
					}
					selectSQL += "recording_id = '" + albumArray.get(i) + "'";
				}
				connection = getConnection();
				ptmt = connection.prepareStatement(selectSQL);
				rs1 = ptmt.executeQuery();

				Albums = new ArrayList<AlbumBean>();

				while (rs1.next()) {
					AlbumBean temp = new AlbumBean();
					temp.setRecordID(rs1.getInt("recording_id"));
					temp.setArtistName(rs1.getString("artist_name"));
					temp.setAlbumTitle(rs1.getString("title"));
					temp.setCategory(rs1.getString("category"));
					temp.setImage_name(rs1.getString("image_name"));
					temp.setNum_tracks(rs1.getInt("num_tracks"));
					temp.setPrice(rs1.getFloat("price"));
					temp.setStock_count(rs1.getInt("stock_count"));
					Albums.add(temp);
				}

				String query = "INSERT INTO orders(artist_name, title, price, user_id, orderNumber) VALUES(?,?,"
						+ orderTotal + ",?,?)";
				PreparedStatement ps = connection.prepareStatement(query);
				for (int k = 0; k < Albums.size(); k++) {
					ps.setString(1, Albums.get(k).getArtistName());
					ps.setString(2, Albums.get(k).getAlbumTitle());
					ps.setInt(3, customer_id);
					ps.setInt(4, orderNumber);
					ps.addBatch();
				}
				ps.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs1 != null)
						rs1.close();
					if (ptmt != null)
						ptmt.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return Albums;
		} else {
			return Albums;
		}

	}
}