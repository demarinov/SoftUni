package com.mobile.controllers;

import com.mobile.models.dtos.BrandDto;
import com.mobile.models.dtos.OfferDto;
import com.mobile.services.BrandService;
import com.mobile.services.ModelService;
import com.mobile.services.OfferService;
import com.mobile.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
@RequiredArgsConstructor
@Slf4j
public class OfferController {

    private final UserService userService;
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;

    @ModelAttribute("model")
    public String initModels(Model model) {
        return "";
    }

    @GetMapping("/all")
    public String offers(Model model) {
        model.addAttribute("userOffers", offerService.getAllOffers());
        model.addAttribute("allModels", modelService.getAllModels());
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!userService.isUserLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("offerModel")) {
            model.addAttribute("offerModel", new OfferDto());
        }

        // load the models and brands
        List<BrandDto> brandList = brandService.getAllBrands();
        model.addAttribute("brands", brandList);

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferDto offerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @RequestParam(value = "model", required = false) String userModel) {

        if (!userService.isUserLoggedIn()) {
            return "redirect:/offers/all";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("invalid", true);
            return "redirect:/offers/add";
        }

        log.debug(offerDto.toString());
        offerService.addOffer(offerDto);
        return "redirect:/offers/all";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") UUID id, Model model) {
        OfferDto offerDto = offerService.getOfferDetails(id);
        model.addAttribute("offerDetails", offerDto);
        model.addAttribute("carModel", modelService.findModel(offerDto.getModelId()));
        return "details";
    }

    @RequestMapping(value="/delete/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") UUID id) {

        log.debug(id.toString());
        offerService.deleteOffer(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable UUID id, Model model) {

        if (!userService.isUserLoggedIn()) {
            return "redirect:/offers/all";
        }

        OfferDto offerDto = offerService.getOfferDetails(id);
        model.addAttribute("offerDetails", offerDto);

        // load the models and brands
        List<BrandDto> brandList = brandService.getAllBrands();
        model.addAttribute("brands", brandList);

        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") UUID id, @Valid OfferDto offerDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors() || offerDto.getModelId() == null) {

            return "redirect:/offers/update/"+id;
        }

        log.debug(offerDto.toString());

        offerService.updateOffer(offerDto);

        return "redirect:/offers/all";
    }

}
