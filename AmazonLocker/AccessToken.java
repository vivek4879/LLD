package AmazonLocker;


class AccessToken{
    private final int code;
    private final Compartment compartment;
    private final long expiresAt;
    private boolean isUsed;

    public AccessToken(Compartment compartment, long curTime){
        this.code = generateCode();
        this.compartment = compartment;
        this.expiresAt = curTime + 7 *24*60*60*1000L;
    }
    private int generateCode() {
        return 100000 + new java.util.Random().nextInt(900000);  // 6-digit
    }
    public int getCode(){
        return code;
    }
    public Compartment getCompartment(){
        return compartment;
    }
    public boolean isValid(long curTime){
        return !isUsed && expiresAt > curTime;
    }

    public void markUsed(){
        this.isUsed = true;
    }
}