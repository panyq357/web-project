package cn.ac.panlab.backend.context;

import cn.ac.panlab.backend.model.User;

public class UserContext {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<User>();

    public static void set(User user) {
        currentUser.set(user);
    }

    public static User get() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
