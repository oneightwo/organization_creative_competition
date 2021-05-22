package com.organization_creative_competition.resource;

import com.organization_creative_competition.dto.CompetitionDto;
import com.organization_creative_competition.dto.ParticipantDto;
import com.organization_creative_competition.dto.UserDto;
import com.organization_creative_competition.model.Competition;
import com.organization_creative_competition.model.Participant;
import com.organization_creative_competition.model.User;
import com.organization_creative_competition.security.SecurityUtils;
import com.organization_creative_competition.service.CompetitionService;
import com.organization_creative_competition.service.ParticipantService;
import com.organization_creative_competition.service.ReportService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class CompetitionResource {

    private final MapperFacade mapperFacade;
    private final CompetitionService competitionService;
    private final ParticipantService participantService;
    private final ReportService reportService;

    @GetMapping("/competitions")
    private String getActiveCompetitions(Model model) {
        List<CompetitionDto> competitionDtos = mapperFacade.mapAsList(competitionService.getActiveCompetitions(),
                CompetitionDto.class);
        model.addAttribute("competitions", competitionDtos);
        return "competitions";
    }

    @GetMapping("/administration")
    private String administration(Model model) {
        model.addAttribute("competitions", mapperFacade.mapAsList(competitionService.getActiveCompetitionsByCreated(),
                CompetitionDto.class));
        return "administration";
    }

    @PostMapping("/administration/competitions")
    private String createAdministrationCompetitions(@ModelAttribute("competition") CompetitionDto competitionDto) {
        competitionService.save(mapperFacade.map(competitionDto, Competition.class));
        return "redirect:/administration";
    }

    @DeleteMapping("/administration/competitions/{id}")
    private void deleteCompetitions(@PathVariable("id") Long id) {
        competitionService.delete(id);
    }

    @GetMapping("/administration/competitions/edit/{id}")
    private String getAdministrationCompetitions(@PathVariable("id") Long id, Model model) {
        model.addAttribute("competition", mapperFacade.map(competitionService.getById(id), CompetitionDto.class));
        return "editCompetition";
    }

    @GetMapping("/administration/competitions/create")
    private String createAdministrationCompetitions(Model model) {
        model.addAttribute("competition", new CompetitionDto());
        return "createCompetition";
    }

    @PostMapping("/administration/competitions/edit/{id}")
    private String updateAdministrationCompetitions(@PathVariable("id") Long id,
                                                    @ModelAttribute("competition") CompetitionDto competitionDto) {
        competitionService.update(mapperFacade.map(competitionDto, Competition.class));
        return "redirect:/administration";
    }

    @GetMapping("/administration/competitions/{id}/participants")
    private String getAdministrationParticipants(@PathVariable("id") Long id, Model model) {
        model.addAttribute("competition", mapperFacade.map(competitionService.getById(id), CompetitionDto.class));
        model.addAttribute("participants", mapperFacade.mapAsList(participantService.getAllByCompetitionId(id), ParticipantDto.class));
        return "competition";
    }

    @GetMapping("/administration/competitions/participants/{id}/markAsWinner")
    private String markAsWinner(@PathVariable("id") Long id) {
        Participant participant = participantService.markAsWinner(id, Boolean.TRUE);
        return "redirect:/administration/competitions/" + participant.getCompetition().getId() + "/participants";
    }

    @GetMapping("/administration/competitions/participants/{id}/markAsNotWinner")
    private String markAsNotWinner(@PathVariable("id") Long id) {
        Participant participant = participantService.markAsWinner(id, null);
        return "redirect:/administration/competitions/" + participant.getCompetition().getId() + "/participants";
    }

    @GetMapping("/competitions/{id}/participants")
    private String getParticipants(@PathVariable("id") Long id, Model model) {
        model.addAttribute("competition", mapperFacade.map(competitionService.getById(id), CompetitionDto.class));
        return "participant";
    }

    @PostMapping("/competitions/{id}/participants")
    private String createParticipants(@PathVariable("id") Long id,
                                      @ModelAttribute("participant") ParticipantDto participantDto) {
        User user = SecurityUtils.getUserFromContext();
        if (Objects.isNull(user)) {
            return "redirect:/login";
        }
        participantDto.setId(null);
        participantDto.setCompetition(new CompetitionDto().setId(id));
        participantDto.setUser(new UserDto().setId(user.getId()));
        participantService.save(mapperFacade.map(participantDto, Participant.class));
        return "redirect:/users/profile";
    }

    @DeleteMapping("/competitions/participants/{id}")
    private void deleteParticipants(@PathVariable("id") Long id) {
        participantService.delete(id);
    }

    @GetMapping("/administration/reports/{id}")
    private ResponseEntity<?> getReport(@PathVariable("id") Long id) {
        ByteArrayInputStream bis = reportService.getReportByCompetitionId(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Отчет.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
