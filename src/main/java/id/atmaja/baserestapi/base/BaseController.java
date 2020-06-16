package id.atmaja.baserestapi.base;

import id.atmaja.baserestapi.config.exception.InvalidTokenException;
import id.atmaja.baserestapi.config.exception.TokenException;
import id.atmaja.baserestapi.config.exception.TokenExpiredException;
import id.atmaja.baserestapi.utils.JwtTokenUtil;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    protected String authenticate(String token) throws TokenException {
        if (token == null || "".equals(token)) {
            throw new InvalidTokenException();
        }

        String tokenData = JwtTokenUtil.getTokenData(token);

        if (tokenData == null) {
            throw new TokenExpiredException();
        }

        return tokenData;
    }
}
