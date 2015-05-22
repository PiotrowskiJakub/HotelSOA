package pl.edu.agh.soa.core.service.test;

import javax.ejb.Remote;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;

@Remote
public interface AstService {
	public void add(AdditionalServiceType ast);
}
