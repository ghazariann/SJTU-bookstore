package com.bookstore.bookstore_backend.entity;

//import com.bookstore.bookstore_backend.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")

public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
//	@JsonIgnore  // Ignore this field when converting to JSON
	@JoinColumn(name="user_id")
	private User user;

	@Column(name="order_date")
	private Date orderDate;

	@Column(name="total_price")
	private Double totalPrice;

	@Column(name="shipping_address")
	private String shippingAddress;

	// need this when adding new order with it's orderItems to the database
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;
}
