package org.morgorithm.frames.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.morgorithm.frames.dto.MemberDTO;
import org.morgorithm.frames.dto.PageRequestDTO;
import org.morgorithm.frames.dto.PageResultDTO;
import org.morgorithm.frames.service.MemberService;
import org.morgorithm.frames.service.StatusService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
@Log4j2
public class MemberController {
    private final MemberService memberService;






    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String register(MemberDTO memberDTO, RedirectAttributes redirectAttributes){

        Long mno=memberService.register(memberDTO);

        return "redirect:/";
    }
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", memberService.getMemberList(pageRequestDTO));

    }
    @GetMapping({"/read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("mno: " + mno);

        MemberDTO dto = memberService.read(mno);

        model.addAttribute("dto", dto);

    }
    @PostMapping("/remove")
    public String remove(long mno, RedirectAttributes redirectAttributes) {


        log.info("mno: " + mno);

        memberService.remove(mno);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/member/list";

    }

    @PostMapping("/modify")
    public String modify(MemberDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        memberService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("mno", dto.getMno());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        return "redirect:/member/read";

    }
}
