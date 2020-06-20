package id.atmaja.baserestapi.rest.example;

import id.atmaja.baserestapi.base.BaseResponse;
import id.atmaja.baserestapi.base.BaseService;
import id.atmaja.baserestapi.builder.ResponseBuilder;
import id.perdiatmaja.sqlhelper.utils.QueryUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class ExampleService extends BaseService {

    BaseResponse index() {
        return new BaseResponse.Builder().message("Example").code("Example").build();
    }

    BaseResponse getJoinExample() throws Exception {
        String query = QueryUtil.selectBuilder().table(Table1.class, "t1")
                .columns(TableJoinResult.getSelectedColumns())
                .INNER_JOIN(Table2.class, "t2")
                .ON("t1", "t2", "varStr")
                .THEN()
                .targetClass(TableJoinResult.class)
                .build();

        List results = getEntityManager().createQuery(query).getResultList();
        closeEntityManager();

        return ResponseBuilder.success(results);
    }

}
