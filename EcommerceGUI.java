import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
class Product {

    String name;
    int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " - ₹" + price;
    }
}

public class EcommerceGUI extends JFrame implements ActionListener {

    JComboBox<Product> productBox;

    JButton addCartBtn;
    JButton viewCartBtn;
    JButton checkoutBtn;
    JButton clearCartBtn;

    JTextArea outputArea;

    ArrayList<Product> cart = new ArrayList<>();

    Product[] products = {
            new Product("Laptop", 50000),
            new Product("Mouse", 500),
            new Product("Keyboard", 1200),
            new Product("Headphones", 2500),
            new Product("Mobile", 20000)
    };

    public EcommerceGUI() {

        setTitle("Simple E-Commerce System");
        setSize(700, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("E-COMMERCE SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(200, 20, 300, 30);
        add(title);

        JLabel productLabel = new JLabel("Select Product:");
        productLabel.setBounds(50, 100, 120, 30);
        add(productLabel);

        productBox = new JComboBox<>(products);
        productBox.setBounds(180, 100, 250, 30);
        add(productBox);

        addCartBtn = new JButton("Add To Cart");
        addCartBtn.setBounds(50, 170, 150, 40);
        addCartBtn.addActionListener(this);
        add(addCartBtn);

        viewCartBtn = new JButton("View Cart");
        viewCartBtn.setBounds(220, 170, 150, 40);
        viewCartBtn.addActionListener(this);
        add(viewCartBtn);

        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBounds(390, 170, 150, 40);
        checkoutBtn.addActionListener(this);
        add(checkoutBtn);

        clearCartBtn = new JButton("Clear Cart");
        clearCartBtn.setBounds(560, 170, 110, 40);
        clearCartBtn.addActionListener(this);
        add(clearCartBtn);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 250, 620, 220);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        outputArea.setEditable(false);
        add(outputArea);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCartBtn) {

            Product p = (Product) productBox.getSelectedItem();

            cart.add(p);

            outputArea.setText(
                    p.name + " added to cart successfully!");
        }
        else if (e.getSource() == viewCartBtn) {

            if (cart.isEmpty()) {

                outputArea.setText("Cart is Empty!");
                return;
            }

            String data = "===== YOUR CART =====\n\n";

            int total = 0;

            for (Product p : cart) {

                data += p.name + " - ₹" + p.price + "\n";
                total += p.price;
            }

            data += "\n----------------------";
            data += "\nTotal = ₹" + total;

            outputArea.setText(data);
        }
        else if (e.getSource() == checkoutBtn) {

            if (cart.isEmpty()) {

                outputArea.setText("Cart is Empty!");
                return;
            }

            int total = 0;

            for (Product p : cart) {
                total += p.price;
            }

            outputArea.setText(
                    "===== ORDER SUCCESSFUL =====\n\n" +
                    "Items Purchased : " + cart.size() +
                    "\nTotal Amount : ₹" + total +
                    "\nPayment Status : SUCCESS");
        }
        else if (e.getSource() == clearCartBtn) {

            cart.clear();

            outputArea.setText("Cart Cleared Successfully!");
        }
    }

    public static void main(String[] args) {

        new EcommerceGUI();
    }
}