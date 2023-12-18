package com.ecommerce_project.product_service.Adapters;

import com.ecommerce_project.product_service.Models.Product;

import java.util.List;

public interface IThirdPartyAdapter {
    public List<IThirdPartyResponseDTO> getAllProducts();
    public IThirdPartyResponseDTO getProductById(String id);
    public List<IThirdPartyResponseDTO> getTopXResults(String limit);
    public List<IThirdPartyResponseDTO> getSortedResults(String sortOrder);
    public List<IThirdPartyResponseDTO> getAllProductsInOneCategory(String category);
    public IThirdPartyResponseDTO addNewProduct(IThirdPartyRequestDTO requestDTO);
    public IThirdPartyResponseDTO updateAProduct(IThirdPartyRequestDTO requestDTO);
    public IThirdPartyResponseDTO deleteAProduct(IThirdPartyRequestDTO requestDTO);
    public List<IThirdPartyResponseDTO> getAllCategories();
    public Product convertDtoToEntity(IThirdPartyResponseDTO responseDTO);
    public IThirdPartyRequestDTO convertEntityToDto(Product product);

}
