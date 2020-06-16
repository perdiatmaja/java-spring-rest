package id.atmaja.baserestapi.rest.example;

import id.atmaja.baserestapi.base.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    BaseResponse index() {
        return new BaseResponse.Builder().message("Example").code("Example").build();
    }
}
