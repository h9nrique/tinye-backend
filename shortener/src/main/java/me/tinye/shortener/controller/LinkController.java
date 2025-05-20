package me.tinye.shortener.controller;

import jakarta.validation.Valid;
import me.tinye.shortener.DTO.AccessLinkResponseDTO;
import me.tinye.shortener.DTO.CreateLinkRequestDTO;
import me.tinye.shortener.DTO.LinkResponseDTO;
import me.tinye.shortener.entity.Link;
import me.tinye.shortener.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LinkResponseDTO> createLink(@RequestBody @Valid CreateLinkRequestDTO data) {
        LinkResponseDTO link = linkService.createLink(data);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }

    @GetMapping("/{shortLink}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccessLinkResponseDTO> accessLink(@PathVariable("shortLink") String shortLink) {
        AccessLinkResponseDTO accessLinkResponseDTO = linkService.accessLink(shortLink);
        return new ResponseEntity<>(accessLinkResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteLink(@PathVariable("id") UUID id) {
        linkService.deleteLink(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/links")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LinkResponseDTO>> getLinks() {
        List<LinkResponseDTO> links = linkService.getLinks();
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    @PatchMapping("/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LinkResponseDTO> changeStatus(@PathVariable("id") UUID id) {
        LinkResponseDTO link = linkService.changeStatus(id);
        return new ResponseEntity<>(link, HttpStatus.OK);
    };
}
