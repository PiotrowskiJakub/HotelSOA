package pl.edu.agh.soa.core.dict;

public interface EmailMessages {
	public static final String ACCOUNT_ACTIVATION_LINK = "http://localhost:8080/core/registration/confirm/";
	public static final String SUBJECT = "[SOAHotel] Twoje konto zostało utworzone";
	public static final String MESSAGE = ",\n\nTwoje konto w systemie SoaHotel zostało utworzone.\nKliknij w poniższy link, aby aktywować konto:\n" + ACCOUNT_ACTIVATION_LINK;
	public static final String END_MESSAGE = "\n\nPozdrawiamy,\nZespół SoaHotel";
}
