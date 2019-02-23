package com.linguaclassica.access;

public enum MenuItemEnum
{
	// Consultant English Menus
	COOVERVIEW("Overview"),
	CLIENTS("Clients"),
	TRAININGMATERIALS("Training Materials"),
	OTHERRESOURCES("Other Resources"),
	MESSAGING("Messaging"),
	ACCOUNT("My Account"),
	
	// Consultant French Menus
	COOVERVIEW_F("Aperçu"),
	COCLIENTS_F("Clients"),
	TRAININGMATERIALS_F("Matériel de formation"),
	CORESOURCES_F("Autres ressources"),
	//COMESSAGING("Messaging"),
	COMESSAGING("Messagerie"),
	COUSAGE_F("Usage"),
	COACCOUNT_F("Mon compte"),
	
	// Client English Menus
	CLOVERVIEW("Overview"),
	CLASSIGNMENTS("Assignments"),
	CLOTHERES("Other Resources"),
	CLMESSAGING("Messaging"),
	CLACCOUNT("My Account"),

	// Client French Menus
	CLOVERVIEW_F("Aperçu"),
	CLASSIGNMENTS_F("Affectations"),
	CLOTHERES_F("Autres ressources"),
	CLMESSAGING_F("Messagerie"),
	CLPARTICIPATION_F("Dossier de participation"),
	CLACCOUNT_F("Mon compte"),
	
	// Office Administrator Menus
	OAOVERVIEW("Overview"),
	OANOTIFICATIONS("Notifications"),
	OATRAININGMATL("Training Material"),
	OAEVALUATION("Evaluation"),
	OAOTHERRES("Other Resources"),
	OACONSULTANTS("Consultants"),
	OACONCLIENTS("Clients"),
	OAADMINISTRATOR("Administrators"),
	OAREPORTS("Reports"),
	OAMYACCOUNT("My Account"),
	OAACCOUNTS("All Accounts"),
	
	// Office Administrator French Menus
	OAOVERVIEW_F("Aperçu"),
	OANOTIFICATIONS_F("Notifications"),
	OATRAININGMATL_F("Matériel de formation"),
	OAEVALUATION_F("Évaluation"),
	OAOTHERRES_F("Autres ressources"),
	OACONSULTANTS_F("Consultants"),
	OACONCLIENTS_F("Clients"),
	OAADMINISTRATOR_F("Administrateurs"),
	OAREPORTS_F("Rapports"),
	OAMYACCOUNT_F("Mon compte"),
	OAACCOUNTS_F("Tous les comptes"),
	
	// System Administrator Menus
	SAORGANIZATIONS("Organizations"),
	
	// Public access menus
	LOGIN("Log In"),
	FORGOT_PASS("Forgot Password"),
	CONTACT_US("Contact Us"),
	
	// Public access French menus
	LOGIN_F("Se connecter"),
	FORGOT_PASS_F("Mot de passe oublié"),
	CONTACT_US_F("Contactez nous"),
    
    // Undefined menus
	EMPTYMENU("Empty Page"),
	EMPTYMENU_F("Page vide"),
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