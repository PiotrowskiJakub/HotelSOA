package pl.edu.agh.soa.core.dict;

public interface EmailMessages {
	public static final String ACCOUNT_ACTIVATION_LINK = "http://soahotelcore-hotelcore.rhcloud.com/core-0.1/registration/confirm/";
	public static final String SUBJECT = "[SOAHotel] Twoje konto zostało utworzone";
	public static final String MESSAGE = ",\n\nTwoje konto w systemie SoaHotel zostało utworzone.\n\nKliknij w poniższy link, aby aktywować konto:\n" + ACCOUNT_ACTIVATION_LINK;
	public static final String END_MESSAGE = "\n\nPozdrawiamy,\nZespół SoaHotel";
	
	public static final String COMPLAITN_SUBJECT = "[SOAHotel] Twoja reklamacja została zgłoszona";
	public static final String COMPLAINT_MESSAGE = ",\n\nTwoja reklamacja została zgłoszona.\n\n\"";
	public static final String COMPLAITN_EMPLOYEE_SUBJECT = "Zgłoszono reklamacje";
	public static final String COMPLAINT_EMPLOYEE_MESSAGE = " zgłosił reklamację o treści:\n\n\"";
}
