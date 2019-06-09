package util;

import entity.TokenCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/2
 * util
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {

	private String key ;

	private long ttl ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	/**
	 * 生成JWT
	 *
	 * @param id id
	 * @param subject name
	 * @return String jwt
	 */
	public String createJWT(String id, String subject, String roles) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setId(id)
				.setSubject(subject)
				.setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, key).claim(TokenCode.ROLES, roles);
		if (ttl > 0) {
			builder.setExpiration( new Date( nowMillis + ttl));
		}
		System.out.println("TOKEN -------- "+builder.compact());
		return builder.compact();
	}

	/**
	 * 解析JWT
	 * @param jwtStr jwt
	 * @return Claims
	 */
	public Claims parseJWT(String jwtStr){
		System.out.println("解析后  "+Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwtStr)
				.getBody());

		return  Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwtStr)
				.getBody();
	}

}
