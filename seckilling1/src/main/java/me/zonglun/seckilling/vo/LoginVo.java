package me.zonglun.seckilling.vo;

import javax.validation.constraints.NotNull;

import me.zonglun.seckilling.validator.IsMoblie;
import org.hibernate.validator.constraints.Length;


public class LoginVo {
	
	@NotNull

    @IsMoblie
	private String mobile;
	
	@NotNull
	@Length(min=32)
	private String password;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
	}
}
