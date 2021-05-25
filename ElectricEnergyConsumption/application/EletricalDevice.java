package application;

public class EletricalDevice {
    private String name;
    private Double watts;
    private Integer time;
    private Integer quantidade;

    public EletricalDevice(String name, Double watts, Integer time, Integer quantidade) {
        this.name = name;
        this.watts = watts;
        this.time = time;
        this.quantidade = quantidade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWatts() {
        return this.watts;
    }

    public void setWatts(Double watts) {
        this.watts = watts;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {this.time = time; }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
