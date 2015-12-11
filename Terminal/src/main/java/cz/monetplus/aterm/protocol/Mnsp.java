package cz.monetplus.aterm.protocol;

import java.util.Formatter;

/**
 * Created by krajcovic on 12/11/15.
 */
public class Mnsp {
    private String sequenceNumber;
    private String terminalId;
    private String encryptType;

    private Spdh spdh;

    public Mnsp(String mnsp_terminal_name, String mnsp_sequence_number, String mnsp_encrypt_type) {
        this.sequenceNumber = mnsp_sequence_number;
        this.terminalId = mnsp_terminal_name;
        this.encryptType = mnsp_encrypt_type;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String toJson() {
        return "Mnsp{" +
                "sequenceNumber='" + sequenceNumber + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", encryptType='" + encryptType + '\'' +
                ", spdh=" + spdh +
                '}';
    }

    public String toStream() {
        Formatter formatter = new Formatter();
        return formatter.format("%08d%010d%C", this.sequenceNumber, this.terminalId, this.encryptType).toString();
    }

    @Override
    public String toString() {
        return "Mnsp{" +
                "sequenceNumber='" + sequenceNumber + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", encryptType='" + encryptType + '\'' +
                ", spdh=" + spdh +
                '}';
    }
}
