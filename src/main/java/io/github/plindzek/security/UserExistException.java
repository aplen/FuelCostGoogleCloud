package io.github.plindzek.security;

import java.io.PrintStream;

public class UserExistException extends Exception {

    @Override
    public void printStackTrace(PrintStream s) {
        System.err.println("Can't create user -  user with given name already exist");
        super.printStackTrace(s);

    }

}
