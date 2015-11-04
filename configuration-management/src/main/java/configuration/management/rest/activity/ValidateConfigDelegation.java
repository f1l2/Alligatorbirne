package configuration.management.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.ConfigurationDelegation;

@Component
public class ValidateConfigDelegation extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(ValidateConfigDelegation.class);

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        if (null == item.getDataSink()) {
            logger.error("Delegation failed due missing data sink.");
            this.setErrorResponse(new ResponseEntity<String>("", HttpStatus.BAD_REQUEST));
        } else if (CollectionUtils.isEmpty(item.getDataSources())) {
            logger.error("Delegation failed due missing DataSources");
            this.setErrorResponse(new ResponseEntity<String>("", HttpStatus.BAD_REQUEST));
        }

        return next("OK", item);
    }

}
