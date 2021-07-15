package com.shykunkv.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private MongoRepo mongoRepo;

    @Autowired
    private ElkRepo elkRepo;

    @GetMapping(path = "/random/generate")
    public ResponseEntity<String> generate() {
        final int nextInt = new Random().nextInt(1000);
        return ResponseEntity.ok(mongoRepo.save(new RandomNumber(nextInt)).toString());
    }

    @GetMapping(path = "/random/all")
    public ResponseEntity<List<Integer>> all() {
        final List<Integer> response = mongoRepo.findAll().stream()
                .map(n -> n.value)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/sentence/save")
    public ResponseEntity<String> save(@RequestParam("value") String text) {
        Sentence save = elkRepo.save(new Sentence(text));

        return ResponseEntity.ok(save.value);
    }

    @GetMapping(path = "/sentence/search")
    public ResponseEntity<String> search(@RequestParam("value") String text) {
        List<Sentence> sentences = elkRepo.findByValue(text);

        if (sentences.isEmpty()) {
            return ResponseEntity.ok("NOT FOUND");
        } else {
            final String result = sentences.stream()
                    .map(s -> s.value)
                    .collect(Collectors.joining("</br>"));
            return ResponseEntity.ok("RESULT:</br>" + result);
        }
    }

    @GetMapping(path = "/sentence/all")
    public ResponseEntity<List<String>> allSentences() {
        List<String> result = new ArrayList<>();
        elkRepo.findAll().forEach(i -> result.add(i.value));
        return ResponseEntity.ok(result);
    }
}
