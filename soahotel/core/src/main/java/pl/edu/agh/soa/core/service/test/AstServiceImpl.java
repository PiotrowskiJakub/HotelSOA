package pl.edu.agh.soa.core.service.test;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.AdditionalServiceType;

@Stateless
public class AstServiceImpl implements AstService {
	@EJB
	AstDao astDao;

	@Override
	public void add(AdditionalServiceType ast) {
		astDao.add(ast);
	}
}
