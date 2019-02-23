package com.linguaclassica.teacher;

import com.linguaclassica.shared.SwitchPermissionPanel;

public class TeacherSwitchPermissionPage extends TeacherBasePage
{
	private static final long serialVersionUID = 1L;

	public TeacherSwitchPermissionPage()
	{
		super();
		
		SwitchPermissionPanel spp = new SwitchPermissionPanel();
		add(spp);
	}
}
