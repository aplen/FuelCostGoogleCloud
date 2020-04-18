package io.github.plindzek.security;

import java.io.PrintStream;
/**
 * @author Adam
 *  Exception thrown when try to request POST user who already exist
 */
public class UserExistException extends Exception {

    @Override
    public void printStackTrace(PrintStream s) {
        System.err.println("Can't create user -  user with given name already exist");
        super.printStackTrace(s);

    }

}
