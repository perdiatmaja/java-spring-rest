package id.atmaja.baserestapi.rest.example;

import id.atmaja.baserestapi.base.BaseResponse;
import id.atmaja.baserestapi.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ExampleService extends BaseService {

    BaseResponse index() {
        getEntityManager();
        return new BaseResponse.Builder().message("Example").code("Example").build();
    }
}
