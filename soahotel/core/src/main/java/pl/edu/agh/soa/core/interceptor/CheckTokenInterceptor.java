package pl.edu.agh.soa.core.interceptor;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import pl.edu.agh.soa.core.bean.Token;
import pl.edu.agh.soa.core.dao.TokenDAO;

@Interceptor
@CheckToken
public class CheckTokenInterceptor {

	private static final long TOKEN_VALIDITY_TIME = 10 * 60 * 1000;
	@EJB
	TokenDAO tokenDAO;

	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		HttpServletRequest req = getHttpServletRequest(ic);

		if (req == null) {
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
					.entity("There is no http request.").build();
		}

		// Get token from header
		String tokenString = req.getHeader("Token-Auth");
		Token token = tokenDAO.getToken(tokenString);

		if (token == null) {
			return Response
					.status(Response.Status.UNAUTHORIZED.getStatusCode())
					.entity("Bad token.").build();
		}

		Timestamp checkTimestamp = new Timestamp(token.getTimestamp().getTime()+ TOKEN_VALIDITY_TIME);
		Date now = new Date();

		// If now is after token creation time plus 10 minutes
		if (!checkTimestamp.after(now)) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode())
					.entity("Session expired.").build();
		}

		// update token
		token.setTimestamp(new Timestamp(now.getTime()));
		tokenDAO.updateToken(token);
		return ic.proceed();
	}

	private HttpServletRequest getHttpServletRequest(InvocationContext ic) {
		for (Object parameter : ic.getParameters()) {
			if (parameter instanceof HttpServletRequest) {
				return (HttpServletRequest) parameter;
			}
		}

		return null;
	}
}
