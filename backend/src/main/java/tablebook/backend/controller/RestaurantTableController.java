package tablebook.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tablebook.backend.entity.RestaurantTable;
import tablebook.backend.service.TableService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tables")
public class RestaurantTableController {

    private final TableService tableService;

    @GetMapping()
    public List<RestaurantTable> getTables(LocalDate date, String time) {
        return tableService.getTables();
    }

}
