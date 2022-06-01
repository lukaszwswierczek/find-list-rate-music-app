package pl.lukaszswierczek.findListRateApp.user;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}