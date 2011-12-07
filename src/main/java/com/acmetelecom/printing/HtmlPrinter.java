package com.acmetelecom.printing;

/**
 *
 * @author dc408, ra808, je08, jm308
 */
class HtmlPrinter implements Printer {

    private static Printer instance = new HtmlPrinter();

    /**
     *
     */
    private HtmlPrinter() {
    }

    /**
     *
     * @return
     */
    public static Printer getInstance() {
        return instance;
    }

    /**
     *
     * @param name
     * @param phoneNumber
     * @param pricePlan
     */
    public void printHeading(String name, String phoneNumber, String pricePlan) {
        beginHtml();
        System.out.println(h2(name + "/" + phoneNumber + " - " + "Price Plan: " + pricePlan));
        beginTable();
    }

    /**
     *
     */
    private void beginTable() {
        System.out.println("<table border=\"1\">");
        System.out.println(tr(th("Time") + th("Number") + th("Duration") + th("Cost")));
    }

    /**
     *
     */
    private void endTable() {
        System.out.println("</table>");
    }

    /**
     *
     * @param text
     * @return
     */
    private String h2(String text) {
        return "<h2>" + text + "</h2>";
    }

    /**
     *
     * @param time
     * @param callee
     * @param duration
     * @param cost
     */
    public void printItem(String time, String callee, String duration, String cost) {
        System.out.println(tr(td(time) + td(callee) + td(duration) + td(cost)));
    }

    /**
     *
     * @param text
     * @return
     */
    private String tr(String text) {
        return "<tr>" + text + "</tr>";
    }

    /**
     *
     * @param text
     * @return
     */
    private String th(String text) {
        return "<th width=\"160\">" + text + "</th>";
    }

    /**
     *
     * @param text
     * @return
     */
    private String td(String text) {
        return "<td>" + text + "</td>";
    }

    /**
     *
     * @param total
     */
    public void printTotal(String total) {
        endTable();
        System.out.println(h2("Total: " + total));
        endHtml();
    }

    /**
     *
     */
    private void beginHtml() {
        System.out.println("<html>");
        System.out.println("<head></head>");
        System.out.println("<body>");
        System.out.println("<h1>");
        System.out.println("Acme Telecom");
        System.out.println("</h1>");
    }

    /**
     *
     */
    private void endHtml() {
        System.out.println("</body>");
        System.out.println("</html>");
    }
}
