package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.PanelsModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="panels")
public class EntityPanelsModel implements PanelsModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private PanelType panel;
	
	public EntityPanelsModel() {
	}

	public EntityPanelsModel(Integer id, PanelType panel) {
		this.id = id;
		this.panel = panel;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public PanelType getPanel() {
		return panel;
	}

	@Override
	public void setPanel(PanelType panel) {
		this.panel = panel;
	}
}




