
package com.sti.payment.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

public abstract class BaseImpl {
	@PersistenceContext
	protected EntityManager em;
	@PersistenceUnit
	protected EntityManagerFactory emf;
}
