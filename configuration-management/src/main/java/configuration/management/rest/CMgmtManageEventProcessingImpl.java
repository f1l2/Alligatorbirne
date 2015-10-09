package configuration.management.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.transformer.Transformer;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.rest.task.DelegateConfigChange;
import configuration.management.rest.task.HeartbeatEP;
import configuration.management.rest.task.RegisterEP;
import configuration.management.rest.task.ValidateConfigDelegation;
import configuration.management.rest.task.ValidateConnection;

@RestController
public class CMgmtManageEventProcessingImpl implements CMgmtManageEventProcessing {

    final static Logger logger = LoggerFactory.getLogger(CMgmtManageEventProcessingImpl.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private EventProcessingTransformer transformer;

    @Autowired
    private ValidateConnection validateConnection;

    @Autowired
    private ValidateConfigDelegation validateConfigDelegation;

    @Autowired
    private RegisterEP register;

    @Autowired
    private HeartbeatEP heartBeat;

    @Autowired
    private DelegateConfigChange delegatConfigChange;

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Connection>> getAll() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING));

        List<Connection> eps = transformer.toRemote(Transformer.makeCollection(eventProcessingRepo.findAll()));

        return new ResponseEntity<List<Connection>>(eps, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.POST)
    public ResponseEntity<Connection> register(@RequestBody Connection connection) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING));

        validateConnection.setNextTask(register);
        validateConnection.setCt(COMPONENT_TYPE.EVENT_PROCESSING);

        return validateConnection.doStep(connection);
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.PUT)
    public ResponseEntity<Connection> heartbeat(@RequestBody Connection connection) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING));

        validateConnection.setNextTask(heartBeat);
        validateConnection.setCt(COMPONENT_TYPE.EVENT_PROCESSING);

        return validateConnection.doStep(connection);

    }

    @Override
    @RequestMapping(value = "/delegation/{id}", method = RequestMethod.POST)
    public ResponseEntity<ConfigurationDelegation> delegate(@PathVariable(value = "id") Long id, @RequestBody ConfigurationDelegation data) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_DELEGATION));

        validateConfigDelegation.setNextTask(delegatConfigChange);

        return validateConfigDelegation.doStep(data);

    }
}
