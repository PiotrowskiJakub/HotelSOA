package pl.edu.agh.soa.core.dao;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;

@Local
public interface AstDao {
	public void add(AdditionalServiceType ast);
}
