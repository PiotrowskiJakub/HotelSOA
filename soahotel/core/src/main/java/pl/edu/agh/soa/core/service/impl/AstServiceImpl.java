package pl.edu.agh.soa.core.service.impl;

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
	public void add(AdditionalServiceType ast) {
		astDao.add(ast);
	}
}
