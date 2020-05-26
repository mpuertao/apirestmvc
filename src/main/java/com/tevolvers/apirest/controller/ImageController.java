package com.tevolvers.apirest.controller;

import com.tevolvers.apirest.model.ImageModel;
import com.tevolvers.apirest.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @GetMapping
    public List<ImageModel> getImages() {
        return imageService.getImages();
    }

    @GetMapping("/{id}")
    public ImageModel getImage(@PathVariable int id) {
        return imageService.getImages().get(id - 1);
    }
}
