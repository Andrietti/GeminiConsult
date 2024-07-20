package GeminiConsult.Exceptions;

public class GenericExceptions extends RuntimeException{

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidRoleException extends RuntimeException {
        public InvalidRoleException(String message) {
            super(message);
        }
    }
}
