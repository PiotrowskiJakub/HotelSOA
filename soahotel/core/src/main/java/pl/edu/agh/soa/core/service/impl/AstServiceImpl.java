package pl.edu.agh.soa.core.service.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;
import pl.edu.agh.soa.core.dao.AstDao;
import pl.edu.agh.soa.core.service.AstService;

@Stateless
public class AstServiceImpl implements AstService {
	@EJB
	AstDao astDao;

	@Override
	public AdditionalServiceType getAst() {
		return new AdditionalServiceType(new Long(1), "Nazwa", "Opis", new BigDecimal(12.3));
	}
	
	@Override
	public void add(AdditionalServiceType ast) {
		astDao.add(ast);
	}
}
