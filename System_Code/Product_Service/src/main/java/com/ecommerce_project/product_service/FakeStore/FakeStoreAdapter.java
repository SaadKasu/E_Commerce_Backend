package com.ecommerce_project.product_service.FakeStore;

import com.ecommerce_project.product_service.Adapters.IThirdPartyAdapter;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Category;
import com.ecommerce_project.product_service.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreAdapter implements IThirdPartyAdapter {

    private final RestTemplate restTemplate;
    @Autowired
    public FakeStoreAdapter(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreResponseDTO[]> responseEntity = makeGETCalloutForListOfProducts(url);
        return convertResponseEntityToProductList(responseEntity);
    }

    @Override
    public Product getProductById(long id) {
        String url = "https://fakestoreapi.com/products/"+id;
        ResponseEntity<FakeStoreResponseDTO> responseEntity = makeGETCalloutForSingleProduct(url);
        return convertResponseEntityToProduct(responseEntity);
    }

    @Override
    public List<Product> getTopXResults(int limit) {
        String url = "https://fakestoreapi.com/products?limit="+limit;
        ResponseEntity<FakeStoreResponseDTO[]> responseEntity = makeGETCalloutForListOfProducts(url);
        return convertResponseEntityToProductList(responseEntity);
    }

    @Override
    public List<Product> getSortedResults(String sortOrder) {
        String url = "https://fakestoreapi.com/products?sort="+sortOrder;
        ResponseEntity<FakeStoreResponseDTO[]> responseEntity = makeGETCalloutForListOfProducts(url);
        return convertResponseEntityToProductList(responseEntity);
    }

    @Override
    public List<Product> getAllProductsInOneCategory(String category) {
        String url = "https://fakestoreapi.com/products/category/"+category;
        ResponseEntity<FakeStoreResponseDTO[]> responseEntity = makeGETCalloutForListOfProducts(url);
        return convertResponseEntityToProductList(responseEntity);
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreRequestDTO requestDTO = convertProductToDTO(product);
        String url = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreResponseDTO> responseEntity = makePOSTCallout(url,requestDTO);
        return convertResponseEntityToProduct(responseEntity);
    }

    @Override
    public Product updateAProduct(Product product) {
        FakeStoreRequestDTO requestDTO = convertProductToDTO(product);
        String url = "https://fakestoreapi.com/products/"+requestDTO.getId();
        ResponseEntity<FakeStoreResponseDTO> responseEntity = makePATCHCallout(url,requestDTO);
        return convertResponseEntityToProduct(responseEntity);
    }

    @Override
    public Product deleteAProduct(Product product) {
        FakeStoreRequestDTO requestDTO = convertProductToDTO(product);
        String url = "https://fakestoreapi.com/products/"+requestDTO.getId();
        ResponseEntity<FakeStoreResponseDTO> responseEntity = makeDELETECallout(url,requestDTO);
        return convertResponseEntityToProduct(responseEntity);
    }

    @Override
    public List<String> getAllCategories() {
        String url = "https://fakestoreapi.com/products/categories";
        ResponseEntity<String[]> responseEntity = makeGETCalloutForListOfStrings(url);
        return convertResponseEntityToStringList(responseEntity);
    }

    private static Product convertDTOToProduct(FakeStoreResponseDTO responseDTO){
        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setDescription(responseDTO.getDescription());
        product.setImage(responseDTO.getImage());
        product.setCategory(new Category(responseDTO.getCategory()));
        product.setTitle(responseDTO.getTitle());
        product.setPrice(responseDTO.getPrice());
        return product;
    }

    private static FakeStoreRequestDTO convertProductToDTO(Product product){
        FakeStoreRequestDTO requestDTO = new FakeStoreRequestDTO();
        requestDTO.setCategory(product.getCategory().getName());
        requestDTO.setDescription(product.getDescription());
        requestDTO.setImage(product.getImage());
        requestDTO.setTitle(product.getTitle());
        requestDTO.setPrice(product.getPrice());
        requestDTO.setId(product.getId());
        return requestDTO;
    }


    private static List<Product> convertResponseEntityToProductList(ResponseEntity<FakeStoreResponseDTO[]> responseEntity){
        if (responseEntity.getStatusCode() != HttpStatusCode.valueOf(200) || responseEntity.getBody() == null){
            return null;
        }
        List<Product> products = new ArrayList<>();
        FakeStoreResponseDTO [] responseDTOS = responseEntity.getBody();
        for (FakeStoreResponseDTO responseDTO : responseDTOS){
            products.add(convertDTOToProduct(responseDTO));
        }
        return products;
    }

    private static Product convertResponseEntityToProduct(ResponseEntity<FakeStoreResponseDTO> responseEntity){
        if (responseEntity.getStatusCode() != HttpStatusCode.valueOf(200) || responseEntity.getBody() == null){
            return null;
        }
        Product product = convertDTOToProduct(responseEntity.getBody());
        return product;
    }

    private static List<String> convertResponseEntityToStringList(ResponseEntity<String[]> responseEntity){
        if (responseEntity.getStatusCode() != HttpStatusCode.valueOf(200) || responseEntity.getBody() == null){
            return null;
        }
        List<String> strings = new ArrayList<>();
        String [] responseDTOS = responseEntity.getBody();
        for (String string : responseDTOS){
            strings.add(string);
        }
        return strings;
    }

    private ResponseEntity<String[]> makeGETCalloutForListOfStrings(String url){
        ResponseEntity<String[]> responseEntity;
        try{
            responseEntity = restTemplate.getForEntity(url, String[].class);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return responseEntity;
    }


    private ResponseEntity<FakeStoreResponseDTO[]> makeGETCalloutForListOfProducts(String url){
        ResponseEntity<FakeStoreResponseDTO[]> responseEntity;
        try{
            responseEntity = restTemplate.getForEntity(url, FakeStoreResponseDTO[].class);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return responseEntity;
    }
    private ResponseEntity<FakeStoreResponseDTO> makeGETCalloutForSingleProduct(String url){
        ResponseEntity<FakeStoreResponseDTO> responseEntity;
        try{
            responseEntity = restTemplate.getForEntity(url, FakeStoreResponseDTO.class);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return responseEntity;
    }

    private ResponseEntity<FakeStoreResponseDTO> makePOSTCallout(String url, FakeStoreRequestDTO requestDTO){
        ResponseEntity<FakeStoreResponseDTO> responseEntity;
        try{
            responseEntity = restTemplate.postForEntity(url,requestDTO, FakeStoreResponseDTO.class);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return responseEntity;
    }

    private ResponseEntity<FakeStoreResponseDTO> makePATCHCallout(String url, FakeStoreRequestDTO requestDTO){
        ResponseEntity<FakeStoreResponseDTO> responseEntity;
        try{
            responseEntity = patchForEntity(URI.create(url),requestDTO, FakeStoreResponseDTO.class);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        responseEntity = patchForEntity(URI.create(url),requestDTO, FakeStoreResponseDTO.class);
        return responseEntity;
    }

    private  ResponseEntity<FakeStoreResponseDTO> makeDELETECallout(String url, FakeStoreRequestDTO requestDTO){
        ResponseEntity<FakeStoreResponseDTO> responseEntity;
        try{
            responseEntity = deleteForEntity(URI.create(url),requestDTO, FakeStoreResponseDTO.class);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return responseEntity;
    }

    private ResponseEntity<FakeStoreResponseDTO> deleteForEntity(URI url, @Nullable Object request, Class<FakeStoreResponseDTO> responseType) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<FakeStoreResponseDTO>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.execute(url, HttpMethod.DELETE, requestCallback, responseExtractor);
        return responseEntity;
    }

    private ResponseEntity<FakeStoreResponseDTO> patchForEntity(URI url, @Nullable Object request, Class<FakeStoreResponseDTO> responseType) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<FakeStoreResponseDTO>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.execute(url, HttpMethod.PATCH, requestCallback, responseExtractor);
        return responseEntity;
    }
}
