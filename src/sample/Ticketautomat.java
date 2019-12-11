package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Ticket{
    ERMAESSIGT,
    KURZSTRECKE,
    NORMAL
}

enum Geld{
    ZEHN("10 Cent"),
    ZWAHNZIG("20 Cent"),
    FUENZIG("50 Cent"),
    EINS("1 Euro"),
    ZWEI("2 Euro");

    private String name;

    Geld(String theType) {
        this.name = theType;
    }

    static public List toList(){
        List<Geld> geld = Arrays.asList(Geld.values());
        return geld;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class Ticketautomat {

    private Ticket ticket;
    private double fahrpreis;
    private double eingezahlt;

    Ticketautomat(){
        this.fahrpreis = 0;
        this.eingezahlt = 0;
    }

    public void ticketAuswahl(String t){
        switch (t){
            case "Ermaessigt":
                this.ticket = Ticket.ERMAESSIGT;
                break;
            case "Kurzstrecke":
                this.ticket = Ticket.KURZSTRECKE;
                break;
            case "Normal":
                this.ticket = Ticket.NORMAL;
                break;
            default:
                this.ticket = Ticket.NORMAL;

        }
    }

    public double getFahrpreis() {
        return this.fahrpreis;
    }

    public void setFahrpreis(double fahrpreis) {
        this.fahrpreis = fahrpreis;
    }

    public void setTicketToFahrpreis() {
        switch (getTicket()){
            case NORMAL:
                setFahrpreis(2.0);
                break;
            case ERMAESSIGT:
                setFahrpreis(1.0);
                break;
            case KURZSTRECKE:
                setFahrpreis(0.5);
                break;
            default:
                System.out.println("No Ticket selected");
        }
    }

    public Ticket getTicket() {
        return ticket;
    }

    public double getEingezahlt() {
        return eingezahlt;
    }

    public void setEingezahlt(Geld g) {
        switch (g){
            case ZEHN:
                this.eingezahlt += 0.10;
                break;
            case ZWAHNZIG:
                this.eingezahlt += 0.20;
                break;
            case FUENZIG:
                this.eingezahlt += 0.50;
                break;
            case EINS:
                this.eingezahlt += 1;
                break;
            case ZWEI:
                this.eingezahlt += 2;
                break;
            default:
                this.eingezahlt = 0;
        }

    }

    public double calcDiff(){
        return getEingezahlt() - getFahrpreis();
    }



}
