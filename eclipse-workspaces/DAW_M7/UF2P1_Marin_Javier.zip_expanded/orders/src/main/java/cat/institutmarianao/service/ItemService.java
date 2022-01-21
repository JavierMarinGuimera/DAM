package cat.institutmarianao.service;

import java.util.List;

import cat.institutmarianao.domain.Item;

public interface ItemService {
	List<Item> getAll();

	Item get(String reference);
}
