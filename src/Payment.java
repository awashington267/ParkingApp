public class Payment {
    private String payType;
    private Float charge;
    private String custID;

    public Payment(String custID, String payType, Float charge)
    {
        this.payType = payType;
        this.charge = charge;
        this.custID = custID;
    }

    @Override
    public String toString()
    {
        return custID + ", " + payType + ", " + charge;
    }

    public String getPayType() {
        return payType;
    }

    public Float getCharge() {
        return charge;
    }

    public String getCustID() {
        return custID;
    }

}
