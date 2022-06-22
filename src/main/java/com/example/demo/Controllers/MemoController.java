package com.example.demo.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entities.Memo;
import com.example.demo.Repositories.MemoRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/memos")
@Validated
public class MemoController {
    @Autowired
    private MemoRepository memoRepository;

    @PostMapping
    public ResponseEntity<Memo> create(@RequestBody @Valid Memo memo) {
        Memo newMemo = this.memoRepository.save(memo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newMemo);
    }

    @GetMapping
    public ResponseEntity<Iterable<Memo>> list() {
        Iterable<Memo> memos = this.memoRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(memos);
    }

    @GetMapping(value = "/{memoId}")
    public ResponseEntity<Memo> putMethodName(@PathVariable("memoId") Long id) {
        Memo memo = this.memoRepository.findById(id).orElse(null);
        System.out.println(memo);
        if (memo == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memo);
    }

    @PatchMapping(value = "/{memoId}")
    public ResponseEntity<Memo> putMethodName(@PathVariable("memoId") Long id, @RequestBody Memo edited) {
        Memo memo = this.memoRepository.findById(id).orElse(null);

        if (memo == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        if (edited.getContent() != null) {
            memo.setContent(edited.getContent());
        }
        if (edited.getTitle() != null) {
            memo.setTitle(edited.getTitle());
        }

        memo = this.memoRepository.save(memo);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(memo);
    }

    @DeleteMapping("/{memoId}")
    public ResponseEntity<Memo> delete(@PathVariable("memoId") long id) {
        if (!this.memoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.memoRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
