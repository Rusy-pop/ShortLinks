package com.shortLinks.controller;

import com.shortLinks.model.Link;
import com.shortLinks.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class LinksController {

    private final LinkService linkService;

    public LinksController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> create(@RequestParam String url) {
        Link link = new Link(url);

        linkService.create(link);
        return new ResponseEntity<>("http://localhost:8080/"+link.getName(),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Link>> readAll() {
        final List<Link> clients = linkService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Link> read (@PathVariable(name = "id") int id){
//        final Link link = linkService.read(id);
//
//        return link != null
//                ? new ResponseEntity<>(link, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping(value = "/")
    public ResponseEntity<?> update(@RequestParam int id, String newName, String newUrl) {
        final boolean updated = linkService.update(id, newName, newUrl);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<?> delete(@RequestParam int id) {
        final boolean deleted = linkService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{name}")
    public RedirectView redirect(@PathVariable(name = "name") String name) {
        String url = linkService.redirect(name);
        if (url!=null){
            return new RedirectView(url);
        } else return new RedirectView("/");
    }
}
