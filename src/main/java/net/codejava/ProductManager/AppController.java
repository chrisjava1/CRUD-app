package net.codejava.ProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    public ProductService service;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts",listProducts);
        return "index";

    }

    @RequestMapping("/new")
    public String showNewProduct(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        return "add_product";

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product){
        service.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product",product);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "redirect:/";
    }

}
