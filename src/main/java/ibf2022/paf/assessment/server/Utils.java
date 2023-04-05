package ibf2022.paf.assessment.server;

import ibf2022.paf.assessment.server.models.User;

import java.util.UUID;

public class Utils {
    public static User createNewUser(String userName) {
        
        //Convenience method; Smart constructor to create a new user with a given undefined user name
        User newUser = new User();

        // assume the username is the name as well, for simplicity
        newUser.setName(userName);
        
        newUser.setUsername(userName);
        String randomUserId = UUID.randomUUID().toString().substring(0, 8);
        newUser.setUserId(randomUserId);
        return newUser;
    }
}