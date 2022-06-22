package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Entities.Memo;
import com.example.demo.Repositories.MemoRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {
    @Autowired
    private MemoRepository memoRepository;

    @Override
    public void run(String... args) throws Exception {
        Memo demoMemo = new Memo("Demo", "Content...");
        memoRepository.save(demoMemo);
    }
}
