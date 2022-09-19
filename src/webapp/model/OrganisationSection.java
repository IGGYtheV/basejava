package webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganisationSection extends Section {
    private static final long serialVersionUID = 1L;
    private final List<Organisation> organisations;

    public OrganisationSection(Organisation... organisations) {
        this(Arrays.asList(organisations));
    }

    public OrganisationSection(List<Organisation> organisations) {
        Objects.requireNonNull(organisations, "organizations must be not null!");
        this.organisations = organisations;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    @Override
    public String toString() {
        return organisations.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganisationSection)) return false;

        OrganisationSection that = (OrganisationSection) o;

        return getOrganisations().equals(that.getOrganisations());
    }

    @Override
    public int hashCode() {
        return getOrganisations().hashCode();
    }
}
