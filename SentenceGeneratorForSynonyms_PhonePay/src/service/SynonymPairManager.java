package service;

import repository.SynonymPairRepo;

import java.util.Set;

public class SynonymPairManager {
    private SynonymPairRepo synonymPairRepo;

    public SynonymPairManager() {
        synonymPairRepo = SynonymPairRepo.getInstance();
    }

    public void addSynonymPair(String word1, String word2) {
        synonymPairRepo.addSynonymPair(word1, word2);
    }

    public void removeSynonymPair(String word1, String word2) {
        synonymPairRepo.removeSynonymPair(word1, word2);
    }

    public Set<String> getSynonyms(String word) {
        return synonymPairRepo.getSynonyms(word);
    }
}
