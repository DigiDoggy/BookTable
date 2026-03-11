package tablebook.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tablebook.backend.dto.request.ClientRecommendationRequest;
import tablebook.backend.dto.response.RestaurantTableResponse;
import tablebook.backend.service.TableService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class RestaurantTableController {

    private final TableService tableService;

    @GetMapping()
    public ResponseEntity<RestaurantTableResponse> getTables(@RequestBody ClientRecommendationRequest request) {
        return ResponseEntity.ok(tableService.getTables(request));
    }

}
