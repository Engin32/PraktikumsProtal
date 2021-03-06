package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import org.h2.command.dml.Set;

import Interface.Observerable;

public class Model implements Observerable {
	private static Model instance;

	Anmelden anmelden = new Anmelden();

	StelleSpeichern speichern = new StelleSpeichern();

	StelleLoeschen loeschen = new StelleLoeschen();

	EigeneStellenLaden laden = new EigeneStellenLaden();

	Registrierung registrieren = new Registrierung();

	PraktikaSuche suche = new PraktikaSuche();

	FirmenSuche firmensuche = new FirmenSuche();

	public Unternehmenloeschen getUntloeschen() {
		return untloeschen;
	}

	public void setUntloeschen(Unternehmenloeschen untloeschen) {
		this.untloeschen = untloeschen;
	}

	Unternehmenloeschen untloeschen = new Unternehmenloeschen();

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

	public StelleLoeschen getLoeschen() {
		return loeschen;
	}

	public void setLoeschen(StelleLoeschen loeschen) {
		this.loeschen = loeschen;
	}

	public StelleSpeichern getSpeichern() {
		return speichern;
	}

	public static Model getInstance() {
		if (instance == null) {

			instance = new Model();
		}

		return instance;
	}

	public Anmelden getAnmelden() {
		return anmelden;
	}

	HashSet<Observer> observers = new HashSet<Observer>();

	@Override
	public void addObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);

	}

	@Override
	public void notifyObservers() {
		System.out.println("----notify----");
		Collection<Observer> observers1 = getObservers();
		if (observers1 == null) {
			System.out.println("______Observer ist leer_____");
		} else {
			System.out.println("______Observer niicchcht ist leer_____");
		}
		for (Observer o : observers1) {
			o.update(new Observable(), "neu laden");

		}

	}

	public Collection<Observer> getObservers() {
		return observers;
	}

}
