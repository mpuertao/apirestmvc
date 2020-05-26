package com.tevolvers.apirest.service;

import com.tevolvers.apirest.model.ImageModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {

    public List<ImageModel> getImages() {

        List<ImageModel> imageModels = new ArrayList<>();

        imageModels.add(new ImageModel("1", "assets/images/t-evolvers-logo.png", "Logo en PNG #1", "Primer logo"));
        imageModels.add(new ImageModel("2", "assets/images/t-evolvers-logo.png", "Logo en PNG #2", "Segundo logo"));
        imageModels.add(new ImageModel("3", "assets/images/t-evolvers-logo.png", "Logo en PNG #3", "Tercer logo"));

        return imageModels;
    }
}
