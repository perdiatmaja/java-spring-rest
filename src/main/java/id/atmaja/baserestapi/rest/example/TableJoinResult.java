package id.atmaja.baserestapi.rest.example;

import id.perdiatmaja.sqlhelper.builder.ColumnsBuilder;

public class TableJoinResult {

    private String varStr1;

    private String varStr2;

    private int varInt1;

    private int varInt2;

    public TableJoinResult(int varInt1, String varStr1, int varInt2, String varStr2) {
        this.varStr1 = varStr1;
        this.varStr2 = varStr2;
        this.varInt1 = varInt1;
        this.varInt2 = varInt2;
    }

    public static String getSelectedColumns() {
        return new ColumnsBuilder()
                .columnsWithAlias("t1", "varInt", "varStr")
                .columnsWithAlias("t2", "varInt", "varStr")
                .build();
    }

    public String getVarStr1() {
        return varStr1;
    }

    public void setVarStr1(String varStr1) {
        this.varStr1 = varStr1;
    }

    public String getVarStr2() {
        return varStr2;
    }

    public void setVarStr2(String varStr2) {
        this.varStr2 = varStr2;
    }

    public int getVarInt1() {
        return varInt1;
    }

    public void setVarInt1(int varInt1) {
        this.varInt1 = varInt1;
    }

    public int getVarInt2() {
        return varInt2;
    }

    public void setVarInt2(int varInt2) {
        this.varInt2 = varInt2;
    }

}
