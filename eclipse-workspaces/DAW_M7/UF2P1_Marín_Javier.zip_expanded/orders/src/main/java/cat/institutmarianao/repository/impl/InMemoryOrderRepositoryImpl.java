package cat.institutmarianao.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderRepository;

@Repository
@Scope(value = "singleton")
public class InMemoryOrderRepositoryImpl implements OrderRepository {
	private static final Map<Integer, Order> referencesOrders = new HashMap<>();
	private static final Map<User, Set<Order>> clientsOrders = new HashMap<>();

	@Override
	public Set<Order> getAll() {
		return clientsOrders.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
	}

	@Override
	public Order getByReference(Integer reference) {
		return referencesOrders.get(reference);
	}

	@Override
	public Set<Order> findByUser(User client) {
		return clientsOrders.get(client);
	}

	@Override
	public void save(Order order) {
		saveToReferencesOrders(order);
		saveToClientsOrders(order);
	}

	private void saveToReferencesOrders(Order order) {
		referencesOrders.put(order.getReference(), order);
	}

	private void saveToClientsOrders(Order order) {
		User client = order.getClient();
		Set<Order> orders = clientsOrders.get(client);
		if (orders == null) {
			orders = new HashSet<>();
		}
		orders.add(order);
		clientsOrders.put(client, orders);
	}
}
