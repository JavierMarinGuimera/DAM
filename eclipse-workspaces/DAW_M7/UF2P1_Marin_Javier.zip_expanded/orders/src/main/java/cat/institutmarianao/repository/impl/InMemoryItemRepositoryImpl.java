package cat.institutmarianao.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.repository.ItemRepository;

@Repository
@Scope(value = "singleton")
public class InMemoryItemRepositoryImpl implements ItemRepository {

	private final List<Item> items = new ArrayList<>();

	public InMemoryItemRepositoryImpl() {
		Item pig = new Item("A0000001", "Big Pig");
		pig.setDescription("Cerdito bonito y apestoso de gran calidad");
		pig.setPrice(23.90);
		pig.setImage("cerdo.jpg");

		Item monkey = new Item("A0000002", "Silly Monkey");
		monkey.setDescription("Simpático monito que hará las delícias de los más pequeños");
		monkey.setPrice(24.90);
		monkey.setImage("mono.jpg");

		Item bear = new Item("A0000003", "Fat Bear");
		bear.setDescription("El oso amoroso que le hará coger el sueño con sólo abrazarlo");
		bear.setPrice(25.00);
		bear.setImage("oso.jpg");

		Item cow = new Item("A0000004", "Vaca Paca");
		cow.setDescription("La vaca más realista del mundo de los peluches. Sólo le falta andar");
		cow.setPrice(23.80);
		cow.setImage("vaca.jpg");

		items.add(pig);
		items.add(monkey);
		items.add(bear);
		items.add(cow);
	}

	@Override
	public List<Item> getAll() {
		return items;
	}

	@Override
	public Item get(String reference) {
		for (Item Item : items) {
			if (Item != null && Item.getReference() != null && Item.getReference().equals(reference)) {
				return Item;
			}
		}
		throw new IllegalArgumentException("No Items with reference=" + reference + " found");
	}
}
