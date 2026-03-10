package tablebook.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


}
