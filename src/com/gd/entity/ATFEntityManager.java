package com.gd.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Wrapper around EntityManager class to provide static access.
 * 
 * @author dhuang
 *
 */
public class ATFEntityManager 
{
	//Logger logger = LoggerFactory.getLogger(ATFEntityManager.class);
	
	private EntityManagerFactory factory;
	private EntityManager em;
	private String env;
	
	///////////////////////////
	//  Constructors         //
	///////////////////////////
	
	public ATFEntityManager(String oEnv)
	{
		env = oEnv;
		factory = Persistence.createEntityManagerFactory(env);
		em = factory.createEntityManager();
	}

	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	public Query createNativeQuery(java.lang.String sSQL, java.lang.Class resultClass)
	{
		return em.createNativeQuery(sSQL, resultClass);
	}
	
	public Query createNativeQuery(java.lang.String sSQL)
	{
		return em.createNativeQuery(sSQL);
	}
	
	public Query createQuery(java.lang.String sJPQL)
	{
		return em.createQuery(sJPQL);
	}
	
	public Object find(java.lang.Class entityClass, java.lang.Object primaryKey)
	{
		return em.find(entityClass, primaryKey);
	}
	
	public void close()
	{
		if (factory != null && factory.isOpen())
			factory.close();
	}
	
	public void open(String oEnv)
	{
		close();
		
		factory = Persistence.createEntityManagerFactory(oEnv);
		em = factory.createEntityManager();
	}

	
	public void begin()
	{
		em.getTransaction().begin();
	}
	
	public void commit()
	{
		em.getTransaction().commit();
	}
	
	public void rollBack()
	{
		em.getTransaction().rollback();
	}
	
	public void persist(Object oEntity)
	{
		em.persist(oEntity);
	}
	
	public void refresh(Object oEntity)  // This method will not refresh entities in relationships.  Each entity is refreshed separately.  e.g., plastic in consumer.
	{
		em.refresh(oEntity);
	}
	
	public static ATFEntityManager getEntityManager(String oEnv)
	{
		return new ATFEntityManager(oEnv);
	}

	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}
}
