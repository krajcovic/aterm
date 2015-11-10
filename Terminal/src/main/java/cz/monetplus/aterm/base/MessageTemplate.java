package cz.monetplus.aterm.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krajcovic on 11/5/15.
 */
public class MessageTemplate {

    private Integer id;

    /**
     * Name of message.
     */
    private String name;

    /**
     * Description of message.
     */
    private String description;

    /**
     * List of fids.
     */
    private List<Fid> fidList;


    /**
     *
     */
    public MessageTemplate() {
        fidList = new ArrayList<Fid>();
        clear();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return
     */
    public List<Fid> getFidList() {
        return fidList;
    }

    /**
     * @param fidList
     */
    public void setFidList(List<Fid> fidList) {
        this.fidList = fidList;
    }

    /**
     *
     */
    public void clear() {
        this.getFidList().clear();
        this.setName("");
        this.setDescription("Description");
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
