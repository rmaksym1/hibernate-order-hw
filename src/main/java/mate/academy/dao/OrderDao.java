package mate.academy.dao;

import mate.academy.model.Order;
import mate.academy.model.User;
import java.util.List;

public interface OrderDao {
    public Order add(Order order);

    public List<Order> getByUser(User user);
}
