public class Notes {

    private int notes100;
    private int notes500;
    private int notes2000;

    public Notes() {}

    public Notes() {
        notes100 = 10;
        notes500 = 10;
        notes2000 = 10;
    }

    public Notes getNotesForWithdrawal(int amount) {
        Notes n = new Notes();

        if(amount > 2000){
            n.setNotes2000(amount / 2000);
            amount = amount % 2000;
        }
        if(amount > 500){
            n.setNotes500(amount / 500);
            amount = amount % 500;
        }
        if(amount > 100){
            n.setNotes100(amount / 100);
            amount = amount % 100;
        }

        if(amount != 0)
            return null;

        return n;
    }
}