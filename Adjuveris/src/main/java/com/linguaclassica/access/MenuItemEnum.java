package com.linguaclassica.access;

public enum MenuItemEnum
{
    // Possible Institution Administrator menu enums
	OVERVIEW("Overview"),
	TERMS("Terms"),
	CLASSES("Classes"),
	TEACHER("Teacher"),
	STUDENT("Student"),
	TA("TA"),
	REPORTS("Reports"),
	INSTPREFS("Inst Prefs"),

	
	// Student Menus
	SNOTIFICATIONS("Notifications"),
	PRACTICEX("Practice Exercises"),
	SRESULTS("Assignment Results"),
	SPROFICIENCIES("Proficiencies"),
	SACCOUNT("My Account"),
	
	// Teacher Menus
	TENOTIFICATIONS("Notifications"),
	TEXERCISES("Exercises"),
	TEASSIGNMENTS("Assignments"),
	GROUPS("Groups"),
	TERESULTS("Assignment Results"),
	TEPROFICIENCIES("Proficiencies"),
	TEACCOUNT("My Account"),
	
	// TA Menus
	TANOTIFICATIONS("Notifications"),
	TAASSIGNMENTS("Assignments"),
	TARESULTS("Assignment Results"),
	TAPROFICIENCIES("Proficiencies"),
	TAACCOUNT("My Account"),
    
    // Undefined menus
    NONE(""),
    ERROR("Error test");

    private String label;

    private MenuItemEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}