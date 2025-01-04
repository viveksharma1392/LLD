import controller.SynonymSystemController;
import exception.SynonymOperationException;
import service.BlackListManager;
import service.CacheManager;
import service.SentenceService;
import service.SynonymPairManager;

import java.util.*;
public class SynonymSystemDriver {

    public static void main(String[] args) {
        try {

            SynonymPairManager  synonymPairManager = new SynonymPairManager();
            CacheManager cacheManager = new CacheManager();
            BlackListManager blackListManager = new BlackListManager();
            SentenceService sentenceService = new SentenceService(synonymPairManager, cacheManager, blackListManager);
            // Initialize Synonym System
            SynonymSystemController synonymSystem = new SynonymSystemController(synonymPairManager, cacheManager, sentenceService, blackListManager);

            // Add some synonym pairs
            synonymSystem.addSynonymPair("hello", "hey");
            synonymSystem.addSynonymPair("world", "earth");
            synonymSystem.addSynonymPair("planet", "earth");

            // Get similar sentences
            String sentence = "hello world";
            List<String> sentences = synonymSystem.getSentences(sentence);
            sentences.forEach(System.out::println);
            System.out.println("===========================================");

            sentence = "world earth";
            sentences = synonymSystem.getSentences(sentence);
            sentences.forEach(System.out::println);
            System.out.println("===========================================");
            synonymSystem.removeSynonymPair("planet", "earth");
            sentence = "hello world";
            sentences = synonymSystem.getSentences(sentence);
            sentences.forEach(System.out::println);
            System.out.println("===========================================");

            // Add a blacklisted word and show its impact on the sentences
            synonymSystem.addBlackListWord("earth");
            sentences = synonymSystem.getSentences(sentence);
            sentences.forEach(System.out::println);
            System.out.println("===========================================");
            // Remove synonym pair
            // Remove blacklisted word and show the sentences again
            synonymSystem.removeBlackListWord("earth");
            sentences = synonymSystem.getSentences(sentence);
            sentences.forEach(System.out::println);

        } catch (SynonymOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
