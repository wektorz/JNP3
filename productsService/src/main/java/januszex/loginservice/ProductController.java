package januszex.loginservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductsService service;


    @GetMapping(
            value = "/api/products",
            produces = "application/json"
    )
    public ResponseEntity<String> getproducts(@RequestParam(required = false) Optional<String> id)
    {
        return service.getproducts(id);
    }




}
/*products:
    get:
    tags:
            - Products Service
    summary: Get items from database
    parameters:
            - in: query
    name: itemId
    required: false
    schema:
    type: integer
    format: int32
        - in: query
    name: noImg
    required: false
    schema:
    type: boolean
        # - in: query
        #   name: filter
        #   requred: false
            #   schema:
            #     type: string
    responses:
            '200':
    description: Get successful
    content:
    application/json:
    schema:
    type: array
    items:
    example:
            - 'itemId: 10374078'
            - 'desc: jjfakl;'
            - 'example: 190u490'
            '400':
    description: Something's wrong */