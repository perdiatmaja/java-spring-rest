package id.atmaja.baserestapi.rest.example;

import id.atmaja.baserestapi.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExampleMap.EXAMPLE)
public class ExampleController {

    private final ExampleService exampleService;

    @Autowired
    ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping(value = ExampleMap.MAIN_INDEX, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse example() throws Exception {
        return exampleService.index();
    }

    @GetMapping(value = ExampleMap.JOIN, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse exampleJoin() throws Exception {
        return exampleService.getJoinExample();
    }


}
