package com.ecommerce_project.product_service.FakeStore;

import com.ecommerce_project.product_service.Adapters.IThirdPartyResponseDTO;
import lombok.Data;

@Data
public class FakeStoreResponseDTO extends IThirdPartyResponseDTO {
    private String id;
    private String title;
    private String category;
    private String description;
    private String image;
}
