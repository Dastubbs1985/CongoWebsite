/*-----------------------------------------------------------------
 *  Author:         Dale Stubbs
 *	MMU Id:			14024149
 *  Written:    	19/11/2015
 *  Last updated:	15/12/2015
 *
 *  Bean used to create a customer as an object.
 *
 *	Code formatted to the eclipse standard.
 *------------------------------------------------------------------*/

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerBean implements Serializable {

	private int User_id;
	private String prefix;
	private String First_Name;
	private String Last_Name;
	private int house_number;
	private String address1;
	private String address2;
	private String address3;
	private String town;
	private String county;
	private String postCode;

	public CustomerBean() {
	}

	public CustomerBean(int usrId, String prefix, String fir_nme,
			String surname, int ho_num, String add1, String add2, String add3,
			String twn, String ctny, String p_Code) {
		User_id = usrId;
		this.prefix = prefix;
		First_Name = fir_nme;
		Last_Name = surname;
		house_number = ho_num;
		address1 = add1;
		address2 = add2;
		address3 = add3;
		town = twn;
		county = ctny;
		postCode = p_Code;
	}
	/*---------------------------------------------------------
	 * All getters and setters are instantiated below
	 ---------------------------------------------------------*/
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public int getHouse_number() {
		return house_number;
	}
	public void setHouse_number(int house_number) {
		this.house_number = house_number;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}