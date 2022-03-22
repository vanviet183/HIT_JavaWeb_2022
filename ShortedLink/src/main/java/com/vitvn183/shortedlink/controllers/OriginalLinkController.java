package com.vitvn183.shortedlink.controllers;

import com.vitvn183.shortedlink.entity.OriginalLink;
import com.vitvn183.shortedlink.model.dto.OriginalLinkDto;
import com.vitvn183.shortedlink.services.OriginalLinkserviceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OriginalLinkController {

    @Autowired
    private OriginalLinkserviceImp originalLinkserviceImp;

    @GetMapping(value = {"/", "/original-links"})
    public String getHome(Model model) {
        model.addAttribute("originalLinks", originalLinkserviceImp.getAll());
        return "index";
    }

    @GetMapping("/{shortLink}")
    public RedirectView redirectToLink(@PathVariable("shortLink") String shortLink) {
        String originalLink = originalLinkserviceImp.getOriginalLink(shortLink);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalLink);
        return redirectView;
    }

    @PostMapping("/link/addNew")
    public String addNew(OriginalLinkDto originalLinkDto) {
        originalLinkserviceImp.addNew(originalLinkDto);
        return "redirect:/original-links";
    }

    @GetMapping("/link/update/{id}")
    public String getUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "update";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String update(@PathVariable("id") Long id, OriginalLinkDto originalLinkDto) {
        originalLinkserviceImp.update(id, originalLinkDto);
        return "redirect:/original-links";
    }

    @GetMapping("/link/delete/{id}")
    public String getDelete(@PathVariable("id") Long id) {
        return "delete";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        originalLinkserviceImp.delete(id);
        return "redirect:/original-links";
    }

}
