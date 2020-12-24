package com.app.model.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.api.OrderStatusDao;
import com.app.model.api.Converter;
import com.app.model.dto.OrderDto;
import com.app.model.dto.OrderStatusDto;
import com.app.model.entity.OrderEntity;
import com.app.model.entity.OrderStatusEntity;

@Component
public class OrderStatusConverter implements Converter<OrderStatusDto, OrderStatusEntity> {

    private OrderStatusDao orderStatusDao;
    private OrderConverter orderConverter;

    @Override
    public OrderStatusDto convertFromEntityToDto(OrderStatusEntity entity) {
        OrderStatusDto orderStatusDto = new OrderStatusDto();

        orderStatusDto.setName(entity.getName());

        List<OrderDto> ordersDto = orderConverter.convertFromEntitiesToDtos(entity.getOrderEntities());
        orderStatusDto.setOrdersDto(ordersDto);

        return orderStatusDto;
    }

    @Override
    public OrderStatusEntity convertFromDtoToEntity(OrderStatusDto dto) {
        Long id = dto.getId();
        OrderStatusEntity orderStatusEntity = ( id != null ) ? orderStatusDao.getById(id) : new OrderStatusEntity();

        orderStatusEntity.setName(dto.getName());

        List<OrderEntity> orderEntities = orderConverter.convertFromDtosToEntities(dto.getOrdersDto());
        orderStatusEntity.setOrderEntities(orderEntities);

        return orderStatusEntity;
    }

    @Autowired
    public void setOrderStatusDao(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Autowired
    public void setOrderConverter(OrderConverter orderConverter) {
        this.orderConverter = orderConverter;
    }
}