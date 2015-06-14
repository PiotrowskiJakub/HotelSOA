package pl.edu.agh.soa.core.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Token;
import pl.edu.agh.soa.core.dao.TokenDAO;
import pl.edu.agh.soa.core.service.LoginService;

@Stateless
public class LoginServiceImpl implements LoginService {
	
	@EJB
	TokenDAO tokenDAO;
	
	@Override
	public Token createToken() {
		Token token = new Token();
		token.setTimestamp(new Timestamp(Calendar.getInstance().getTime().getTime()));
		token.setToken(UUID.randomUUID().toString());
		tokenDAO.saveToken(token);
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		return tokenDAO.checkToken(token);
	}

}
