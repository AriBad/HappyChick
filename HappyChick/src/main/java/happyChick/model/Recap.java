package happyChick.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.model.jsonview.JsonViews;

@Entity
public class Recap {
	@JsonView(JsonViews.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@JsonView(JsonViews.Base.class)
	@ManyToOne
	private Poulailler poulailler;
	
	@JsonView(JsonViews.Base.class)
	private int saison;
	
	@JsonView(JsonViews.Base.class)
	private String listeRecapCourts;
	
	@JsonView(JsonViews.Base.class)
	@Lob
	private String listeRecapLongs;

	public Recap() {
	}

	public Recap(Poulailler poulailler, int saison, String listeRecapCourts, String listeRecapLongs) {

		this.poulailler = poulailler;
		this.saison = saison;
		this.listeRecapCourts = listeRecapCourts;
		this.listeRecapLongs = listeRecapLongs;
	}



	public Poulailler getPoulailler() {
		return poulailler;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPoulailler(Poulailler poulailler) {
		this.poulailler = poulailler;
	}

	public int getSaison() {
		return saison;
	}

	public void setSaison(int saison) {
		this.saison = saison;
	}

	
	public String getListeRecapCourts() {
		return listeRecapCourts;
	}

	public void setListeRecapCourts(String listeRecapCourts) {
		this.listeRecapCourts = listeRecapCourts;
	}

	public String getListeRecapLongs() {
		return listeRecapLongs;
	}

	public void setListeRecapLongs(String listeRecapLongs) {
		this.listeRecapLongs = listeRecapLongs;
	}

	@Override
	public String toString() {
		return "Recap [poulailler=" + poulailler + ", saison=" + saison + ", listeRecapCourts=" + listeRecapCourts
				+ ", listeRecapLongs=" + listeRecapLongs + "]";
	}


}
