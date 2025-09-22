package com.wp.estore.helper;

import com.wp.estore.dtos.OrderDto;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.dtos.UserDto;
import com.wp.estore.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    //Over here U and V are generic types
    //U --> entity (User)
    //V --> Dto (UserDto)
    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){

        List<U> entity = page.getContent();
        List<V> dtoList = entity.stream()
                .map(object -> new ModelMapper().map(object,type)) //Mapping entity to Dto using Model Mapper
                .collect(Collectors.toList());

        //Setting Pageable response fields to show the response
        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

    // helper method to get null or empty property names
    public static String[] getNullOrEmptyPropertyNames(OrderDto source) {
        List<String> nullProps = new ArrayList<>();
        if (source.getOrderStatus() == null || source.getOrderStatus().isEmpty()) nullProps.add("orderStatus");
        if (source.getPaymentStatus() == null || source.getPaymentStatus().isEmpty()) nullProps.add("paymentStatus");
        if (source.getDeliveredDate() == null) nullProps.add("deliveredDate");
        if (source.getBillingName() == null || source.getBillingName().isEmpty()) nullProps.add("billingName");
        if (source.getBillingPhone() == null || source.getBillingPhone().isEmpty()) nullProps.add("billingPhone");
        // Add more fields as needed
        return nullProps.toArray(new String[0]);
    }


}
