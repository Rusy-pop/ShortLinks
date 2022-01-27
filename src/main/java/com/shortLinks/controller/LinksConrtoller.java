package com.shortLinks.controller;

import com.shortLinks.model.Link;
import com.shortLinks.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LinksConrtoller {

    private final LinkService linkService;

    @Autowired
    public LinksConrtoller(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(value = "/links")
    public ResponseEntity<?> create(@RequestBody Link link) {
        linkService.create(link);
        return new ResponseEntity<>(Http);
    }

    @GetMapping(value = "/links")
    public ResponseEntity<List<Link>> readAll() {
        final List<Link> clients = linkService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/links/{id}")
    public String read(@PathVariable(name = "id") String id) {
        final Link link = linkService.read(id);

        return link != null
                ? "redirect :"+ link.getOldUrl()
                : "redirect:/links";
    }

    @PutMapping(value = "/links/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody Link link) {
        final boolean updated = linkService.update(link, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/links/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        final boolean deleted = linkService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
