package edu.mum.ea.socialmedia.security;

import java.util.Collection;

import edu.mum.ea.socialmedia.model.User;
import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String email;
	private User user;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String email, User user, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.email = email;
		this.user = user;
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}