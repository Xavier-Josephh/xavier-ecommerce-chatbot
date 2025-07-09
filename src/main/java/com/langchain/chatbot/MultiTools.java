package com.langchain.chatbot;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class MultiTools {
  /*  @Tool("Convert amounts between two currencies")
    double convertCurrency(
            @P("Currency to convert from") String fromCurrency,
            @P("Currency to convert to") String toCurrency,
            @P("Amount to convert") double amount) {

        double result = amount;

        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            result = amount * 0.93;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            result = amount * 0.79;
        }

        System.out.println(
                "convertCurrency(fromCurrency = " + fromCurrency +
                        ", toCurrency = " + toCurrency +
                        ", amount = " + amount + ") == " + result);

        return result;
    }

    @Tool("Get the current value of a stock in US dollars")
    double getStockPrice(@P("Stock symbol") String symbol) {
        double result = 170.0 + 10 * new Random().nextDouble();

        System.out.println("getStockPrice(symbol = " + symbol + ") == " + result);

        return result;
    }

    @Tool("Apply a percentage to a given amount")
    double applyPercentage(@P("Initial amount") double amount, @P("Percentage between 0-100 to apply") double percentage) {
        double result = amount * (percentage / 100);

        System.out.println("applyPercentage(amount = " + amount + ", percentage = " + percentage + ") == " + result);

        return result;
    }*/

    @Tool("Track status of the order")
    String trackOrderStatus(@P("Order Number") String orderNumber) {
        return "Your order".concat(orderNumber).concat("is still on the way to your delivery location");
    }

    @Tool("Exchange an order")
    String exchangeOrder(@P("Order Number") String orderNumber, @P(value = "The product that has to be exchanged", required = false) String productTobeExchanged) {
        String is_exchanged_with = "Your order".concat(orderNumber).concat("is exchanged with").concat(productTobeExchanged != null ? productTobeExchanged : "IronBox")
                .concat("and will delivered to on next week");
        if (orderNumber.equals("EE053") && productTobeExchanged.equals("IronBox")) {
            return is_exchanged_with;
        } else if (orderNumber.equals("EE053")) {
            return "I see two products, Please tell us the product that has to be exchanged";
        }
        return is_exchanged_with;
    }

    @Tool("Replace an order")
    String replaceOrder(@P("Order Number") String orderNumber, @P("The product that has to be replaced") String productTobeReplaced) {
        return "Your order".concat(orderNumber).concat("is replace with").concat(productTobeReplaced).concat("and will delivered to on next week");
    }

    @Tool("Refund an order")
    String refundOrder(@P("Order Number") String orderNumber, @P("The product that has to be refunded") String productTobeRefunded) {
        return "Your order".concat(orderNumber).concat("which is").concat(productTobeRefunded).concat("is refunded and you will be getting it next week");
    }
}
