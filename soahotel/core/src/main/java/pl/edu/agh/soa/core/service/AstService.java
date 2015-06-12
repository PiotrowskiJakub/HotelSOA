package pl.edu.agh.soa.core.service;

import javax.ejb.Remote;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;

@Remote
public interface AstService {
	public AdditionalServiceType getAst();
	public void add(AdditionalServiceType ast);
}
