package model;

public class Model {
private static Model instance;



Anmelden anmelden = new Anmelden();

StelleSpeichern speichern = new StelleSpeichern();

StelleLöschen löschen = new StelleLöschen();

EigeneStellenLaden laden = new EigeneStellenLaden();

Registrierung registrieren = new Registrierung();

PraktikaSuche suche = new PraktikaSuche();


FirmenSuche firmensuche = new FirmenSuche();


public FirmenSuche getFirmensuche() {
	return firmensuche;
}



public void setFirmensuche(FirmenSuche firmensuche) {
	this.firmensuche = firmensuche;
}



public PraktikaSuche getSuche() {
	return suche;
}



public void setSuche(PraktikaSuche suche) {
	this.suche = suche;
}



public Registrierung getRegistrieren() {
	return registrieren;
}



public void setRegistrieren(Registrierung registrieren) {
	this.registrieren = registrieren;
}



public EigeneStellenLaden getLaden() {
	return laden;
}



public void setLaden(EigeneStellenLaden laden) {
	this.laden = laden;
}



public StelleLöschen getLöschen() {
	return löschen;
}



public void setLöschen(StelleLöschen löschen) {
	this.löschen = löschen;
}



public StelleSpeichern getSpeichern() {
	return speichern;
}



public static Model getInstance() {
	if(instance==null){
		
		instance = new Model();
	}
	
	return instance;
}


public Anmelden getAnmelden() {
	return anmelden;
}






}
