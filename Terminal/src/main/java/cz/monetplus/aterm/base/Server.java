package cz.monetplus.aterm.base;

/**
 * Created by krajcovic on 12/11/15.
 */
public class Server {

    private Long id;

    private String host;

    private Integer port;

    public Server() {

    }

    public Server(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
