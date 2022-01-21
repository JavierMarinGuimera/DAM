package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.domain.Item;

public interface ItemRepository {
	List<Item> getAll();

	Item get(String reference);
}
