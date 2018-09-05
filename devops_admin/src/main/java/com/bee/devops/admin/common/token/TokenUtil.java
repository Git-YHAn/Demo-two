package com.bee.devops.admin.common.token;

import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class TokenUtil {
	private static Logger logger = Logger.getLogger(TokenUtil.class);

	/**
	 * 存储token
	 * 
	 * @return
	 */
	public static String getToken(String json_py) {
		try {
			// The JWT signature algorithm we will be using to sign the token
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			// We will sign our JWT with our ApiKey secret
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(OpsSysParameterUtil.getLoginSigning());
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

			// Let's set the JWT Claims
			JwtBuilder builder = Jwts.builder().setPayload(json_py.toString()).signWith(signatureAlgorithm, signingKey);
			return builder.compact();
		} catch (Exception e) {
			logger.error("生成token异常", e);
			throw new ApplicationContextException("生成token异常" + e.getMessage());
		}
	}

	public static String refreshToken(String jwt) {
		if (StringUtils.isNotBlank(jwt) && jwt.split("\\.").length == 3) {
			String header = jwt.split("\\.")[0];
			String payload = jwt.split("\\.")[1];
			String sign = jwt.split("\\.")[2];// 带过来的签名
			String headerNew = getToken(Base64Codec.BASE64URL.decodeToString(payload)).split("\\.")[0];
			String signNew = getToken(Base64Codec.BASE64URL.decodeToString(payload)).split("\\.")[2];
			if (header.equals(headerNew) && sign.equals(signNew)) {// 进行安全校验（头部和签名）
				Claims claims = Jwts.parser()
						.setSigningKey(DatatypeConverter.parseBase64Binary(OpsSysParameterUtil.getLoginSigning()))
						.parseClaimsJws(jwt).getBody();
				if (claims != null) {
					TokenPayLoad payloadObj = JSONObject.parseObject(JSONObject.toJSONString(claims),
							TokenPayLoad.class);
					long now = System.currentTimeMillis();
					// 当前时间减上次过期时间小于超期时间时刷新token
					long expireTime = OpsSysParameterUtil.getLongProperty(OpsSysParameterUtil.LOGIN_EXPIRE_TIME);
					if (now - payloadObj.getExpDate() < expireTime * 1000) {
						payloadObj.setExpDate(now + OpsSysParameterUtil.getLoginTimeoutSeconds() * 1000);
						jwt = getToken(JSONObject.toJSONString(payloadObj));
						logger.info(
								"刷新用户[" + payloadObj.getUserid() + "-" + payloadObj.getUsername() + "]token成功：" + jwt);
					}
				}
			}
		}
		return jwt;
	}

	/**
	 * 判断是否token值看是否登录成功
	 * 
	 * @return
	 */
	public static TokenPayLoad parseToken(String jwt) {
		if (StringUtils.isNotBlank(jwt) && jwt.split("\\.").length == 3) {
			String header = jwt.split("\\.")[0];
			String payload = jwt.split("\\.")[1];
			String sign = jwt.split("\\.")[2];// 带过来的签名
			String headerNew = getToken(Base64Codec.BASE64URL.decodeToString(payload)).split("\\.")[0];
			String signNew = getToken(Base64Codec.BASE64URL.decodeToString(payload)).split("\\.")[2];
			if (header.equals(headerNew) && sign.equals(signNew)) {// 进行安全校验（头部和签名）
				Claims claims = Jwts.parser()
						.setSigningKey(DatatypeConverter.parseBase64Binary(OpsSysParameterUtil.getLoginSigning()))
						.parseClaimsJws(jwt).getBody();
				TokenPayLoad payloadObj = JSONObject.parseObject(JSONObject.toJSONString(claims), TokenPayLoad.class);
				if (claims != null) {
					long expdate = payloadObj.getExpDate();
					long nowMillis = System.currentTimeMillis();
					if (expdate > nowMillis) {// 判断token有效性
						return JSONObject.parseObject(JSONObject.toJSONString(claims), TokenPayLoad.class);
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		TokenPayLoad payload = new TokenPayLoad(1L, "admin1", "123456", "192.168.2.140");
		String token = TokenUtil.getToken(JSONObject.toJSONString(payload));
		System.out.println(payload.toString());
		System.out.println("token的值：" + token);
		// Thread.currentThread().sleep(timeout);
		TokenPayLoad params = TokenUtil.parseToken(token);
		System.out.println("返回参数params：" + params);

		String refreshToken = refreshToken(token);
		System.out.println("刷新后的token：" + refreshToken);
	}

}
