package repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CacheRepo implements CacheRepoInterface{

    private final ConcurrentHashMap<String, List<String>> synonymCache;
    private CacheRepo() {
        synonymCache = new ConcurrentHashMap<>();
    }
    private static CacheRepo cacheRepo;

    public static CacheRepo getInstance(){
        if(cacheRepo==null){cacheRepo=new CacheRepo();}
        return cacheRepo;
    }

    public List<String> getCachedSynonym(String sentence) {
        return synonymCache.get(sentence);
    }

    public void cacheWordSynonym(String word, List<String> synonyms) {
        synonymCache.put(word, synonyms);
    }

    public void invalidateCache(String word) {
        if(synonymCache.containsKey(word)){
            List<String> neighbourSynonym = synonymCache.get(word);
            for(String neighbour :neighbourSynonym){
                synonymCache.remove(neighbour);
            }
        }
    }

    public void invalidateCache1(String word) {
        if(synonymCache.containsKey(word)){
            List<String> neighbourSynonym = synonymCache.get(word);
            for(String neighbour :neighbourSynonym){
                synonymCache.remove(neighbour);
            }
        }
    }
    public void invalidateCache2(String word) {
        if(synonymCache.containsKey(word)){
            List<String> neighbourSynonym = synonymCache.get(word);
            for(String neighbour :neighbourSynonym){
                synonymCache.remove(neighbour);
            }
        }
    }
}
