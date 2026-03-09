package tablebook.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tablebook.backend.service.ReservationService;
import tablebook.backend.service.TableService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tables")
public class RestaurantTableController {

    private final TableService tableService;

    @GetMapping()
    public String getTables() {

    }

}
