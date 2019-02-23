package com.linguaclassica.student;

import com.linguaclassica.shared.SwitchPermissionPanel;

public class StudentSwitchPermissionPage extends StudentBasePage
{
	private static final long serialVersionUID = 1L;
	
	public StudentSwitchPermissionPage()
	{
		super();
		
		SwitchPermissionPanel spp = new SwitchPermissionPanel();
		add(spp);
	}
}
