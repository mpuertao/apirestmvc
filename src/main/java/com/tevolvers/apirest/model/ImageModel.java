package com.tevolvers.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageModel {

    private String id;
    private String image;
    private String title;
    private String description;
}
