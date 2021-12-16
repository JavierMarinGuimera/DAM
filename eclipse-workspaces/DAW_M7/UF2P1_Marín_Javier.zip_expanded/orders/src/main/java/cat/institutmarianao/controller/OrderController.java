package cat.institutmarianao.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.service.ItemService;
import cat.institutmarianao.service.OrderService;

// Configure Spring element and add mappings

@Controller
@RequestMapping("/users/orders")
@SessionAttributes("order")
public class OrderController {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ItemService itemService;

	@ModelAttribute("order")
	public Order setupOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User client = (User) userDetailsService.loadUserByUsername(username);

		Order order = new Order();
		order.setClient(client);
		return order;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView orders() throws ServletException, IOException {
		// Get authenticated user here
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User client = (User) userDetailsService.loadUserByUsername(username);

		// Get user orders

		List<Order> orders = new ArrayList<>();

		if (null != orderService.findByUser(client)) {
			orders = new ArrayList<>(orderService.findByUser(client));
			client.setOrders(orders);
		}

		// Prepare the orders.jsp view and send user orders and Order.STATES as
		// parameter
		ModelAndView modelview = new ModelAndView("orders");
		modelview.getModelMap().addAttribute("orders", orders);
		modelview.getModelMap().addAttribute("states", Order.STATES);
		return modelview;
	}

	@RequestMapping(value = "/newOrder", method = RequestMethod.GET)
	public ModelAndView newOrder() throws ServletException, IOException {
		// Prepare the newOrder.jsp view and send all the available items
		// The new user order is in session
		ModelAndView modelview = new ModelAndView("newOrder");
		modelview.getModelMap().addAttribute("itemsDisponibles", itemService.getAll());
		return modelview;
	}

	@RequestMapping(value = "/newOrder/clearItems", method = RequestMethod.GET)
	public String newOrderClearItems(@SessionAttribute("order") Order order) throws ServletException, IOException {
		order.clearItems();

		return "redirect:/users/orders/newOrder";
	}

	@RequestMapping(value = "/newOrder/increaseItem", method = RequestMethod.GET)
	public String newOrderIncreaseItem(@SessionAttribute("order") Order order,
			@RequestParam("referenciaItem") String referenciaItem) throws ServletException, IOException {

		// Get the item related to the reference passed as parameter
		// Increase item quantity
		order.increaseQuantity(itemService.get(referenciaItem));

		return "redirect:/users/orders/newOrder";
	}

	@RequestMapping(value = "/newOrder/decreaseItem", method = RequestMethod.GET)
	public String newOrderDecreaseItem(@SessionAttribute("order") Order order,
			@RequestParam("referenciaItem") String referenciaItem) throws ServletException, IOException {

		// Get the item related to the reference passed as parameter
		// Decrease item quantity
		order.decreaseQuantity(itemService.get(referenciaItem));

		return "redirect:/users/orders/newOrder";
	}

	@RequestMapping(value = "/newOrder/finishOrder", method = RequestMethod.GET)
	public String finishOrder() throws ServletException, IOException {
		// Nothing to do. We have order attibute in session, so the view can take it
		// from there
		return "finishOrder";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
	}

	@RequestMapping(value = "/newOrder/finishOrder", method = RequestMethod.POST)
	public String finishOrder(@ModelAttribute("order") Order newOrder, BindingResult bindingResult,
			SessionStatus sessionStatus) throws ParseException {

		if (bindingResult.hasErrors()) {
			return "finishOrder";
		}
		// Set a new reference for the order using Order.incReferenceSequence()
		newOrder.setReference(Order.incReferenceSequence());
		// Set order start date to current date

		// He probado a formatear la fecha, pero no hay manera. Siempre que la llamo
		// desde el "orders.jsp" me sale la fecha al completo.
//		String dia = new Date().toString().substring(8, 10);
//		String month = new Date().toString().substring(4, 8);
//		String year = new Date().toString().substring(24, 28);
//		String fecha = dia + "-" + month + "-" + year;
//		Date deliveryDateParsed = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);

		newOrder.setStartDate(new Date());
		// Save order
		orderService.save(newOrder);
		sessionStatus.setComplete(); // Clean session attributes - leave a new order ready in session

		return "redirect:/users/orders/";
	}
}
