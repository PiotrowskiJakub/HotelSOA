package pl.edu.agh.soa.core.dao;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.Token;

@Local
public interface TokenDAO {
	public boolean saveToken(Token token);
	public Token getToken(String mail);	
}
