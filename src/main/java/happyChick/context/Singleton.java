package happyChick.context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import happyChick.dao.DAOPoulailler;
import happyChick.dao.DAOPoule;
import happyChick.dao.DAOTemperament;
import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAOTemperament;
import happyChick.model.Insouciante;
import happyChick.model.MamanPoule;
import happyChick.model.Psychopathe;
import happyChick.model.Serieuse;

public class Singleton {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceJPA");
	
	private IDAOPoule daoCompte = new DAOPoule();
	private IDAOPoulailler daoEvenement= new DAOPoulailler();
	private IDAOTemperament daoParticipation = new DAOTemperament();	
	private Serieuse serieuse = new Serieuse();
	private MamanPoule mamanPoule = new MamanPoule();
	private Insouciante insouciante = new Insouciante();
	private Psychopathe psychopathe = new Psychopathe();
	
	
	private static Singleton instance;

	
	private Singleton() {}
	
	
	
	public static Singleton getInstance() {
		if(instance == null) 
		{
			instance=new Singleton();
		}
		
		return instance;
	}

	


	public Serieuse getSerieuse() {
		return serieuse;
	}



	public void setSerieuse(Serieuse serieuse) {
		this.serieuse = serieuse;
	}



	public MamanPoule getMamanPoule() {
		return mamanPoule;
	}



	public void setMamanPoule(MamanPoule mamanPoule) {
		this.mamanPoule = mamanPoule;
	}



	public Insouciante getInsouciante() {
		return insouciante;
	}



	public void setInsouciante(Insouciante insouciante) {
		this.insouciante = insouciante;
	}



	public Psychopathe getPsychopathe() {
		return psychopathe;
	}



	public void setPsychopathe(Psychopathe psychopathe) {
		this.psychopathe = psychopathe;
	}



	public static void setInstance(Singleton instance) {
		Singleton.instance = instance;
	}



	public IDAOPoule getDaoCompte() {
		return daoCompte;
	}



	public void setDaoCompte(IDAOPoule daoCompte) {
		this.daoCompte = daoCompte;
	}



	public IDAOPoulailler getDaoEvenement() {
		return daoEvenement;
	}



	public void setDaoEvenement(IDAOPoulailler daoEvenement) {
		this.daoEvenement = daoEvenement;
	}



	public IDAOTemperament getDaoParticipation() {
		return daoParticipation;
	}



	public void setDaoParticipation(IDAOTemperament daoParticipation) {
		this.daoParticipation = daoParticipation;
	}



	public EntityManagerFactory getEmf() {
		return emf;
	}



	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	
	
	


	
	
	
	

}
