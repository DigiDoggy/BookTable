package tablebook.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tablebook.backend.repository.RestaurantTableRepository;

@Service
@RequiredArgsConstructor
public class TableService {
    private final RestaurantTableRepository tableRepository;


}
