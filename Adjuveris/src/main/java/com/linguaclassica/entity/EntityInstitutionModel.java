package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.InstitutionModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="institutions")
public class EntityInstitutionModel implements InstitutionModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private String instname;
	private String postaddress;
	private String primecontact;
	private String primephone;
	private String primeemail;
	private String adminfirstname;
	private String adminlastname;
	private String adminemail;
	
	public EntityInstitutionModel() {
	}

	public EntityInstitutionModel(Integer id, String instname, String postaddress, String primecontact, 
			String primephone, String primeemail, String adminfirstname, String adminlastname, 
			String adminemail) {
		this.id = id;
		this.instname = instname;
		this.postaddress = postaddress;
		this.primecontact = primecontact;
		this.primephone = primephone;
		this.primeemail = primeemail;
		this.adminfirstname = adminfirstname;
		this.adminlastname = adminlastname;
		this.adminemail = adminemail;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getInstname() {
		return instname;
	}

	@Override
	public void setInstname(String instname) {
		this.instname = instname;
	}

	@Override
	public String getPostaddress() {
		return postaddress;
	}

	@Override
	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}

	@Override
	public String getPrimecontact() {
		return primecontact;
	}

	@Override
	public void setPrimecontact(String primecontact) {
		this.primecontact = primecontact;
	}

	@Override
	public String getPrimephone() {
		return primephone;
	}

	@Override
	public void setPrimephone(String phone) {
		this.primephone = phone;
	}

	@Override
	public String getPrimeemail() {
		return primeemail;
	}

	@Override
	public void setPrimeemail(String primeemail) {
		this.primeemail = primeemail;
	}

	@Override
	public String getAdminfirstname() {
		return adminfirstname;
	}

	@Override
	public void setAdminfirstname(String adminfirstname) {
		this.adminfirstname = adminfirstname;
	}

	@Override
	public String getAdminlastname() {
		return adminlastname;
	}

	@Override
	public void setAdminlastname(String adminlastname) {
		this.adminlastname = adminlastname;
	}

	@Override
	public String getAdminemail() {
		return adminemail;
	}

	@Override
	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
}



