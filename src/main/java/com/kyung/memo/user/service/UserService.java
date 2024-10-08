package com.kyung.memo.user.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyung.memo.common.MD5HashingEncoder;
import com.kyung.memo.user.domain.User;
import com.kyung.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String email){
		
		// 비밀번호 암호화
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.insertUser(loginId, encryptPassword, name, email);
	}
	
	public boolean isDuplicateId(String loginId) {
		int count = userRepository.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public User getUser(String loginId, String password){
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.selectUser(loginId, encryptPassword);
	}
}
