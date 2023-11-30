package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exception.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/cover/{pizzaId}")
    public ResponseEntity<byte[]> serveCover(@PathVariable Integer pizzaId) {
        try {
            Pizza pizza = pizzaService.getPizzaById(pizzaId);
            byte[] coverBytes = pizza.getCover();
            // se ha la cover la restituisco
            if (coverBytes != null && coverBytes.length > 0) {
                MediaType mediaType = getMediaTypeForByteArray(coverBytes);
//                MediaType mediaType = MediaType.ALL;

                // / restituisco i byte[] della cover come contenuto della response
                return ResponseEntity.ok().contentType(mediaType).body(coverBytes);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }

    private MediaType getMediaTypeForByteArray(byte[] array) {
        // determinare il tipo di file in base ai primi byte
        String fileHeader = bytesToHex(array, 8);
        if (fileHeader.startsWith("89504E47")) {
            return MediaType.IMAGE_PNG;
        } else if (fileHeader.startsWith("47494638")) {
            return MediaType.IMAGE_GIF;
        } else if (fileHeader.startsWith("FFD8FF")) {
            return MediaType.IMAGE_JPEG;
        }
        return MediaType.APPLICATION_OCTET_STREAM; // tipo di media generico se non riconosciuto
    }

    private String bytesToHex(byte[] bytes, int bLength) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bLength && i < bytes.length; i++) {
            builder.append(String.format("%02X", bytes[i]));

        }
        return builder.toString();
    }
}
