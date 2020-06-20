package id.atmaja.baserestapi.rest.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Table1")
public class Table1 {

    @Id
    private String varStr;

    private int varInt;

    private boolean varBool;

    public String getVarStr() {
        return varStr;
    }

    public void setVarStr(String varStr) {
        this.varStr = varStr;
    }

    public int getVarInt() {
        return varInt;
    }

    public void setVarInt(int varInt) {
        this.varInt = varInt;
    }

    public boolean isVarBool() {
        return varBool;
    }

    public void setVarBool(boolean varBool) {
        this.varBool = varBool;
    }

}
