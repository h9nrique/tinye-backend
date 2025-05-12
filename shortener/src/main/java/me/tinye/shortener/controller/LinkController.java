package me.tinye.shortener.controller;

import jakarta.validation.Valid;
import me.tinye.shortener.DTO.AccessLinkResponseDTO;
import me.tinye.shortener.DTO.CreateLinkRequestDTO;
import me.tinye.shortener.entity.Link;
import me.tinye.shortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Link> createLink(@RequestBody @Valid CreateLinkRequestDTO data) {
        Link link = linkService.createLink(data);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }

    @GetMapping("/{shortLink}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccessLinkResponseDTO> accessLink(@PathVariable("shortLink") String shortLink) {
        AccessLinkResponseDTO accessLinkResponseDTO = linkService.accessLink(shortLink);
        return new ResponseEntity<>(accessLinkResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteLink(@PathVariable("id") Long id) {
        linkService.deleteLink(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
