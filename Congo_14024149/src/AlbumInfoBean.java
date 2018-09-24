/* ----------------------------------------------------------------- *
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	15/12/2015
 *
 *  Album information bean for creation of albums and track listings
 *  for display purposes only.
 *
 *	Code formatted to the eclipse standard.
 * ------------------------------------------------------------------ */

import java.io.Serializable;

@SuppressWarnings("serial")
public class AlbumInfoBean implements Serializable {
	private int ID;
	private int recordID;
	private String albumTitle;
	private int duration;
	
	public AlbumInfoBean() {
    }
	
	public AlbumInfoBean(int id, int rec_id, String alb_title, int dur) {
		ID = id;
		recordID = rec_id;
		albumTitle = alb_title;
		duration = dur;
	}
	/* --------------------------------------------------------- *
	 * All getters and setters are instantiated below
	 * --------------------------------------------------------- */
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
