package cz.monetplus.aterm.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by krajcovic on 11/5/15.
 */
public class MessageTemplate {

    private String messageName;

    private List<Fid> fidList;


    /*

     */
    public MessageTemplate() {
        fidList = new ArrayList<Fid>();
    }

    /*

         */
    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }


    public List<Fid> getFidList() {
        return fidList;
    }

    public void setFidList(List<Fid> fidList) {
        this.fidList = fidList;
    }

    public void clear() {
        this.getFidList().clear();
        this.setMessageName("");
    }
}
