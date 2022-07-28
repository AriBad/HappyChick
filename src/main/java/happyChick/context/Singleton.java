package happyChick.context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import gEvent.dao.IDAOCompte;
import gEvent.dao.IDAOEvenement;
import gEvent.dao.IDAOIntervenant;
import gEvent.dao.IDAOParticipation;
import gEvent.dao.IDAOPrestation;
import gEvent.dao.IDAOSpectateur;
import happyChick.IDAO.IDAOPoulailler;
import happyChick.IDAO.IDAOPoule;
import happyChick.IDAO.IDAOTemperament;

public class Singleton {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceJPA");
	
	private IDAOPoule daoCompte = new DAOPoule();
	private IDAOPoulailler daoEvenement= new DAOPoulailler();
	private IDAOTemperament daoParticipation = new DAOTemperament();	
	
	
	private static Singleton instance;

	
	private Singleton() {}
	
	
	
	public static Singleton getInstance() {
		if(instance == null) 
		{
			instance=new Singleton();
		}
		
		return instance;
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
