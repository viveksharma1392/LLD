package service;

import repository.BlackListRepo;
public class BlackListManager {

    private BlackListRepo blackListRepo;
    public BlackListManager(){
        blackListRepo = BlackListRepo.getInstance();
    }

    public void addToBlacklist(String word) {
        blackListRepo.addToBlacklist(word);
    }

    public void removeFromBlacklist(String word) {
        blackListRepo.removeFromBlacklist(word);
    }

    public boolean isBlacklisted(String word) {
        return blackListRepo.isBlacklisted(word);
    }
}
