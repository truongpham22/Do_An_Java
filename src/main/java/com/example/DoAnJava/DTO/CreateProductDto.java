package com.example.DoAnJava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    private String name;
    private String description;
    private Double price;
    private String unit;
    private String urlImageThumbnail;
    private String imageList;
    private Integer quantityStock;
    private Long category_id;
    private Long product_type_id;
    private List<MultipartFile> file;
    private List<MultipartFile> files;
}
