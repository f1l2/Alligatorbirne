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

import common.data.Connection;
import common.data.DataSource;
import common.data.dto.DataSourcesDTO;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.Transformer;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceDataSourceTransformer;
import configuration.management.repo.IoTDeviceRepository;
import configuration.management.repo.IoTDeviceTransformer;
import configuration.management.rest.activity.HeartbeatIoTDevice;
import configuration.management.rest.activity.RegisterIoTDevice;
import configuration.management.rest.activity.RegisterIoTDeviceDataSources;
import configuration.management.rest.activity.ValidateConnection;

@RestController
public class CMgmtManageIoTDeviceImpl implements CMgmtManageIoTDevice {

    final static Logger logger = LoggerFactory.getLogger(CMgmtManageIoTDeviceImpl.class);

    @Autowired
    private IoTDeviceRepository deviceRepo;

    @Autowired
    private IoTDeviceTransformer transformer;

    @Autowired
    private IoTDeviceDataSourceTransformer transformerDataSource;

    @Autowired
    private ValidateConnection validateConnection;

    @Autowired
    private RegisterIoTDevice register;

    @Autowired
    private RegisterIoTDeviceDataSources registerDataSources;

    @Autowired
    private HeartbeatIoTDevice heartbeat;

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Connection>> getAll() {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES));

        List<Connection> devices = transformer.toRemote(Transformer.makeCollection(deviceRepo.findAll()));

        return new ResponseEntity<List<Connection>>(devices, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.POST)
    public ResponseEntity<Connection> register(@RequestBody Connection connection) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE));

        /**
         * If device with URL already exists, return existing values. Otherwise generate new values.
         */

        validateConnection.setNextActivity(register);
        validateConnection.setCt(COMPONENT_TYPE.IOT_DEVICE);

        return validateConnection.doStep(connection);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> registerDataSources(@PathVariable(value = "id") Long id, @RequestBody DataSourcesDTO data) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES));

        registerDataSources.setId(id);

        return registerDataSources.doStep(data);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> heartbeat(@PathVariable Long id) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE));
        return heartbeat.doStep(id);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DataSource>> getDataSources(@PathVariable(value = "id") Long id) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CMGMT_GET_DEVICE_DATA_SOURCES));

        IoTDeviceRO device = deviceRepo.findOne(id);

        if (null == device) {

            return null;

        } else {
            List<DataSource> dataSource = transformerDataSource.toRemote(device.getIoTDeviceDataSources());

            return new ResponseEntity<List<DataSource>>(dataSource, HttpStatus.OK);
        }
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{devInfo}/{domainInfo}", method = RequestMethod.GET)
    public ResponseEntity<List<Connection>> getDeviceByDataSource(@PathVariable(value = "devInfo") String devInfo, @PathVariable(value = "domainInfo") String domainInfo) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CMGGT_GET_DEVICE_BY_DATA_SOURCES));

        List<IoTDeviceRO> devices = deviceRepo.findByIoTDeviceDataSources(devInfo, domainInfo);

        List<Connection> connections = transformer.toRemote(devices);

        return new ResponseEntity<List<Connection>>(connections, HttpStatus.OK);
    }
}
