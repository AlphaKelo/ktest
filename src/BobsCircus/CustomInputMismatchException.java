package BobsCircus;
import java.util.InputMismatchException;

//Custom exception class for input mismatches
public class CustomInputMismatchException extends InputMismatchException{
	public CustomInputMismatchException(String message) {
		super(message);
	}
}
