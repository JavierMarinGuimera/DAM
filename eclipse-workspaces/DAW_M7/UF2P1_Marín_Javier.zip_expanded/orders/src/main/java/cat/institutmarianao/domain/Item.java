package cat.institutmarianao.domain;

public class Item {
	private String reference;
	private String name;
	private String description;
	private Double price;
	private String image;

	public Item(String reference, String name) {
		this.reference = reference;
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Item)) {
			return false;
		}
		Item theOtherArticle = (Item) obj;
		if (reference == null) {
			return false;
		}
		return reference.equals(theOtherArticle.getReference());
	}

	@Override
	public int hashCode() {
		return reference.hashCode();
	}
}
