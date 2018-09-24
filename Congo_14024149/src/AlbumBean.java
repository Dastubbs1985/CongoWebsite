/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	15/12/2015
 *
 *  Bean used to create Albums as an object for addition to the tables 
 *  and the order as well as removal from the orders.
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.io.Serializable;

@SuppressWarnings("serial")
public class AlbumBean implements Serializable {

	private int recordID;
	private String artistName;
	private String albumTitle;
	private String category;
	private String image_name;
	private int num_tracks;
	private float price;
	private int stock_count;

	public AlbumBean() {
	}

	public AlbumBean(int rec_id, String art_nme, String alb_title, String cat,
			String img, int num_tra, float pri, int stock) {
		recordID = rec_id;
		artistName = art_nme;
		albumTitle = alb_title;
		category = cat;
		image_name = img;
		num_tracks = num_tra;
		price = pri;
		stock_count = stock;
	}

	/*---------------------------------------------------------
	 * All getters and setters are instantiated below
	 ---------------------------------------------------------*/
	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public int getNum_tracks() {
		return num_tracks;
	}

	public void setNum_tracks(int num_tracks) {
		this.num_tracks = num_tracks;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock_count() {
		return stock_count;
	}

	public void setStock_count(int stock_count) {
		this.stock_count = stock_count;
	}

}
