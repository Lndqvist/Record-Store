package se.iths.java23.petterlundqvist.webshop.service;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.iths.java23.petterlundqvist.webshop.model.Order;
import se.iths.java23.petterlundqvist.webshop.model.OrderLine;

@Service
public class MailService {
    private final Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(Environment env){
        this.env = env;
    }

    public void formatMail(Order order) {
        String address = order.getUser().getEmail();
        String title = "Order confirmation, ID: " + order.getIdOrder();
        StringBuilder text = new StringBuilder("""
                Thank you for your order!

                Your order contains the following items:""");
        for(OrderLine l : order.getOrderLines()) {
            text.append("\n").append(l.getRecord().getName()).append(" - ")
                    .append(l.getRecord().getArtist()).append(" | ")
                    .append(l.getQuantity()).append(" x $")
                    .append(l.getRecord().getPrice());
        }
        sendMail(address, title, String.valueOf(text));
    }

    public void sendMail(String address, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(address);
        message.setSubject(title);
        message.setText(text);
        javaMailSender.send(message);
    }
}
