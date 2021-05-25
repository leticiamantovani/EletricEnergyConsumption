package application;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Consume {
    private EletricalDevice[] dispositivos = new EletricalDevice[9];
    private String[] list = new String[9];
    private String[] list2 = new String[9];
    DecimalFormat df = new DecimalFormat("###,##0.00");

    public Consume() {
        seed();
    }

    public void start() {
        int input = -1;

        for (int i = 0; i < dispositivos.length; ++i) {
            list[i] = (i + 1) + " - " + dispositivos[i].getName();
        }

        JOptionPane.showMessageDialog(null, "Bem-vindo ao calculo de consumo eletrico");

        while (input != 0) {

            JComboBox lst2 = new JComboBox(list);
            lst2.setEditable(true);
            JTextField field = new JTextField(10);
            JTextField field2= new JTextField(10);
            JLabel select = new JLabel("selecione um dos dispostivos");
            JLabel DeviceQuantity = new JLabel("diga quantos dispositivos tem em sua casa  ");
            JLabel time = new JLabel("digite as horas de uso por dia");

            JPanel fields = new JPanel(new GridLayout(3, 2));

            fields.add(select);
            fields.add(lst2);
            fields.add(DeviceQuantity);
            fields.add(field);
            fields.add(time);
            fields.add(field2);

            Object[] options = { "Confirmar", "Cancelar" };

            int result = JOptionPane.showConfirmDialog(null, fields, "calculadora de consumo elétrico", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (result) {
                case JOptionPane.OK_OPTION:
                    String itemSelection = lst2.getSelectedItem().toString();
                    Integer itemQuantity = Integer.valueOf(field.getText());
                    Integer itemHours = Integer.valueOf(field2.getText());
                    char ch1 = itemSelection.charAt(0);
                    Integer value = Character.getNumericValue(ch1) - 1;
                    if (itemQuantity < 0) {
                        JOptionPane.showMessageDialog(null, "digite uma quantidade de dispositivos valida");
                    } else if (itemHours < 0 || itemHours > 24) {
                        JOptionPane.showMessageDialog(null, "digite um numero de horas diarías");
                    } else {
                        dispositivos[value].setQuantidade(itemQuantity);
                        dispositivos[value].setTime(itemHours);
                        JOptionPane.showMessageDialog(null, "dispositivo adicionado");
                        int result2 = JOptionPane.showOptionDialog(null, "clique Confirmar para continuar ou cancelar para ver o total gasto" , "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                        if (result2 != 0) {
                            showEletricalDevices();
                            input = 0;
                        }
                    }
            }
        }
    }

    private void seed() {
        dispositivos[0] = new EletricalDevice("chuveiro", 5500.0, 0, 0);
        dispositivos[1] = new EletricalDevice("computador", 300.0, 0, 0);
        dispositivos[2] = new EletricalDevice("televisão",200.0, 0, 0);
        dispositivos[3] = new EletricalDevice("geladeira",250.0, 0, 0);
        dispositivos[4] = new EletricalDevice("videogame",20.0, 0, 0);
        dispositivos[5] = new EletricalDevice("maquina de lavar louça",1500.0, 0, 0);
        dispositivos[6] = new EletricalDevice("maquina de lavar roupa",1000.0, 0, 0);
        dispositivos[7] = new EletricalDevice("freezer",300.0, 0, 0);
        dispositivos[8] = new EletricalDevice("ferro elétrico",1000.0, 0, 0);
    }

    private void showEletricalDevices() {
        JPanel fields = new JPanel(new GridLayout(10, 3, 5, 5));
        JLabel items = new JLabel("eletronico  " + "consumo  " + "preço");
        fields.add(items);
        double priceTotal = 0;
        double consumeTotal = 0;


        for (int i = 0; i < dispositivos.length; ++i) {
            int quantidade = dispositivos[i].getQuantidade();
            if (quantidade > 0) {
                Double watts = dispositivos[i].getWatts();
                int horas = dispositivos[i].getTime();
                double consume = (watts * horas * 30) / 1000;
                double price = consume * 0.35;
                priceTotal += price;
                consumeTotal += consume;
                String consumef = df.format(consume) + " kWh/mês";
                String pricef = df.format(price) + " R$";
                String info = dispositivos[i].getName() + "  " + consumef + "  " + pricef;

                JLabel DeviceQuantity = new JLabel(info);
                fields.add(DeviceQuantity);
            }
        }
        JLabel priceTotalj = new JLabel("preço total: " + String.valueOf(df.format(priceTotal)) + " R$");
        JLabel consumeTotalj = new JLabel("consumo total: " + String.valueOf(df.format(consumeTotal)) + " kWh/mês");
        fields.add(priceTotalj);
        fields.add(consumeTotalj);

        int result = JOptionPane.showConfirmDialog(null, fields, "consumo eletrico", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        JPanel fields2 = new JPanel(new GridLayout(3, 3));

        double poluição = consumeTotal * 0.11;
        String text = "O seu consumo de energia além de afetar seu bolso, não contribui para a natureza o seu consumo de energia gerou " +  String.valueOf(df.format(poluição))
                + "kgCO2 em um mês uma arvore em um ano consegue absorver aproximadamente 22 quilos de gás carbonico";
        JLabel textj = new JLabel(text);
        fields2.add(textj);

        JOptionPane.showMessageDialog(null, fields2);
    }
}