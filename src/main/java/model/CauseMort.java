package model;

import javax.persistence.Embeddable;

@Embeddable
public enum CauseMort {
	Faim, Meurtre, Predation, Maladie, Age
}
