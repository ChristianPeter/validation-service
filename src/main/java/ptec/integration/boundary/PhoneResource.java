package ptec.integration.boundary;

import javax.inject.Inject;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import ptec.integration.control.PhoneService;
import ptec.integration.entity.PhoneValidationRequest;
import ptec.integration.entity.PhoneValidationResponse;
import reactor.core.publisher.Mono;

@Controller("/phone")
public class PhoneResource {

    @Inject
    PhoneService phoneService;

    @Post(uris = {"/validate-number"}, processes = MediaType.APPLICATION_JSON)
    public Mono<PhoneValidationResponse> validatePhone(@Body PhoneValidationRequest validationRequest) {
        return phoneService.validatePhone(validationRequest);
    }
    
    @Get(uris = {"/demo"})
    public String hello() {
        String swissNumberStr = "044 668 18 00";
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        
        try {
            PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "CH");
            return phoneUtil.format(swissNumberProto, PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
        System.err.println("NumberParseException was thrown: " + e.toString());
        }
        return "Hello world";
    }
}
