package br.com.empresa.sgt.persistence.arq;

import java.util.List;

public interface GenericDao<T, ID> {
	
	public static final String CONDICAO_IGUAL = "=";
	
	public void persist(T obj);
	
	public void remove(T obj) ;
	
	public T merge(T obj);
	
	public void refresh(T obj);
	
	public List<T> findAll();
	
	public T findById(ID id);
	
	public T findMaxField(String field);
	
	public T findMinId(ID id);
	
	public T findOneByField(String field, String codition, Object Value);
	
}
