package ua.levelup.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.levelup.domain.Item;
import ua.levelup.services.ItemService;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
    private static final String SELECT_All_ITEMS = "SELECT * FROM items";
    private static final String INSERT_NEW_ITEM = "INSERT INTO items (name,description,price) #";
    @Autowired
    private ItemService itemService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {

        useSingleTemplate();
        useBatchTemplate();

        List<Item> items = itemService.getJdbcTemplate().query(SELECT_All_ITEMS, (rs, intNum) -> {
            Item it = new Item();
            it.setId(rs.getInt("id"));
            it.setName(rs.getString("name"));
            it.setDescription(rs.getString("name"));
            it.setPrice((int) rs.getFloat("price"));
            return it;
        });

        model.addAttribute("items", items);
        return "home";
    }
    void useSingleTemplate(){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        itemService.getJdbcTemplate().update((connection) -> {
            PreparedStatement preparedStatement =
                        connection.prepareStatement(INSERT_NEW_ITEM.replace("#","VALUES(?,?,?)"), new String[]{"id"});
            preparedStatement.setString(1, "single_item");
            preparedStatement.setString(2, "single_item");
            preparedStatement.setFloat(3, 5f);

            return preparedStatement;
        }, keyHolder);

        LOGGER.info("WAS CREATED NEW ITEM WITH id {}", keyHolder.getKey());

    }
    void useBatchTemplate(){
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            items.add(new Item("new_item_" + i, "new_item_" + i, (float) i));
        }

        itemService.getNamedParameterJdbcTemplate().batchUpdate(
                INSERT_NEW_ITEM.replace("#","VALUES(:name,:description,:price)"),
                SqlParameterSourceUtils.createBatch(items));
    }
}
