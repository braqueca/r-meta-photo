package com.braquec.rmetaphoto.web;

import com.braquec.rmetaphoto.dto.PhotoDto;
import com.braquec.rmetaphoto.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/externalapi/photos")
public class PhotosRs {
    @Autowired
    private PhotosService photosService;

    @GetMapping("")
    public List<PhotoDto> getAll(@RequestParam Map<String,String> allRequestParams){
        return photosService.getAll(allRequestParams);
    }

    @GetMapping("/{id}")
    public PhotoDto getById(@PathVariable Long id) {
        return photosService.getById(id);
    }
}
