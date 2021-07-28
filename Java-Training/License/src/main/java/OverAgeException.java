public class OverAgeException extends IllegalArgumentException{
    @Override
    public String getMessage() {
        return "User is over 60. Please enter a valid age.";
    }
}
