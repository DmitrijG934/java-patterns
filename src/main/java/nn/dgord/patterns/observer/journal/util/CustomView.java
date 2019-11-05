package nn.dgord.patterns.observer.journal.util;

public final class CustomView {
    public interface IdView {}
    public interface MainJournalInfoView extends IdView {}
    public interface FullJournalView extends MainJournalInfoView {}
}
