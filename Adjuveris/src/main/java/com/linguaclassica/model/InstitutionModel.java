package com.linguaclassica.model;

import java.io.Serializable;


/**
 * Business layer interface for EntityClassModel entity.
 */
public interface InstitutionModel extends Serializable {

	Integer getId();

	String getInstname();

	void setInstname(String instname);

	String getPostaddress();

	void setPostaddress(String postaddress);

	String getPrimecontact();

	void setPrimecontact(String primecontact);

	String getPrimephone();

	void setPrimephone(String primephone);

	String getPrimeemail();

	void setPrimeemail(String primeemail);
	
	String getAdminfirstname();
	
	void setAdminfirstname(String adminfirstname);
	
	String getAdminlastname();
	
	void setAdminlastname(String adminlastname);
	
	String getAdminemail();
	
	void setAdminemail(String adminemail);

}

