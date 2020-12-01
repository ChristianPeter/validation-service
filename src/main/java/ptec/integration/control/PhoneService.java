package ptec.integration.control;

import javax.inject.Singleton;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import ptec.integration.entity.PhoneValidationRequest;
import ptec.integration.entity.PhoneValidationResponse;
import reactor.core.publisher.Mono;

@Singleton
public class PhoneService {

    public Mono<PhoneValidationResponse> validatePhone(PhoneValidationRequest validationRequest) {
        PhoneNumberUtil p = PhoneNumberUtil.getInstance();
        
        try {
            PhoneNumber parsed = p.parse(validationRequest.number, validationRequest.country);
            PhoneValidationResponse response = PhoneValidationResponse.of(validationRequest);
            response.isValid = p.isValidNumber(parsed);
            return Mono.just(response);
        } catch (NumberParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            PhoneValidationResponse error = PhoneValidationResponse.of(validationRequest);
            error.isValid = false;
            error.message = e.getMessage();
            
            return Mono.just(error);
        }

        


	}
    
}
