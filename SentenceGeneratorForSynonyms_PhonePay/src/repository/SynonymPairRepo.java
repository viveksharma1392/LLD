package repository;

import exception.SynonymOperationException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SynonymPairRepo {
    private final ConcurrentHashMap<String, Set<String>> synonymsMap;
    private static SynonymPairRepo synonymPairRepo;
    private SynonymPairRepo() {
        synonymsMap = new ConcurrentHashMap<>();
    }

    public static SynonymPairRepo getInstance(){
        if(synonymPairRepo==null){synonymPairRepo = new SynonymPairRepo();}
        return synonymPairRepo;
    }

    public void addSynonymPair(String word1, String word2) {
        synonymsMap.putIfAbsent(word1, new HashSet<>());
        synonymsMap.putIfAbsent(word2, new HashSet<>());
        synonymsMap.get(word1).add(word2);
        synonymsMap.get(word2).add(word1);
    }

    public void removeSynonymPair(String word1, String word2) {
        if (!synonymsMap.containsKey(word1) || !synonymsMap.containsKey(word2)) {
            throw new SynonymOperationException("Synonym pair does not exist.");
        }

        synonymsMap.get(word1).remove(word2);
        synonymsMap.get(word2).remove(word1);
    }

    public Set<String> getSynonyms(String word) {
        return synonymsMap.getOrDefault(word, Collections.emptySet());
    }
}
