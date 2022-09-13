public class Main {
    public static void main(String[] args) {
        UI ui = new UI();

        ui.components();
        ui.loadPanels();
        ui.loadTable();
        ui.loadSidePanel();
        ui.refresh();
    }
}
