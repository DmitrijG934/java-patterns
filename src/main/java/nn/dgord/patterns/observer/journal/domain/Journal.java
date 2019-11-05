package nn.dgord.patterns.observer.journal.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nn.dgord.patterns.observer.journal.util.CustomView;
import nn.dgord.patterns.observer.journal.util.JsonUtils;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "journalName", "description", "pages", "year"})
@JsonView(CustomView.FullJournalView.class)
public class Journal extends BaseEntity {
    @JsonView(CustomView.MainJournalInfoView.class)
    private String journalName;
    @JsonView(CustomView.MainJournalInfoView.class)
    private String description;
    private Integer pages;
    private Integer year;

    public String toString() {
        return JsonUtils.toJson(this);
    }
}
