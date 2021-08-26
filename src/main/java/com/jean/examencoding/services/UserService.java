package com.jean.examencoding.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jean.examencoding.models.User;
import com.jean.examencoding.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//========================
	// REGISTRO DE UN USUARIO
	//=======================
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}
	
	//========================
	// ENCONTRAR USUARIO POR EMAIL
	//========================
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	//========================
	// ENCONTRAR USUARIO POR NOMBRE
	//========================
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
	
	//========================
	// ENCONTRAR USUARIO POR ID
	//========================
	public User findUserById(Long id) {
		Optional<User> u = userRepository.findById(id);
		
		if(u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}
	
	//================================
	// -------------------------
	//================================
	public boolean authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return false;
		} else {
			if(BCrypt.checkpw(password,  user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
}
