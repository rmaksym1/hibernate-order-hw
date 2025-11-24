package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import mate.academy.dao.OrderDao;
import mate.academy.dao.ShoppingCartDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.service.OrderService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    OrderDao orderDao;

    @Inject
    ShoppingCartDao shoppingCartDao;

    @Override
    @Transactional
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setOrderTime(LocalDateTime.now());
        order.setTickets(new ArrayList<>(shoppingCart.getTickets()));

        Order saved = orderDao.add(order);

        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);

        return saved;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
