package cpb.dwh_bi_api.features.user.service.impl;

import cpb.dwh_bi_api.features.user.dto.request.CreateUserRequest;
import cpb.dwh_bi_api.features.user.dto.request.UpdateUserRequest;
import cpb.dwh_bi_api.features.user.dto.response.UserResponse;
import cpb.dwh_bi_api.features.user.mapper.UserMapper;
import cpb.dwh_bi_api.features.user.models.User;
import cpb.dwh_bi_api.features.user.repository.UserRepository;
import cpb.dwh_bi_api.features.user.service.UserService;
import cpb.dwh_bi_api.shared.exception.ResourceNotFoundException;
import cpb.dwh_bi_api.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final JwtTokenUtil jwtTokenUtil;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> getAll() {
		log.info("Fetching all users");
		return userRepository.findAll()
			.stream()
			.map(userMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse getById(UUID id) {
		log.info("Fetching user by id: {}", id);
		User user = userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return userMapper.toResponse(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse getByUsername(String username) {
		log.info("Fetching user by username: {}", username);
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
		return userMapper.toResponse(user);
	}

	@Override
	public UserResponse create(CreateUserRequest request) {
		log.info("Creating new user: {}", request.getUsername());
		User user = userMapper.toEntity(request);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		User saved = userRepository.save(user);
		return userMapper.toResponse(saved);
	}

	@Override
	public UserResponse update(UUID id, UpdateUserRequest request) {
		log.info("Updating user with id: {}", id);
		User user = userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		userMapper.updateEntity(request, user);
		User updated = userRepository.save(user);
		return userMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting user with id: {}", id);
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
		userRepository.deleteById(id);
	}

	@Override
	public UserResponse resetPassword(UUID id, String newPassword) {
		log.info("Resetting password for user id: {}", id);
		User user = userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		user.setPassword(passwordEncoder.encode(newPassword));
		User updated = userRepository.save(user);
		return userMapper.toResponse(updated);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse getProfile(String token) {
		log.info("Fetching user profile from token");
		String username = jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", ""));
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
		return userMapper.toResponse(user);
	}
}
