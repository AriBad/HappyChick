package happyChick.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import happyChick.context.Singleton;
import happyChick.model.Temperament;

public class DAOTemperament implements IDAOTemperament{

	@Override
	public Temperament findById(Integer id) {
		Temperament t = null;
		EntityManager em = null;
		try {
			em = Singleton.getInstance().getEmf().createEntityManager();
			t = em.find(Temperament.class, id);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {
			if(em!=null) {em.close();}
		}

		return t;
	}

	@Override
	public List<Temperament> findAll() {
		List<Temperament> temparaments =new ArrayList();
		EntityManager em = null;
		try {

			em = Singleton.getInstance().getEmf().createEntityManager();
			temparaments = em.createQuery("from Temperament").getResultList();
		}catch(Exception e) {e.printStackTrace();}
		finally {
			if(em!=null) 
			{
				em.close();
			}
		}
		return temparaments;
	}

	@Override
	public Temperament save(Temperament t) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try 
		{
			em = Singleton.getInstance().getEmf().createEntityManager();
			tx=em.getTransaction();
			tx.begin();
			t = em.merge(t);
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		finally 
		{
			if(em!=null) 
			{
				em.close();
			}
		}
		return t;
	}

	@Override
	public void delete(Integer id) {	

		EntityManager em = null;
		EntityTransaction tx = null;
		try 
		{
			em = Singleton.getInstance().getEmf().createEntityManager();
			tx=em.getTransaction();
			tx.begin();
			Temperament t = em.find(Temperament.class, id);
			em.remove(t);
			tx.commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		finally 
		{
			if(em!=null) 
			{
				em.close();
			}
		}

	}

}
