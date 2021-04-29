package utils;

public class ServerDTO {
    private String ip;
    private Integer port;
    private Integer load;




    public String getIp() {
        return ip;
    }


    public Integer getPort() {
        return port;
    }


    public synchronized Integer  getLoad() {
        return load;
    }

    public synchronized void setLoad(int load) {
        this.load = load;
    }

    public ServerDTO( String ip, int port, int load) {

        this.ip = ip;
        this.port = port;
        this.load = load;
    }

    @Override
    public String toString() {
        return "utils.ServerDTO{" +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", load=" + load +
                '}';
    }
}
