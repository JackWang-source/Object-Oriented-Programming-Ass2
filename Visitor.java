public class Visitor extends Person {
    private String ticketNumber;
    private String visitDate;

    public Visitor(String name, int age, String id, String ticketNumber, String visitDate) {
        super(name, age, id);
        this.ticketNumber = ticketNumber;
        this.visitDate = visitDate;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
}