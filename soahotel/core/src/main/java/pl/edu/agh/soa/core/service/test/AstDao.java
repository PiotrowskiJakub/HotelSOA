package pl.edu.agh.soa.core.service.test;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;

@Local
public interface AstDao {
	public void add(AdditionalServiceType ast);
}
