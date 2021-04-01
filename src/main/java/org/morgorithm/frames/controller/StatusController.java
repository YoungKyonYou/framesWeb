package org.morgorithm.frames.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.morgorithm.frames.dto.PageRequestDTO;
import org.morgorithm.frames.entity.Facility;
import org.morgorithm.frames.service.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/status")
@Controller
@Log4j2
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/list")
    public String register(PageRequestDTO pageRequestDTO, Facility facility, Model model){
        model.addAttribute("result", statusService.getStatusList(pageRequestDTO));
        return "status/list";
    }
}
