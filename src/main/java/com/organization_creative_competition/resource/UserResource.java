package com.organization_creative_competition.resource;

import com.organization_creative_competition.dto.UserDto;
import com.organization_creative_competition.model.User;
import com.organization_creative_competition.security.SecurityUtils;
import com.organization_creative_competition.service.CompetitionService;
import com.organization_creative_competition.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserResource {

    private final UserService userService;
    private final MapperFacade mapperFacade;
    private final CompetitionService competitionService;

    @GetMapping("/registration")
    private String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    private String registration(@ModelAttribute("user") UserDto userDto) {
        try {
            userService.save(mapperFacade.map(userDto, User.class));
        } catch (Exception e) {
            return "redirect:/registration?error";
        }
        return "redirect:/login";
    }

    @GetMapping("/administration/registration")
    private String getAdministrationRegistration() {
        return "administrationRegistration";
    }

    @PostMapping("/administration/registration")
    private String administrationRegistration(@ModelAttribute("user") UserDto userDto) {
        try {
            userService.save(mapperFacade.map(userDto, User.class));
        } catch (Exception e) {
            return "/registration";
        }
        return "redirect:/administration/staff";
    }

    @GetMapping("/users/profile")
    private String profile(Principal principal) {
        if (Objects.isNull(principal)) {
            return "redirect:/login";
        }
        return "profile";
    }

    @GetMapping("/administration/staff")
    private String getAdministrationStaff(Model model) {
        User userFromContext = SecurityUtils.getUserFromContext();
        if (Objects.nonNull(userFromContext)) {
            model.addAttribute("users", mapperFacade.mapAsList(userService.getUsersByCreationUserId(
                    userFromContext.getId()), UserDto.class));
        }
        return "staff";
    }

    @GetMapping("/administration/staff/{id}/edit")
    private String getAdministrationEditStaff(@PathVariable("id") Long id, Model model) {
        model.addAttribute("staff", mapperFacade.map(userService.getById(id), UserDto.class));
        return "editStaff";
    }

    @PostMapping("/administration/staff/{id}/edit")
    private String editUser(@ModelAttribute("user") UserDto userDto) {
        try {
            userService.update(mapperFacade.map(userDto, User.class));
        } catch (Exception e) {
            return "redirect:/administration/staff?error";
        }
        return "redirect:/administration/staff";
    }

    @DeleteMapping("/administration/users/{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
