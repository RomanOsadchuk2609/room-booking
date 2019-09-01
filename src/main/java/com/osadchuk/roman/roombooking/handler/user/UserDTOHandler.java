package com.osadchuk.roman.roombooking.handler.user;

import com.osadchuk.roman.roombooking.handler.Handler;
import com.osadchuk.roman.roombooking.model.UserDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * Abstract Handler for UserDTO object
 */
@Component
public abstract class UserDTOHandler implements Handler<UserDTO> {
    private UserDTOHandler next;

    private static String message = Strings.EMPTY;

    @Override
    public void setNext(Handler<UserDTO> handler) {
        this.next = (UserDTOHandler) handler;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public boolean handle(UserDTO user) {
        if (isValid(user)) {
            if (hasNext()) {
                return next.handle(user);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void setMessage(String message) {
        UserDTOHandler.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
