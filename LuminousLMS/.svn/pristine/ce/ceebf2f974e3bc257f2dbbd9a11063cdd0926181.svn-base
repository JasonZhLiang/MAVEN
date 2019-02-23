package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.OrgModel;

@Entity
@Table(name="orgs")
public class EntityOrgModel implements OrgModel {

	private static final long serialVersionUID = 6331128951228340173L;

	@Id
	@GeneratedValue()
	private Integer id;
		
	private String orgname;
	
	private String orgemail;
		
	public EntityOrgModel() {
		}

	/**
	 * Normal constructor used to create a org
	 */
	public EntityOrgModel(String orgname, String orgemail) {
			this.orgname = orgname;
			this.orgemail = orgemail;
		}

		@Override
		public Integer getId() {
		return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}	 

		@Override
		public String getOrgname() {
			return orgname;
		}

		@Override
		public void setOrgname(String orgname) {
			this.orgname = orgname;
		}

		@Override
		public String getOrgemail() {
			return orgemail;
		}

		@Override
		public void setOrgemail(String orgemail) {
			this.orgemail = orgemail;
		}
	}
