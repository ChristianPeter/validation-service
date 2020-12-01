package ptec.integration.entity;

public class PhoneValidationResponse {
    public String country;
    public String number;
    public boolean isValid;
    public String message;

    public static PhoneValidationResponse of(PhoneValidationRequest validationRequest) {
        PhoneValidationResponse response = new PhoneValidationResponse();
        response.country = validationRequest.country;
        response.country = validationRequest.number;
        return response;
    }
}
