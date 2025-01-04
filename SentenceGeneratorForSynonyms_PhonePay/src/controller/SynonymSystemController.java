package controller;

import service.CacheManager;
import service.SentenceService;
import service.SynonymPairManager;
import service.BlackListManager;

import java.util.*;

public class SynonymSystemController {
    private final SynonymPairManager synonymPairManager;
    private final CacheManager cacheManager;
    private final BlackListManager blackListManager;

    private final SentenceService sentenceService;

    public SynonymSystemController(SynonymPairManager synonymPairManager, CacheManager cacheManager, SentenceService sentenceService, BlackListManager blackListManager) {
        this.sentenceService = sentenceService;
        this.synonymPairManager= synonymPairManager;
        this.cacheManager = cacheManager;
        this.blackListManager = blackListManager;
    }

    public void addSynonymPair(String word1, String word2) {
        cacheManager.invalidateCache(word1);
        cacheManager.invalidateCache(word2);
        synonymPairManager.addSynonymPair(word1, word2);
    }

    public void removeSynonymPair(String word1, String word2) {
        cacheManager.invalidateCache(word1);
        cacheManager.invalidateCache(word2);
        synonymPairManager.removeSynonymPair(word1, word2);
    }

    public void addBlackListWord(String word) {
        blackListManager.addToBlacklist(word);
    }

    public void removeBlackListWord(String word) {
        blackListManager.removeFromBlacklist(word);
    }


    public List<String> getSentences(String sentence) {
        return sentenceService.getSentences(sentence);
    }
}
