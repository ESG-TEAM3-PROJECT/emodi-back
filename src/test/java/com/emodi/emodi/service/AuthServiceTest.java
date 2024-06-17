package com.emodi.emodi.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emodi.emodi.entity.User;
import com.emodi.emodi.service.dto.SignupInfoRequest;

@SpringBootTest
class AuthServiceTest {
	@Autowired
	private AuthService authService;

	@Test
	public void signup() {
		// given
		SignupInfoRequest request = new SignupInfoRequest("aaa123", "qwer1234");

		// when
		User user = authService.signUp(request);

		// then
		assertThat(user.getUsername()).isEqualTo(request.username());
	}
}
