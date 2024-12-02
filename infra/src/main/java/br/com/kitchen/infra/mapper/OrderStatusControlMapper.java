package br.com.kitchen.infra.mapper;

import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.infra.entity.OrderStatusControlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderStatusControlMapper {
    OrderStatusControlMapper INSTANCE = Mappers.getMapper(OrderStatusControlMapper.class);

    OrderStatusControl orderStatusControlEntityToOrderStatusControl(final OrderStatusControlEntity orderStatusControlEntity);

    OrderStatusControlEntity orderStatusControlToOrderStatusControlEntity(final OrderStatusControl orderStatusControl);

}
