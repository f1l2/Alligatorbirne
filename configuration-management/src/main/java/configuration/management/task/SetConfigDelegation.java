package configuration.management.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;

public class SetConfigDelegation extends DelegationTask {

    final static Logger logger = LoggerFactory.getLogger(SetConfigDelegation.class);

    private ConfigurationDelegation cd;

    public SetConfigDelegation(ConfigurationDelegation cd, List<Connection> connections) {

        super(connections);

        this.cd = cd;

    }

    @Override
    public void doRun(Connection connection) {

        RestTemplate restTemplate = new RestTemplate();

        logger.info("Delegate configuration modification for {}", connection.getUrl().getAuthority());

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.DEV_SET_CONFIGURATION, connection.getUrl().getAuthority());
            restTemplate.postForEntity(url, cd, String.class);
        } catch (Exception e) {
            logger.error("{}", e);

        }
    }
}
