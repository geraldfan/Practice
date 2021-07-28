public class UnderAgeException extends IllegalArgumentException{
    @Override
    public String getMessage() {
        return "User is under 18. Please enter a valid age.";
    }
}
