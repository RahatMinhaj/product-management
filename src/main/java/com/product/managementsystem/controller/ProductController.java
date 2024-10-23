package com.product.managementsystem.controller;

import com.product.managementsystem.application.ProductApplicationService;
import com.product.managementsystem.component.route.ApiProvider;
import com.product.managementsystem.controller.core.AbstractController;
import com.product.managementsystem.dto.ProductDto;
import com.product.managementsystem.param.PageableParam;
import com.product.managementsystem.param.ProductParam;
import com.product.managementsystem.support.ApiResponseDto;
import com.product.managementsystem.support.DeleteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiProvider.Product.ROOTPATH)
public class ProductController extends AbstractController {

    private final ProductApplicationService productApplicationService;

    @Operation(summary = "get all data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(schema = @Schema(implementation = ProductDto.class)))
    })
    @Parameters({
            @Parameter(name = "pageNo", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "1")),
            @Parameter(name = "pageSize", in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "20")),
            @Parameter(name = "sortBy", in = ParameterIn.QUERY, schema = @Schema(type = "string"), example = "createdAt:desc")
    })
    @GetMapping
    public ResponseEntity findAll(@Schema(hidden = true) PageableParam pageable) {
        if (pageable.isPageable()) {
            Page<ProductDto> productDtos = productApplicationService.findAll(pageable.getPageable());
            return generateResponse(productDtos, HttpStatus.OK, "Data found");
        }
        return generateResponse(productApplicationService.findAll(pageable.getSort()), HttpStatus.OK, "Data found");
    }

    @Operation(summary = "find data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found",  content = @Content(schema = @Schema(implementation = ProductDto.class)))
    })
    @GetMapping(ApiProvider.Product.PRODUCT_IDENTIFIER)
    public ResponseEntity<ApiResponseDto<ProductDto>> getProductById(@PathVariable Long id) {
        ProductDto productDto = productApplicationService.findById(id);
        return generateResponse(productDto, HttpStatus.OK, "Data found");
    }

    @Operation(summary = "Create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(schema = @Schema(implementation = ProductDto.class)))
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<ProductDto>> createProduct(@RequestBody ProductParam param) {
        return generateResponse(productApplicationService.createProduct(param), HttpStatus.CREATED, "Data created successfully");
    }


    @Operation(summary = "update data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found",  content = @Content(schema = @Schema(implementation = ProductDto.class)))
    })
    @PutMapping(ApiProvider.Product.PRODUCT_IDENTIFIER)
    public ResponseEntity<ApiResponseDto<ProductDto>> updateProduct(@PathVariable Long id, @RequestBody ProductParam param) {
        ProductDto updatedProduct = productApplicationService.updateProduct(id, param);
        return generateResponse(updatedProduct, HttpStatus.OK, "Data updated successfully");
    }

    @Operation(summary = "delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @DeleteMapping(ApiProvider.Product.PRODUCT_IDENTIFIER)
    public ResponseEntity<DeleteResponseDto> deleteProduct(@PathVariable Long id) {
        productApplicationService.deleteProduct(id);
        return generateResponse(HttpStatus.OK, "Data deleted successfully");
    }

    @PatchMapping(ApiProvider.Product.STOCK_QUANTITY)
    public ResponseEntity<ApiResponseDto<ProductDto>> updateStock(@PathVariable Long id, @RequestParam Integer stockQuantity) {
        return generateResponse(productApplicationService.updateStock(id, stockQuantity), HttpStatus.OK, "Data updated successfully");

    }

}
