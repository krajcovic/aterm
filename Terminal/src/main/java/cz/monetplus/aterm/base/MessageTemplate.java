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
     * Type of message.
     */
    private Character type;

    /**
     * Subtype of message.
     */
    private Character subType;

    /**
     * Transaction code.
     */
    private Integer code;

    /**
     * precessing flag
     */
    private Integer flag;

    public MessageTemplate(String name) {
        fidList = new ArrayList<Fid>();
//        clear();
    }


    /**
     *
     */
    public MessageTemplate(String name, Character type, Character subType, Integer code, Integer flag) {
        fidList = new ArrayList<Fid>();
        this.setType(type);
        this.setSubType(subType);
        this.setCode(code);
        this.setFlag(flag);
//        clear();
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
//    public void clear() {
//        this.getFidList().clear();
//        this.setName("Undefined");
//        this.setDescription("Description");
//    }

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

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Character getSubType() {
        return subType;
    }

    public void setSubType(Character subType) {
        this.subType = subType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
