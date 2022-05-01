package com.braquec.rmetaphoto.web;

import com.braquec.rmetaphoto.dto.PhotoDto;
import com.braquec.rmetaphoto.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/externalapi/photos")
public class PhotosRs {
    @Autowired
    private PhotosService photosService;

    @GetMapping("")
    public List<PhotoDto> getAll(){
        return photosService.getAll();
    }

    @GetMapping("/{id}")
    public PhotoDto getById(@PathVariable Long id) {
        return photosService.getById(id);
    }
}
