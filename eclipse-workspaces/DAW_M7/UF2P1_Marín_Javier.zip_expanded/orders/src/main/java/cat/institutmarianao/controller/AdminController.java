package cat.institutmarianao.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.service.OrderService;

// Configure Spring element and add mappings

@Controller
@RequestMapping("/admin/orders")
public class AdminController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView orders() throws ServletException, IOException {

		// Get all user orders
		// Prepare the orders.jsp view and send user orders and Order.STATES as
		// parameter
		ModelAndView modelview = new ModelAndView("orders");
		modelview.getModelMap().addAttribute("orders", orderService.getAll());
		modelview.getModelMap().addAttribute("states", Order.STATES);
		return modelview;
	}

	@RequestMapping(value = "/setState", method = RequestMethod.GET)
	public String setState(@RequestParam("referenciaItem") int referenciaItem,
			@RequestParam("state") int state) throws ServletException, IOException {

		/* Get the order related to the reference passed as parameter */
		Order order = orderService.getByReference(referenciaItem);
		/* Set the order state with the state value */
		if (order == null) {
			throw new ServletException("No se ha encontrado la orden con la referencia: " + referenciaItem);
		}
		order.setState(state);
		/* Save the order */
		orderService.save(order);
		return "redirect:/admin/orders/";
	}

	@RequestMapping(value = "/setDeliveryDate", method = RequestMethod.GET)
	public String setDeliveryDate(@RequestParam("referenciaItem") int referenciaItem,
			@RequestParam("deliveryDate") String deliveryDate) throws ServletException, IOException, ParseException {

		/* Get the order related to the reference passed a;s parameter */
		Order order = orderService.getByReference(referenciaItem);
		/* Set the order delivery date with the deliveryDate value */
		if (order == null) {
			throw new ServletException("No se ha encontrado la orden con la referencia: " + referenciaItem);
		}
		Date deliveryDateParsed = new SimpleDateFormat("dd-MM-yyyy").parse(deliveryDate);
		order.setDeliveryDate(deliveryDateParsed);
		/* Save the order */
		orderService.save(order);
		return "redirect:/admin/orders/";
	}
}
