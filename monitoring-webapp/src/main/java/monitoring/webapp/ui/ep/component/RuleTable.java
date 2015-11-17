package monitoring.webapp.ui.ep.component;

import common.data.dto.RuleDTO;
import monitoring.webapp.ui.i18n.Messages;
import monitoring.webapp.ui.table.component.BeanItemTable;
import monitoring.webapp.ui.table.model.BeanItemColumn;

public interface RuleTable extends BeanItemTable<RuleDTO, RuleTable.COLUMN> {

    public enum COLUMN implements BeanItemColumn {
        /**/
        NAME("Name"),
        /**/
        RULE("Rule"),
        /**/
        ACTIVE("Active");

        private final String name;

        COLUMN(String id) {
            this.name = Messages.getString(id, id);
        }

        @Override
        public String getName() {
            return name;
        }

    }

}
