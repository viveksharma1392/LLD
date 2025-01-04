package repository;

import java.util.concurrent.ConcurrentHashMap;

public class BlackListRepo {
    private final ConcurrentHashMap<String, String> blackListWords;

    private BlackListRepo() {
        blackListWords = new ConcurrentHashMap<>();
    }
    private static BlackListRepo blackListRepo;

    public static BlackListRepo getInstance(){
        if(blackListRepo==null){blackListRepo=new BlackListRepo();}
        return blackListRepo;
    }

    public void addToBlacklist(String word) {
        blackListWords.put(word, word);
    }

    public void removeFromBlacklist(String word) {
        blackListWords.remove(word);
    }

    public boolean isBlacklisted(String word) {
        return blackListWords.containsKey(word);
    }
}
