package service;

import java.util.*;

public class SentenceService {

    private SynonymPairManager synonymPairManager;
    private BlackListManager blackListManager;
    private CacheManager cacheManager;

    public SentenceService(SynonymPairManager synonymPairManager, CacheManager cacheManager, BlackListManager blackListManager){
        this.synonymPairManager = synonymPairManager;
        this.blackListManager = blackListManager;
        this.cacheManager = cacheManager;
    }

    private List<String> bfsToGetAllTransitiveSynonyms(String word) {
        if (cacheManager.getCachedSynonym(word) != null) {
            return cacheManager.getCachedSynonym(word);
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(word);
        Set<String> seen = new HashSet<>();
        seen.add(word);

        while (!queue.isEmpty()) {
            String currentWord = queue.poll();
            for (String synonym : synonymPairManager.getSynonyms(currentWord)) {
                if (!seen.contains(synonym)) {
                    seen.add(synonym);
                    queue.add(synonym);
                }
            }
        }

        for(String seenWord : seen){
            List<String> allSortedSynonmsOfWord = new ArrayList<>(seen);
            Collections.sort(allSortedSynonmsOfWord);
            cacheManager.cachewordSynonyms(seenWord, allSortedSynonmsOfWord);
        }

        return cacheManager.getCachedSynonym(word);
    }

    private void getStringWithBackTracking(List<String> words, int currentIndex, List<String> currentSentence, List<String> allSentences) {
        if (currentIndex >= words.size()) {
            allSentences.add(String.join(" ", currentSentence));
            return;
        }

        String currentWord = words.get(currentIndex);
        List<String> synonyms = synonymPairManager.getSynonyms(currentWord).isEmpty() ?
                new ArrayList<>(Collections.singletonList(currentWord)) :
                bfsToGetAllTransitiveSynonyms(currentWord);
        for (String synonym : synonyms) {
            if (!blackListManager.isBlacklisted(synonym)) {
                currentSentence.add(synonym);
                getStringWithBackTracking(words, currentIndex + 1, currentSentence, allSentences);
                currentSentence.remove(currentSentence.size() - 1);
            }
        }
    }

    public List<String> getSentences(String sentence) {
        List<String> resultSentences = new ArrayList<>();
        String[] words = sentence.replaceAll("\\p{Punct}", "").split(" ");
        getStringWithBackTracking(Arrays.asList(words), 0, new ArrayList<>(), resultSentences);
        resultSentences.remove(sentence.replaceAll("\\p{Punct}", ""));
        return resultSentences;
    }
}
