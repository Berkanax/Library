package pl.sda.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.library.model.Magazine;
import pl.sda.library.repository.MagazineRepository;

@Controller
public class MagazineController {

    private final MagazineRepository magazineRepository;

    public MagazineController(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @PostMapping("/addMagazine")
    public String addMagazine(@RequestParam String magazineTitle, @RequestParam String magazinePublisher, @RequestParam String magazineLanguage, @RequestParam String magazineDate) {
        String[] dateParts = magazineDate.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        Magazine magazine = new Magazine(magazineTitle, magazinePublisher, magazineLanguage, year, month, day);
        magazineRepository.save(magazine);
        return "redirect:/welcome";
    }
}
