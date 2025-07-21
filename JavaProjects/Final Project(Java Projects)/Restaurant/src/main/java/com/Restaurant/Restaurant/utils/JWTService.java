package com.Restaurant.Restaurant.utils;

import com.Restaurant.Restaurant.Model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JWTService {

	private String secretKey = null;

	public JWTService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSha256");
			SecretKey sk = keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
			//log.info("JWT secret key generated successfully");
		} catch (NoSuchAlgorithmException e) {
			//log.error("Error generating JWT secret key", e);
			e.printStackTrace();
		}
	}

	public String generateToken(String username) {
		return generateToken(username, Role.USER);
	}

	public String generateToken(String username, Role role) {
		//log.info("Generating token for user: {} with role: {}", username, role.name());
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role.name());
		String token = Jwts.builder().claims().add(claims).subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).and().signWith(getKey())
				.compact();
		//log.info("Token generated successfully for user: {}", username);
		return token;
	}

	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsernameFromToken(String token) {
		//log.debug("Extracting username from token");
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		//log.debug("Extracting all claims from token");
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsernameFromToken(token);
		boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		//log.info("Token validation for user {}: {}", username, isValid ? "valid" : "invalid");
		return isValid;
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}

//
//package com.Restaurant.Restaurant.utils;
//
//import com.Restaurant.Restaurant.Model.Role;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//@Slf4j
//public class JWTService {
//
//	// ✅ Fixed key to ensure consistent token validation across server restarts
//	private static final String SECRET_KEY = "this-is-a-very-strong-secret-key-1234567890";
//
//	/**
//	 * Generates a JWT token with default role USER
//	 */
//	public String generateToken(String username) {
//		return generateToken(username, Role.USER);
//	}
//
//	/**
//	 * ✅ Generates a JWT token with username and role as claims
//	 */
//	public String generateToken(String username, Role role) {
//		Map<String, Object> claims = new HashMap<>();
//		claims.put("role", role.name()); // include role
//
//		String token = Jwts.builder()
//				.claims().add(claims)
//				.subject(username)
//				.issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//				.and()
//				.signWith(getKey())
//				.compact();
//
//		log.info("Generated JWT token for user: {}, role: {}", username, role);
//		log.debug("Token: {}", token); // you may hide this in prod
//
//		return token;
//	}
//
//	/**
//	 * ✅ Build the secret key from static string
//	 */
//	private SecretKey getKey() {
//		byte[] keyBytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()));
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
//
//	/**
//	 * ✅ Extract username from token
//	 */
//	public String extractUsernameFromToken(String token) {
//		String username = extractClaim(token, Claims::getSubject);
//		log.debug("Extracted username from token: {}", username);
//		return username;
//	}
//
//	/**
//	 * Generic method to extract any claim
//	 */
//	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = extractAllClaims(token);
//		return claimsResolver.apply(claims);
//	}
//
//	/**
//	 * ✅ Parses the JWT token and extracts all claims
//	 */
//	public Claims extractAllClaims(String token) {
//		try {
//			Claims claims = Jwts.parser()
//					.verifyWith(getKey())
//					.build()
//					.parseSignedClaims(token)
//					.getPayload();
//
//			log.debug("Extracted claims: {}", claims);
//			return claims;
//
//		} catch (Exception e) {
//			log.error("Invalid or expired JWT token: {}", e.getMessage());
//			throw e; // rethrow to let filter handle it
//		}
//	}
//
//	/**
//	 * ✅ Validates the token with the userDetails
//	 */
//	public boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsernameFromToken(token);
//		boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//
//		log.info("Token validation for {}: {}", username, isValid ? "VALID" : "INVALID");
//		return isValid;
//	}
//
//	/**
//	 * Checks token expiration
//	 */
//	private boolean isTokenExpired(String token) {
//		Date expiry = extractExpiration(token);
//		boolean expired = expiry.before(new Date());
//		log.debug("Token expired: {}", expired);
//		return expired;
//	}
//
//	/**
//	 * Extract expiration date from token
//	 */
//	private Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//}
