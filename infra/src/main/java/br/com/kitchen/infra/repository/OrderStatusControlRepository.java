package br.com.kitchen.infra.repository;

import br.com.kitchen.infra.entity.OrderStatusControlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderStatusControlRepository extends MongoRepository<OrderStatusControlEntity, String> {

}
