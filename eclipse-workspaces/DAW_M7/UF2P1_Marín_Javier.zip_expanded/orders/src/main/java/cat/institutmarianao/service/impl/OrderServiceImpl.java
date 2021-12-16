package cat.institutmarianao.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderRepository;
import cat.institutmarianao.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Set<Order> getAll() {
		return orderRepository.getAll();
	}

	@Override
	public Order getByReference(Integer reference) {
		return orderRepository.getByReference(reference);
	}

	@Override
	public Set<Order> findByUser(User client) {
		return orderRepository.findByUser(client);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

}
