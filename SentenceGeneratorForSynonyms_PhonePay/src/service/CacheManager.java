package service;

import repository.CacheRepo;

import java.util.List;

public class CacheManager {
    private CacheRepo cacheRepo;

    public CacheManager() {
        this.cacheRepo = CacheRepo.getInstance();
    }

    public List<String> getCachedSynonym(String word) {
        return cacheRepo.getCachedSynonym(word);
    }

    public void cachewordSynonyms(String word, List<String> synonyms){
        cacheRepo.cacheWordSynonym(word, synonyms);
    }

    public void invalidateCache(String word){
        cacheRepo.invalidateCache(word);
    }
}

