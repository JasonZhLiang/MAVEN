package com.linguaclassica.instadmin;

import com.linguaclassica.shared.SwitchPermissionPanel;

public class IASwitchPermissionPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;

	public IASwitchPermissionPage()
	{
		super();
		
		SwitchPermissionPanel spp = new SwitchPermissionPanel();
		add(spp);
	}
}
